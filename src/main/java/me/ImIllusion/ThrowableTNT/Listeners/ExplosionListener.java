package me.ImIllusion.ThrowableTNT.Listeners;

import me.ImIllusion.ThrowableTNT.ThrowableTNT;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener {

    @EventHandler
    public void onExplode(EntityExplodeEvent e)
    {
        if(e.getEntity() instanceof TNTPrimed)
        {
            TNTPrimed tnt = (TNTPrimed) e.getEntity();

            if(tnt.getCustomName() != null && tnt.getCustomName().startsWith("TNTPrimed - ") && ThrowableTNT.getInstance().getPower() > -1)
            {
                e.setCancelled(true);
                e.getLocation().getWorld().createExplosion(e.getLocation(), ThrowableTNT.getInstance().getPower(), ThrowableTNT.getInstance().isFire());
            }
        }
    }
}
