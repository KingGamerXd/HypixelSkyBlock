package net.swofty.types.generic.item.items.combat.slayer.enderman.craftable;

import net.swofty.types.generic.item.ItemTypeLinker;
import net.swofty.types.generic.item.ItemQuantifiable;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.*;
import net.swofty.types.generic.item.impl.recipes.ShapedRecipe;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.commons.statistics.ItemStatistics;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TessellatedEnderPearl implements CustomSkyBlockItem, SkullHead, DefaultCraftable, Sellable, TrackedUniqueItem {
    @Override
    public SkyBlockRecipe<?> getRecipe() {
        Map<Character, ItemQuantifiable> ingredientMap = new HashMap<>();
        ingredientMap.put('A', new ItemQuantifiable(ItemTypeLinker.ENCHANTED_LAPIS_LAZULI_BLOCK, 8));
        ingredientMap.put('B', new ItemQuantifiable(ItemTypeLinker.ABSOLUTE_ENDER_PEARL, 16));
        List<String> pattern = List.of(
                "ABA",
                "BBB",
                "ABA");

        return new ShapedRecipe(SkyBlockRecipe.RecipeType.VOIDGLOOM_SERAPH, new SkyBlockItem(ItemTypeLinker.TESSELLATED_ENDER_PEARL), ingredientMap, pattern);
    }

    @Override
    public ItemStatistics getStatistics(SkyBlockItem instance) {
        return ItemStatistics.empty();
    }

    @Override
    public double getSellValue() {
        return 96000;
    }

    @Override
    public String getSkullTexture(@Nullable SkyBlockPlayer player, SkyBlockItem item) {
        return "38be8abd66d09a58ce12d377544d726d25cad7e979e8c2481866be94d3b32f";
    }
}
