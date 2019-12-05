package me.ImIllusion.ThrowableTNT.Listeners;

import me.ImIllusion.ThrowableTNT.Data.ShiftAction;
import me.ImIllusion.ThrowableTNT.ThrowableTNT;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ThrowListener implements Listener {

    @EventHandler
    private void onThrow(PlayerInteractEvent e)
    {
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && ThrowableTNT.getInstance().getAllowInteraction().contains(e.getClickedBlock().getType()))
            return;
        if(e.getItem() != null && e.getItem().getType() == Material.TNT)
        {
            ThrowableTNT instance = ThrowableTNT.getInstance();
            if(instance.getActions().contains(e.getAction()))
            {
                if(instance.getAction() == ShiftAction.THROW && !e.getPlayer().isSneaking())
                    return;
                if(instance.getAction() == ShiftAction.PLACE_BLOCK && e.getPlayer().isSneaking())
                    return;
                Location loc = e.getPlayer().getLocation().add(0, instance.getHeight(), 0);

                TNTPrimed tnt = loc.getWorld().spawn(loc, TNTPrimed.class);
                tnt.setIsIncendiary(instance.isFire());
                tnt.setVelocity(tnt.getVelocity().add(e.getPlayer().getEyeLocation().getDirection()).multiply(instance.getSpeed()));
                tnt.setCustomName("TNTPrimed - " + instance.getPower());

                if(instance.isRemoveItem())
                {
                    e.getItem().setAmount(e.getItem().getAmount() - 1);
                }
            }
        }
    }


}
