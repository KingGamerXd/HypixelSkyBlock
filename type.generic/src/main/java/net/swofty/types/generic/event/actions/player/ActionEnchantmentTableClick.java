package net.swofty.types.generic.event.actions.player;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.Event;
import net.minestom.server.event.player.PlayerBlockInteractEvent;
import net.minestom.server.item.Material;
import net.swofty.types.generic.event.EventNodes;
import net.swofty.types.generic.event.SkyBlockEventClass;
import net.swofty.types.generic.gui.inventory.inventories.GUIEnchantmentTable;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.types.generic.event.SkyBlockEvent;

public class ActionEnchantmentTableClick implements SkyBlockEventClass {

    @SkyBlockEvent(node = EventNodes.PLAYER , requireDataLoaded = true)
    public void run(PlayerBlockInteractEvent event) {
        final SkyBlockPlayer player = (SkyBlockPlayer) event.getPlayer();

        if (Material.fromKey(event.getBlock().key()) != Material.ENCHANTING_TABLE) {
            return;
        }

        event.setBlockingItemUse(true);

        player.openInventory(new GUIEnchantmentTable(player.getInstance(), Pos.fromPoint(event.getBlockPosition())));
    }
}

