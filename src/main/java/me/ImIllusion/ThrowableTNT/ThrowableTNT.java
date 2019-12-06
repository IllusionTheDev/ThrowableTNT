package me.ImIllusion.ThrowableTNT;

import lombok.Getter;
import me.ImIllusion.ThrowableTNT.Data.ShiftAction;
import me.ImIllusion.ThrowableTNT.Listeners.BlockPlace;
import me.ImIllusion.ThrowableTNT.Listeners.ExplosionListener;
import me.ImIllusion.ThrowableTNT.Listeners.ThrowListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ThrowableTNT extends JavaPlugin {

    @Getter
    private boolean fire;
    @Getter
    private int power;
    @Getter
    private List<Action> actions = new ArrayList<>();
    @Getter
    private double speed;
    @Getter
    private double height;
    @Getter
    private boolean removeItem;
    @Getter
    private ShiftAction action;
    @Getter
    private List<Material> allowInteraction = new ArrayList<>();
    @Getter
    private String permission;


    private static ThrowableTNT instance;

    public static ThrowableTNT getInstance()
    {
        return instance == null ? instance = JavaPlugin.getPlugin(ThrowableTNT.class) : instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        fire       = getConfig().getBoolean("fire"       , false);
        power      = getConfig().getInt    ("power"      , -1   );
        speed      = getConfig().getDouble ("speed"                 );
        height     = getConfig().getDouble ("height"                );
        removeItem = getConfig().getBoolean("remove-item", true);
        permission = getConfig().getString("permission"  , "");

        action = ShiftAction.valueOf(getConfig().getString("shift-action", "NOTHING"));

        for(String s : getConfig().getStringList("actions"))
            actions.add(Action.valueOf(s));
        for(String s : getConfig().getStringList("allow-interaction"))
            allowInteraction.add(Material.valueOf(s));

        Bukkit.getPluginManager().registerEvents(new ExplosionListener(), this);
        Bukkit.getPluginManager().registerEvents(new ThrowListener()    , this);
        Bukkit.getPluginManager().registerEvents(new BlockPlace()       , this);

        getLogger().info("ThrowableTNT - Loaded.");
    }

}
