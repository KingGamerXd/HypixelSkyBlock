package net.swofty.type.hub.gui.builder;

import net.swofty.commons.item.ItemType;
import net.swofty.types.generic.gui.inventory.SkyBlockShopGUI;
import net.swofty.types.generic.gui.inventory.shop.ShopItem;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.shop.type.CoinShopPrice;

public class GUIShopBuilderWoodworking extends SkyBlockShopGUI {
    public GUIShopBuilderWoodworking() {
        super("Woodworking", 1, UPPER5ROWS);
    }

    @Override
    public void initializeShopItems() {
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OAK_PLANKS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.SPRUCE_PLANKS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.BIRCH_PLANKS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.JUNGLE_PLANKS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.DARK_OAK_PLANKS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.ACACIA_PLANKS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OAK_DOOR), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.SPRUCE_DOOR), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.BIRCH_DOOR), 1, new CoinShopPrice(2)));

        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OAK_SLAB), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.SPRUCE_SLAB), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.BIRCH_SLAB), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.JUNGLE_SLAB), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.DARK_OAK_SLAB), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.ACACIA_SLAB), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.JUNGLE_DOOR), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.ACACIA_DOOR), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.SPRUCE_DOOR), 1, new CoinShopPrice(2)));

        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OAK_STAIRS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.SPRUCE_STAIRS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.BIRCH_STAIRS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.JUNGLE_STAIRS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.DARK_OAK_STAIRS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.ACACIA_STAIRS), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OAK_TRAPDOOR), 1, new CoinShopPrice(4)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.TORCH), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OAK_BUTTON), 1, new CoinShopPrice(2)));

        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OAK_FENCE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.SPRUCE_FENCE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.BIRCH_FENCE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.JUNGLE_FENCE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.DARK_OAK_FENCE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.ACACIA_FENCE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OAK_SIGN), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.LADDER), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OAK_PRESSURE_PLATE), 1, new CoinShopPrice(2)));

        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.OAK_FENCE_GATE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.SPRUCE_FENCE_GATE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.BIRCH_FENCE_GATE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.JUNGLE_FENCE_GATE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.DARK_OAK_FENCE_GATE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.ACACIA_FENCE_GATE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.CRAFTING_TABLE), 1, new CoinShopPrice(2)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.CHEST), 1, new CoinShopPrice(4)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.BOOKSHELF), 1, new CoinShopPrice(100)));
    }
}
