package net.swofty.types.generic.item.items.minion.mining;

import net.swofty.types.generic.item.ItemTypeLinker;
import net.swofty.types.generic.item.impl.CustomSkyBlockItem;
import net.swofty.types.generic.item.impl.Minion;
import net.swofty.types.generic.minion.MinionIngredient;
import net.swofty.types.generic.minion.MinionRegistry;

import java.util.List;

public class IceMinion implements CustomSkyBlockItem, Minion {
    @Override
    public MinionRegistry getMinionRegistry() {
        return MinionRegistry.ICE;
    }

    @Override
    public ItemTypeLinker getFirstBaseItem() {
        return ItemTypeLinker.WOODEN_PICKAXE;
    }

    @Override
    public boolean isByDefaultCraftable() {
        return false;
    }

    @Override
    public List<MinionIngredient> getMinionCraftingIngredients() {
        return List.of(
                new MinionIngredient(ItemTypeLinker.ICE, 10),
                new MinionIngredient(ItemTypeLinker.ICE, 20),
                new MinionIngredient(ItemTypeLinker.ICE, 40),
                new MinionIngredient(ItemTypeLinker.ICE, 64),
                new MinionIngredient(ItemTypeLinker.PACKED_ICE, 16),
                new MinionIngredient(ItemTypeLinker.PACKED_ICE, 32),
                new MinionIngredient(ItemTypeLinker.PACKED_ICE, 64),
                new MinionIngredient(ItemTypeLinker.ENCHANTED_ICE, 8),
                new MinionIngredient(ItemTypeLinker.ENCHANTED_ICE, 16),
                new MinionIngredient(ItemTypeLinker.ENCHANTED_ICE, 32),
                new MinionIngredient(ItemTypeLinker.ENCHANTED_ICE, 64)
        );
    }
}
