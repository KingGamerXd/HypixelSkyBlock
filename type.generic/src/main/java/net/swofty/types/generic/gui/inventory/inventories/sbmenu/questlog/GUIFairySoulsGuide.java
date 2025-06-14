package net.swofty.types.generic.gui.inventory.inventories.sbmenu.questlog;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.minestom.server.event.inventory.InventoryCloseEvent;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.Material;
import net.swofty.types.generic.gui.inventory.GUIItem;
import net.swofty.types.generic.gui.inventory.ItemStackCreator;
import net.swofty.types.generic.gui.inventory.SkyBlockAbstractInventory;
import net.swofty.types.generic.gui.inventory.actions.SetTitleAction;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.types.generic.user.fairysouls.FairySoulZone;

public class GUIFairySoulsGuide extends SkyBlockAbstractInventory {
    private static final int[] LOCATION_SLOTS = {
            11, 12, 13, 14, 15, 16,
            19, 20, 21, 22, 23, 24
    };

    @Getter
    private enum FairySouls {
        THE_END("The End", "7840b87d52271d2a755dedc82877e0ed3df67dcc42ea479ec146176b02779a5", FairySoulZone.THE_END),
        THE_FARMING_ISLAND("The Farming Islands", "4d3a6bd98ac1833c664c4909ff8d2dc62ce887bdcf3cc5b3848651ae5af6b", FairySoulZone.THE_FARMING_ISLANDS),
        DWARVEN_MINES("Dwarven Mines", "6b20b23c1aa2be0270f016b4c90d6ee6b8330a17cfef87869d6ad60b2ffbf3b5", FairySoulZone.DWARVEN_MINES),
        JERRYS_WORKSHOP("Jerry's Workshop", "6dd663136cafa11806fdbca6b596afd85166b4ec02142c8d5ac8941d89ab7", FairySoulZone.JERRYS_WORKSHOP),
        DEEP_CAVERNS("Deep Caverns", "569a1f114151b4521373f34bc14c2963a5011cdc25a6554c48c708cd96ebfc", FairySoulZone.DEEP_CAVERNS),
        CRIMSON_ISLAND("Crimson Isle", "c3687e25c632bce8aa61e0d64c24e694c3eea629ea944f4cf30dcfb4fbce071", FairySoulZone.CRIMSON_ISLE),
        DUNGEON_HUB("Dungeon Hub", "9b56895b9659896ad647f58599238af532d46db9c1b0389b8bbeb70999dab33d", FairySoulZone.DUNGEON_HUB),
        HUB("Hub", "686718d85e25b006f2c8f160f619b23c8fd6ae75ddf1c06308ec0f539d931703", FairySoulZone.HUB),
        SPIDERS_DEN("Spider's Den", "c754318a3376f470e481dfcd6c83a59aa690ad4b4dd7577fdad1c2ef08d8aee6", FairySoulZone.SPIDERS_DEN),
        THE_RIFT("The Rift", "f26192609d6c46ade73e807fc40dbc3a1a1afbb456ae165785b0fe834dd1cb57", FairySoulZone.THE_RIFT),
        GOLD_MINE("Gold Mine", "73bc965d579c3c6039f0a17eb7c2e6faf538c7a5de8e60ec7a719360d0a857a9", FairySoulZone.GOLD_MINE),
        THE_PARK("The Park", "a221f813dacee0fef8c59f76894dbb26415478d9ddfc44c2e708a6d3b7549b", FairySoulZone.THE_PARK);

        private final String regionName;
        private final String texture;
        private final FairySoulZone zone;

        FairySouls(String regionName, String texture, FairySoulZone zone) {
            this.regionName = regionName;
            this.texture = texture;
            this.zone = zone;
        }
    }

    public GUIFairySoulsGuide() {
        super(InventoryType.CHEST_5_ROW);
        doAction(new SetTitleAction(Component.text("Fairy Souls Guide")));
    }

    @Override
    public void handleOpen(SkyBlockPlayer player) {
        fill(ItemStackCreator.createNamedItemStack(Material.BLACK_STAINED_GLASS_PANE).build());

        // Close and back buttons
        attachItem(GUIItem.builder(40)
                .item(ItemStackCreator.createNamedItemStack(Material.BARRIER, "§cClose").build())
                .onClick((ctx, item) -> {
                    ctx.player().closeInventory();
                    return true;
                })
                .build());

        attachItem(GUIItem.builder(39)
                .item(ItemStackCreator.getStack("§aGo Back", Material.ARROW, 1,
                        "§7To Mission Log").build())
                .onClick((ctx, item) -> {
                    ctx.player().openInventory(new GUIMissionLog());
                    return true;
                })
                .build());

        // Miscellaneous fairy souls
        attachItem(GUIItem.builder(10)
                .item(() -> {
                    int found = player.getFairySoulHandler().getFound(FairySoulZone.MISC_DUNGEONS) +
                            player.getFairySoulHandler().getFound(FairySoulZone.MISC_FISHING) +
                            player.getFairySoulHandler().getFound(FairySoulZone.MISC_GARDEN) +
                            player.getFairySoulHandler().getFound(FairySoulZone.MISC_PLACEABLE);
                    int max = player.getFairySoulHandler().getMax(FairySoulZone.MISC_DUNGEONS) +
                            player.getFairySoulHandler().getMax(FairySoulZone.MISC_FISHING) +
                            player.getFairySoulHandler().getMax(FairySoulZone.MISC_GARDEN) +
                            player.getFairySoulHandler().getMax(FairySoulZone.MISC_PLACEABLE);

                    return ItemStackCreator.getStackHead("§dMiscellaneous",
                            "126ec1ca185b47aad39f931db8b0a8500ded86a127a204886ed4b3783ad1775c",
                            1,
                            "§7Fairy Souls: §e" + found + "§7/§d" + max,
                            " §7Dungeon: §d" + player.getFairySoulHandler().getMax(FairySoulZone.MISC_DUNGEONS),
                            " §7Fishing: §d" + player.getFairySoulHandler().getMax(FairySoulZone.MISC_FISHING),
                            " §7Garden: §d" + player.getFairySoulHandler().getMax(FairySoulZone.MISC_GARDEN),
                            " §7Placeable: §d" + player.getFairySoulHandler().getMax(FairySoulZone.MISC_PLACEABLE)).build();
                })
                .build());

        // Location-specific fairy souls
        FairySouls[] allFairySouls = FairySouls.values();
        for (int i = 0; i < LOCATION_SLOTS.length; i++) {
            if (i >= allFairySouls.length) break;

            FairySouls fairySoul = allFairySouls[i];
            attachItem(GUIItem.builder(LOCATION_SLOTS[i])
                    .item(() -> ItemStackCreator.getStackHead("§d" + fairySoul.regionName,
                            fairySoul.texture,
                            1,
                            "§7Fairy Souls: §e" + player.getFairySoulHandler().getFound(fairySoul.zone) +
                                    "§7/§d" + player.getFairySoulHandler().getMax(fairySoul.zone)).build())
                    .build());
        }
    }

    @Override
    public boolean allowHotkeying() {
        return false;
    }

    @Override
    public void onClose(InventoryCloseEvent event, CloseReason reason) {}

    @Override
    public void onBottomClick(InventoryPreClickEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void onSuddenQuit(SkyBlockPlayer player) {}
}