package net.swofty.types.generic.item.items.combat.slayer.spider.craftable;

import net.swofty.types.generic.item.ItemTypeLinker;
import net.swofty.types.generic.item.ItemQuantifiable;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.*;
import net.swofty.types.generic.item.impl.recipes.ShapedRecipe;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.commons.statistics.ItemStatistic;
import net.swofty.commons.statistics.ItemStatistics;

import java.util.*;
import net.swofty.commons.item.ItemType;

public class MosquitoBow implements CustomSkyBlockItem, DefaultCraftable, BowImpl, NotFinishedYet {
    @Override
    public SkyBlockRecipe<?> getRecipe() {
        Map<Character, ItemQuantifiable> ingredientMap = new HashMap<>();
        ingredientMap.put('A', new ItemQuantifiable(ItemTypeLinker.ENCHANTED_IRON_INGOT, 64));
        ingredientMap.put('B', new ItemQuantifiable(ItemTypeLinker.TARANTULA_SILK, 64));
        ingredientMap.put('C', new ItemQuantifiable(ItemTypeLinker.DIGESTED_MOSQUITO, 1));
        ingredientMap.put(' ', new ItemQuantifiable(ItemType.AIR, 1));
        List<String> pattern = List.of(
                " AB",
                "C B",
                " AB");

        return new ShapedRecipe(SkyBlockRecipe.RecipeType.TARANTULA_BROODFATHER, new SkyBlockItem(ItemTypeLinker.MOSQUITO_BOW), ingredientMap, pattern);
    }

    @Override
    public ItemStatistics getStatistics(SkyBlockItem instance) {
        return ItemStatistics.builder()
                .withBase(ItemStatistic.DAMAGE, 251D)
                .withBase(ItemStatistic.STRENGTH, 151D)
                .withBase(ItemStatistic.CRIT_DAMAGE, 39D)
                .withBase(ItemStatistic.VITALITY, 20D)
                .build();
    }

    @Override
    public ArrayList<String> getLore(SkyBlockPlayer player, SkyBlockItem item) {
        return new ArrayList<>(Arrays.asList(
                "§6Ability: Nasty Bite",
                "§8Fully charged shots while sneaking",
                "§7Costs §b11% §7of max mana.",
                "§7Deal §c+19% §7damage.",
                "§7Heal for §a2x §7the mana cost."));
    }

    @Override
    public boolean shouldBeArrow() {
        return false;
    }

    @Override
    public void onBowShoot(SkyBlockPlayer player, SkyBlockItem item) {}
}
