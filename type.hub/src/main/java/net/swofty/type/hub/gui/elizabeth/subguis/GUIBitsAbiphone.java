package net.swofty.type.hub.gui.elizabeth.subguis;

import net.kyori.adventure.text.Component;
import net.minestom.server.event.inventory.InventoryCloseEvent;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemComponent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.swofty.commons.StringUtility;
import net.swofty.commons.item.ItemType;
import net.swofty.type.hub.gui.elizabeth.GUIBitsShop;
import net.swofty.types.generic.data.datapoints.DatapointToggles;
import net.swofty.types.generic.gui.inventory.GUIItem;
import net.swofty.types.generic.gui.inventory.ItemStackCreator;
import net.swofty.types.generic.gui.inventory.SkyBlockAbstractInventory;
import net.swofty.types.generic.gui.inventory.actions.SetTitleAction;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.updater.NonPlayerItemUpdater;
import net.swofty.types.generic.user.SkyBlockPlayer;

import java.util.ArrayList;

public class GUIBitsAbiphone extends SkyBlockAbstractInventory {

    public GUIBitsAbiphone() {
        super(InventoryType.CHEST_4_ROW);
        doAction(new SetTitleAction(Component.text("Bits Shop - Abiphone")));
    }

    @Override
    public void handleOpen(SkyBlockPlayer player) {
        fill(ItemStackCreator.createNamedItemStack(Material.BLACK_STAINED_GLASS_PANE, " ").build());

        // Back button
        attachItem(GUIItem.builder(31)
                .item(ItemStackCreator.getStack("§aGo Back", Material.ARROW, 1,
                        "§7To Bits Shop").build())
                .onClick((ctx, item) -> {
                    ctx.player().openInventory(new GUIBitsShop());
                    return true;
                })
                .build());

        // Contacts Trio item
        attachItem(createContactsTrioItem());

        // Abicases item
        attachItem(createAbicasesItem());
    }

    private GUIItem createContactsTrioItem() {
        final int price = 6450;
        final ItemType item = ItemType.ABIPHONE_CONTACTS_TRIO;

        return GUIItem.builder(12)
                .item(() -> {
                    SkyBlockItem skyBlockItem = new SkyBlockItem(item);
                    ItemStack.Builder itemStack = new NonPlayerItemUpdater(skyBlockItem).getUpdatedItem();
                    ArrayList<String> lore = new ArrayList<>(itemStack.build()
                            .get(ItemComponent.LORE).stream()
                            .map(StringUtility::getTextFromComponent)
                            .toList());
                    lore.add(" ");
                    lore.add("§7Cost");
                    lore.add("§b" + StringUtility.commaify(price) + " Bits");
                    lore.add(" ");
                    lore.add("§eClick to trade!");
                    return ItemStackCreator.updateLore(itemStack, lore).build();
                })
                .onClick((ctx, clickedItem) -> {
                    SkyBlockPlayer player = ctx.player();
                    if (player.getBits() >= price) {
                        SkyBlockItem skyBlockItem = new SkyBlockItem(item);
                        ItemStack.Builder itemStack = new NonPlayerItemUpdater(skyBlockItem).getUpdatedItem();
                        SkyBlockItem finalItem = new SkyBlockItem(itemStack.build());

                        if (!player.getToggles().get(DatapointToggles.Toggles.ToggleType.PURCHASE_CONFIRMATION_BITS)) {
                            player.addAndUpdateItem(finalItem);
                            player.setBits(player.getBits() - price);
                        } else {
                            player.openInventory(new GUIBitsConfirmBuy(finalItem, price));
                        }
                    } else {
                        player.sendMessage("§cYou don't have enough Bits to buy that!");
                    }
                    return true;
                })
                .build();
    }

    private GUIItem createAbicasesItem() {
        return GUIItem.builder(14)
                .item(ItemStackCreator.getStackHead("§5Abicases",
                        "a3c153c391c34e2d328a60839e683a9f82ad3048299d8bc6a39e6f915cc5a", 1,
                        "§7Any expensive Abiphone needs some",
                        "§7accessories!",
                        " ",
                        "§7Get an Abicase! It keeps your",
                        "§7accessory bag safe while you hold",
                        "§7your Abiphone in your hands.",
                        " ",
                        "§dThree brands to choose from!",
                        "§7Only ONE Abicase will work at a time.",
                        "§eClick to view Abicases!").build())
                .onClick((ctx, item) -> {
                    ctx.player().openInventory(new GUIBitsAbicases());
                    return true;
                })
                .build();
    }

    @Override
    public boolean allowHotkeying() {
        return false;
    }

    @Override
    public void onClose(InventoryCloseEvent event, CloseReason reason) {
    }

    @Override
    public void onBottomClick(InventoryPreClickEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void onSuddenQuit(SkyBlockPlayer player) {
    }
}