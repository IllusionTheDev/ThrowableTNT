package me.ImIllusion.ThrowableTNT.Listeners;

import me.ImIllusion.ThrowableTNT.Data.ShiftAction;
import me.ImIllusion.ThrowableTNT.ThrowableTNT;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler
    private void onBlockPlace(BlockPlaceEvent e)
    {
        if(e.getBlock().getType() == Material.TNT)
        {
            if(ThrowableTNT.getInstance().getAction() == ShiftAction.NOTHING)
                e.setCancelled(true);
            if(ThrowableTNT.getInstance().getAction() == ShiftAction.PLACE_BLOCK)
                e.setCancelled(!e.getPlayer().isSneaking());
            if(ThrowableTNT.getInstance().getAction() == ShiftAction.THROW)
                e.setCancelled(e.getPlayer().isSneaking());
        }
    }
}
