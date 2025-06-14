package net.swofty.type.hub.gui;

import net.kyori.adventure.text.Component;
import net.swofty.commons.item.ItemType;
import net.swofty.types.generic.gui.inventory.SkyBlockShopGUI;
import net.swofty.types.generic.gui.inventory.shop.ShopItem;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.shop.type.CoinShopPrice;

public class GUIShopZog extends SkyBlockShopGUI {
    public GUIShopZog() {
        super("Zog", 1, DEFAULT);
    }

    @Override
    protected void initializeShopItems() {
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.ALL_SKILLS_EXP_BOOST), 1, new CoinShopPrice(50000)));
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.MINING_EXP_BOOST_COMMON), 1, new CoinShopPrice(60000)));
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.FARMING_EXP_BOOST_COMMON), 1, new CoinShopPrice(60000)));
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.FISHING_EXP_BOOST), 1, new CoinShopPrice(60000)));
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.COMBAT_EXP_BOOST), 1, new CoinShopPrice(60000)));
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.FORAGING_EXP_BOOST), 1, new CoinShopPrice(60000)));
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.MINING_EXP_BOOST_RARE), 1, new CoinShopPrice(500000)));
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.FARMING_EXP_BOOST_RARE), 1, new CoinShopPrice(500000)));
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.BIG_TEETH), 1, new CoinShopPrice(750000)));
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.IRON_CLAWS), 1, new CoinShopPrice(750000)));
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.HARDENED_SCALES), 1, new CoinShopPrice(1000000)));
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.SHARPENED_CLAWS), 1, new CoinShopPrice(1000000)));
        attachShopItem(ShopItem.Single(new SkyBlockItem(ItemType.BUBBLEGUM), 1, new CoinShopPrice(5000000)));
    }
}