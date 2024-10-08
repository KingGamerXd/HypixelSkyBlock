package net.swofty.types.generic.item.items.combat.slayer.wolf.craftable;

import net.minestom.server.event.player.PlayerItemAnimationEvent;
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

public class EdibleMace implements CustomSkyBlockItem, DefaultCraftable, StandardItem, DisableAnimationImpl , NotFinishedYet {
    @Override
    public SkyBlockRecipe<?> getRecipe() {
        Map<Character, ItemQuantifiable> ingredientMap = new HashMap<>();
        ingredientMap.put('A', new ItemQuantifiable(ItemTypeLinker.ENCHANTED_MUTTON, 9));
        ingredientMap.put('B', new ItemQuantifiable(ItemTypeLinker.GOLDEN_TOOTH, 18));
        ingredientMap.put(' ', new ItemQuantifiable(ItemType.AIR, 1));
        List<String> pattern = List.of(
                " A ",
                " A ",
                " B ");

        return new ShapedRecipe(SkyBlockRecipe.RecipeType.SVEN_PACKMASTER, new SkyBlockItem(ItemTypeLinker.EDIBLE_MACE), ingredientMap, pattern);
    }

    @Override
    public ItemStatistics getStatistics(SkyBlockItem instance) {
        return ItemStatistics.builder()
                .withBase(ItemStatistic.DAMAGE, 125D)
                .withBase(ItemStatistic.STRENGTH, 25D)
                .build();
    }

    @Override
    public ArrayList<String> getLore(SkyBlockPlayer player, SkyBlockItem item) {
        return new ArrayList<>(Arrays.asList(
                "§6Ability: ME SMASH HEAD §e§lRIGHT CLICK",
                "§7Your next attack deals §cdouble",
                "§cdamage §7and weakens animals,",
                "§7making them deal §c-35% §7damage",
                "§7for §a30 §7seconds.",
                "§7§8Debuff doesn\u0027t stack.",
                "§8Mana Cost: §3100"));
    }

    @Override
    public StandardItemType getStandardItemType() {
        return StandardItemType.SWORD;
    }

    @Override
    public List<PlayerItemAnimationEvent.ItemAnimationType> getDisabledAnimations() {
        return List.of(PlayerItemAnimationEvent.ItemAnimationType.EAT);
    }
}
