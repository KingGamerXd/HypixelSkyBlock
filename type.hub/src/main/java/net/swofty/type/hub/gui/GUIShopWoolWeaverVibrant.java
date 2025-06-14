package net.swofty.type.hub.gui;

import net.kyori.adventure.text.Component;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.swofty.types.generic.gui.inventory.GUIItem;
import net.swofty.types.generic.gui.inventory.ItemStackCreator;
import net.swofty.types.generic.gui.inventory.SkyBlockShopGUI;
import net.swofty.types.generic.gui.inventory.shop.ShopItem;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.shop.type.CoinShopPrice;
import net.swofty.types.generic.user.SkyBlockPlayer;

public class GUIShopWoolWeaverVibrant extends SkyBlockShopGUI {
    public GUIShopWoolWeaverVibrant() {
        super("Wool Weaver (Vibrant)", 1, WOOLWEAVER_VIBRANT);
    }

    @Override
    public void handleOpen(SkyBlockPlayer player) {
        super.handleOpen(player);

        attachItem(GUIItem.builder(53)
                .item(ItemStackCreator.createNamedItemStack(Material.ARROW, "§a->").build())
                .onClick((ctx, item) -> {
                    ctx.player().openInventory(new GUIShopWoolWeaverCool());
                    return true;
                })
                .build());
    }

    @Override
    protected void initializeShopItems() {
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.PURPLE_TERRACOTTA), 1, new CoinShopPrice(8)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.MAGENTA_TERRACOTTA), 1, new CoinShopPrice(8)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.PINK_TERRACOTTA), 1, new CoinShopPrice(8)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.RED_TERRACOTTA), 1, new CoinShopPrice(8)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.ORANGE_TERRACOTTA), 1, new CoinShopPrice(8)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.YELLOW_TERRACOTTA), 1, new CoinShopPrice(8)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.LIME_TERRACOTTA), 1, new CoinShopPrice(8)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.GREEN_TERRACOTTA), 1, new CoinShopPrice(8)));

        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.PURPLE_STAINED_GLASS_PANE), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.MAGENTA_STAINED_GLASS_PANE), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.PINK_STAINED_GLASS_PANE), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.RED_STAINED_GLASS_PANE), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.ORANGE_STAINED_GLASS_PANE), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.YELLOW_STAINED_GLASS_PANE), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.LIME_STAINED_GLASS_PANE), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.GREEN_STAINED_GLASS_PANE), 1, new CoinShopPrice(16)));

        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.PURPLE_STAINED_GLASS), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.MAGENTA_STAINED_GLASS), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.PINK_STAINED_GLASS), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.RED_STAINED_GLASS), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.ORANGE_STAINED_GLASS), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.YELLOW_STAINED_GLASS), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.LIME_STAINED_GLASS), 1, new CoinShopPrice(16)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.GREEN_STAINED_GLASS), 1, new CoinShopPrice(16)));

        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.PURPLE_CARPET), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.MAGENTA_CARPET), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.PINK_CARPET), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.RED_CARPET), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.ORANGE_CARPET), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.YELLOW_CARPET), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.LIME_CARPET), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.GREEN_CARPET), 1, new CoinShopPrice(32)));

        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.PURPLE_WOOL), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.MAGENTA_WOOL), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.PINK_WOOL), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.RED_WOOL), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.ORANGE_WOOL), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.YELLOW_WOOL), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.LIME_WOOL), 1, new CoinShopPrice(32)));
        attachShopItem(ShopItem.Stackable(new SkyBlockItem(Material.GREEN_WOOL), 1, new CoinShopPrice(32)));
    }
}