package net.swofty.type.hub.gui.builder;

import net.swofty.commons.item.ItemType;
import net.swofty.types.generic.gui.inventory.SkyBlockShopGUI;
import net.swofty.types.generic.gui.inventory.shop.ShopItem;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.shop.type.CoinShopPrice;

public class GUIShopBuilderGreenThumb extends SkyBlockShopGUI {
    public GUIShopBuilderGreenThumb() {
        super("Green Thumb", 1, GREENTHUMB);
    }

    @Override
    public void initializeShopItems() {
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OAK_LEAVES), 1, new CoinShopPrice(4)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.SPRUCE_LEAVES), 1, new CoinShopPrice(4)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.BIRCH_LEAVES), 1, new CoinShopPrice(4)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.JUNGLE_LEAVES), 1, new CoinShopPrice(4)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.DARK_OAK_LEAVES), 1, new CoinShopPrice(4)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.ACACIA_LEAVES), 1, new CoinShopPrice(4)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.SHORT_GRASS), 1, new CoinShopPrice(4)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.TALL_GRASS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.FLOWER_POT), 1, new CoinShopPrice(2)));

        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OAK_SAPLING), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.SPRUCE_SAPLING), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.BIRCH_SAPLING), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.JUNGLE_SAPLING), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.DARK_OAK_SAPLING), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.ACACIA_SAPLING), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.FERN), 1, new CoinShopPrice(4)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.LARGE_FERN), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.DEAD_BUSH), 1, new CoinShopPrice(4)));

        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.DANDELION), 1, new CoinShopPrice(50)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.POPPY), 1, new CoinShopPrice(50)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.BLUE_ORCHID), 1, new CoinShopPrice(20)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.AZURE_BLUET), 1, new CoinShopPrice(20)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OXEYE_DAISY), 1, new CoinShopPrice(20)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.ALLIUM), 1, new CoinShopPrice(20)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.PEONY), 1, new CoinShopPrice(20)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.LILAC), 1, new CoinShopPrice(20)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.ROSE_BUSH), 1, new CoinShopPrice(20)));

        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.WHITE_TULIP), 1, new CoinShopPrice(20)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.PINK_TULIP), 1, new CoinShopPrice(20)));
        attachItem(ShopItem.Single(new SkyBlockItem(ItemType.WATER_BUCKET), 1, new CoinShopPrice(12)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.RED_TULIP), 1, new CoinShopPrice(20)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.ORANGE_TULIP), 1, new CoinShopPrice(20)));
    }
}
