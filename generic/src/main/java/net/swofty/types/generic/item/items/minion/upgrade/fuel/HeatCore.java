package net.swofty.types.generic.item.items.minion.upgrade.fuel;

import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.CustomSkyBlockItem;
import net.swofty.types.generic.item.impl.NotFinishedYet;
import net.swofty.types.generic.item.impl.SkullHead;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.types.generic.user.statistics.ItemStatistics;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HeatCore implements CustomSkyBlockItem, SkullHead, NotFinishedYet {
    @Override
    public List<String> getLore(SkyBlockPlayer player, SkyBlockItem item) {
        return List.of("§7Craft with §5Enchanted Lava",
                "§5Buckets§7 to upgrade the minion",
                "§7speed bonus to §a30%§7 and",
                "§7§a30%§7.");
    }

    @Override
    public ItemStatistics getStatistics(SkyBlockItem instance) {
        return ItemStatistics.empty();
    }

    @Override
    public String getSkullTexture(@Nullable SkyBlockPlayer player, SkyBlockItem item) {
        return null;
    }
}