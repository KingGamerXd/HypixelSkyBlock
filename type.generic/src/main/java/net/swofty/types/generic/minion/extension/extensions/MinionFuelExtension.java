package net.swofty.types.generic.minion.extension.extensions;

import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.click.ClickType;
import net.minestom.server.item.ItemComponent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.swofty.commons.StringUtility;
import net.swofty.commons.item.ItemType;
import net.swofty.types.generic.gui.inventory.GUIItem;
import net.swofty.types.generic.gui.inventory.ItemStackCreator;
import net.swofty.types.generic.gui.inventory.SkyBlockAbstractInventory;
import net.swofty.types.generic.gui.inventory.actions.SetCursorItemAction;
import net.swofty.types.generic.gui.inventory.inventories.GUIMinion;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.components.MinionFuelComponent;
import net.swofty.types.generic.item.updater.NonPlayerItemUpdater;
import net.swofty.types.generic.minion.IslandMinionData;
import net.swofty.types.generic.minion.extension.MinionExtension;
import net.swofty.types.generic.user.SkyBlockPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinionFuelExtension extends MinionExtension {
    private long insertionTime = 0;
    private int count = 0;

    public MinionFuelExtension(@Nullable ItemType itemTypeLinker, @Nullable Object data) {
        super(itemTypeLinker, data);

        if (data != null) {
            insertionTime = (long) data;
        }
    }

    @Override
    public @NonNull GUIItem getDisplayItem(IslandMinionData.IslandMinion minion, int slot, SkyBlockAbstractInventory inventory) {
        boolean shouldDisplayItem = true;

        if (getItemTypePassedIn() == null) {
            shouldDisplayItem = false;
        } else {
            long timeFuelLasts = new SkyBlockItem(getItemTypePassedIn()).getComponent(MinionFuelComponent.class).getFuelLastTimeInMS();
            if (System.currentTimeMillis() - insertionTime > timeFuelLasts && timeFuelLasts > 0) {
                // Time has surpassed the fuel time of 1 of the fuel items
                count -= 1;
                if(count <= 0) {
                    // All fuel items have been used
                    shouldDisplayItem = false;
                    setItemTypePassedIn(null);
                }
                // Set insertion time for now because we've moved onto the next item
                insertionTime = System.currentTimeMillis();
                minion.getExtensionData().setData(slot, MinionFuelExtension.this);
            }
        }

        if (!shouldDisplayItem)
            return GUIItem.builder(slot)
                    .item(ItemStackCreator.getStack("§aFuel", Material.ORANGE_STAINED_GLASS_PANE, 1,
                            "§7Increase the speed of your",
                            "§7minion by adding minion fuel",
                            "§7items here.",
                            " ",
                            "§cNote: §7You can't take fuel",
                            "§7back out after you place it",
                            "§7here."))
                    .onClick((ctx, itemClicked) -> {
                        SkyBlockItem fuelItem = new SkyBlockItem(ctx.cursorItem());

                        if (fuelItem.hasComponent(MinionFuelComponent.class)) {
                            new SetCursorItemAction(ctx, ItemStack.AIR).execute(inventory);
                            addFuel(minion, slot, fuelItem);
                            return true;
                        } else {
                            ctx.player().sendMessage("§cThis item is not a valid Minion Fuel item.");
                            return false;
                        }
                    })
                    .build();

        return GUIItem.builder(slot)
                .item(() -> {
                    long timeFuelLasts = new SkyBlockItem(getItemTypePassedIn()).getComponent(MinionFuelComponent.class).getFuelLastTimeInMS();

                    ItemStack.Builder itemBuilder = new NonPlayerItemUpdater(new SkyBlockItem(getItemTypePassedIn(), count)).getUpdatedItem();

                    List<Component> lore = new ArrayList<>(itemBuilder.build().get(ItemComponent.LORE));

                    if(timeFuelLasts > 0) {
                        lore.add(Component.text(""));
                        lore.add(Component.text("§7Time Remaining: §b" + StringUtility.formatTimeLeft(timeFuelLasts * count - (System.currentTimeMillis() - insertionTime)))
                                .decorations(Collections.singleton(TextDecoration.ITALIC), false));
                        lore.add(Component.text(""));
                        lore.add(Component.text("§cRight Click to destroy this fuel.")
                                .decorations(Collections.singleton(TextDecoration.ITALIC), false));
                    }else{
                        lore.add(Component.text(""));
                        lore.add(Component.text("§eClick to take fuel out.")
                                .decorations(Collections.singleton(TextDecoration.ITALIC), false));
                    }

                    return itemBuilder.set(ItemComponent.LORE, lore).build();
                })
                .onClick((ctx, itemstack) -> {
                    SkyBlockPlayer player = ctx.player();
                    SkyBlockItem item = new SkyBlockItem(itemstack);

                    if (item.getComponent(MinionFuelComponent.class).getFuelLastTimeInMS() == 0) {
                        player.addAndUpdateItem(getItemTypePassedIn());
                        setItemTypePassedIn(null);
                        minion.getExtensionData().setData(slot, MinionFuelExtension.this);
                        player.openInventory(new GUIMinion(minion));
                        return false;
                    }
                    if (ctx.clickType() == ClickType.RIGHT_CLICK) {
                        setItemTypePassedIn(null);
                        minion.getExtensionData().setData(slot, MinionFuelExtension.this);
                        player.openInventory(new GUIMinion(minion));
                        return false;
                    }

                    SkyBlockItem fuelItem = new SkyBlockItem(ctx.cursorItem());
                    if (!(fuelItem.hasComponent(MinionFuelComponent.class))){
                        player.sendMessage("§cYou can only put fuel in this slot.");
                        return false;
                    }

                    if (getItemTypePassedIn() != fuelItem.getAttributeHandler().getPotentialType())
                        player.sendMessage("§aReplaced your old fuel!");

                    int added = addFuel(minion, slot, fuelItem);
                    if (added > 0)
                        fuelItem.setAmount(fuelItem.getAmount() - added);

                    if (fuelItem.getAmount() > 0) {
                        new SetCursorItemAction(ctx, new NonPlayerItemUpdater(fuelItem.getItemStack()).getUpdatedItem().build()).execute(inventory);
                    } else new SetCursorItemAction(ctx, ItemStack.AIR).execute(inventory);

                    return true;
                })
                .build();
    }

    // Returns the amount of fuel added
    public int addFuel(IslandMinionData.IslandMinion minion, int slot, SkyBlockItem fuelItem){
        if (fuelItem.hasComponent(MinionFuelComponent.class)) {
            insertionTime = System.currentTimeMillis();
            int added = fuelItem.getAmount();

            if (getItemTypePassedIn() != fuelItem.getAttributeHandler().getPotentialType()) {
                count = added;
            } else {
                int together = count + added;
                if (together > 64) {
                    added = 64 - count;
                    count = 64;
                } else {
                    count = together;
                }
            }

            setItemTypePassedIn(fuelItem.getAttributeHandler().getPotentialType());
            minion.getExtensionData().setData(slot, MinionFuelExtension.this);
            return added;
        }
        return 0;
    }

    @Override
    public String toString() {
        if (getItemTypePassedIn() == null) {
            return "null";
        }
        return getItemTypePassedIn().toString() + ":" + insertionTime + ":" + count;
    }

    @Override
    public void fromString(String string) {
        if (string.equals("null")) {
            setItemTypePassedIn(null);
            return;
        }
        String[] split = string.split(":");
        setItemTypePassedIn(ItemType.valueOf(split[0]));
        insertionTime = Long.parseLong(split[1]);
        if(split.length > 2)
            count = Integer.parseInt(split[2]);
    }
}
