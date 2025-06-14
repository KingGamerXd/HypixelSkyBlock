package net.swofty.type.hub.gui;

import net.minestom.server.item.Material;
import net.swofty.commons.item.ItemType;
import net.swofty.types.generic.gui.inventory.SkyBlockShopGUI;
import net.swofty.types.generic.gui.inventory.shop.ShopItem;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.shop.type.CoinShopPrice;

public class GUIShopLumberMerchant extends SkyBlockShopGUI {
    public GUIShopLumberMerchant() {
        super("Lumber Merchant", 1, DEFAULT);
    }

    @Override
    public void initializeShopItems() {
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.OAK_LOG), 5, new CoinShopPrice(25)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.BIRCH_LOG), 5, new CoinShopPrice(25)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.SPRUCE_LOG), 5, new CoinShopPrice(25)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.DARK_OAK_LOG), 5, new CoinShopPrice(25)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.ACACIA_LOG), 5, new CoinShopPrice(25)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.JUNGLE_LOG), 5, new CoinShopPrice(25)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.STICK), 32, new CoinShopPrice(20)));
        attachItem(ShopItem.Single(new SkyBlockItem(ItemType.ROOKIE_AXE), 1, new CoinShopPrice(12)));
        attachItem(ShopItem.Single(new SkyBlockItem(ItemType.PROMISING_AXE), 1, new CoinShopPrice(35)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.PODZOL), 1, new CoinShopPrice(20)));
        attachItem(ShopItem.Single(new SkyBlockItem(ItemType.SWEET_AXE), 1, new CoinShopPrice(100)));
        attachItem(ShopItem.Single(new SkyBlockItem(ItemType.EFFICIENT_AXE), 1, new CoinShopPrice(100)));
        attachItem(ShopItem.Single(new SkyBlockItem(ItemType.WOODEN_SWORD), 1, new CoinShopPrice(5)));
        attachItem(ShopItem.Single(new SkyBlockItem(ItemType.WOODEN_PICKAXE), 1, new CoinShopPrice(5)));
        attachItem(ShopItem.Single(new SkyBlockItem(ItemType.WOODEN_SHOVEL), 1, new CoinShopPrice(5)));
        attachItem(ShopItem.Single(new SkyBlockItem(ItemType.WOODEN_HOE), 1, new CoinShopPrice(5)));
        attachItem(ShopItem.Single(new SkyBlockItem(ItemType.WOODEN_AXE), 1, new CoinShopPrice(5)));
    }
}
