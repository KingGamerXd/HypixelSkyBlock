package net.swofty.types.generic.item.items.travelscroll;

import net.swofty.types.generic.item.ItemTypeLinker;
import net.swofty.types.generic.item.ItemQuantifiable;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.DefaultCraftable;
import net.swofty.types.generic.item.impl.SkyBlockRecipe;
import net.swofty.types.generic.item.impl.TravelScrollItem;
import net.swofty.types.generic.item.impl.recipes.ShapedRecipe;
import net.swofty.types.generic.warps.TravelScrollType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HubCryptsTravelScroll implements TravelScrollItem, DefaultCraftable {
    @Override
    public TravelScrollType getTravelScrollType() {
        return TravelScrollType.HUB_CRYPTS;
    }

    @Override
    public SkyBlockRecipe<?> getRecipe() {
        Map<Character, ItemQuantifiable> ingredientMap = new HashMap<>();
        ingredientMap.put('A', new ItemQuantifiable(ItemTypeLinker.REVENANT_VISCERA, 1));
        ingredientMap.put('B', new ItemQuantifiable(ItemTypeLinker.ENCHANTED_ENDER_PEARL, 16));
        ingredientMap.put('C', new ItemQuantifiable(ItemTypeLinker.ENCHANTED_OBSIDIAN, 16));
        List<String> pattern = List.of(
                "AAA",
                "CBC",
                "CCC"
        );
        return new ShapedRecipe(SkyBlockRecipe.RecipeType.SLAYER, new SkyBlockItem(ItemTypeLinker.HUB_CRYPT_TRAVEL_SCROLL), ingredientMap, pattern);
    }
}
