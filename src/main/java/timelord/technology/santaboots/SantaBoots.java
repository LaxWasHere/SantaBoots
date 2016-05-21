package timelord.technology.santaboots;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Lax on 5/21/2016.
 */
public class SantaBoots extends JavaPlugin implements Listener {

    public ArrayList<UUID> reindeers = new ArrayList<>();
    PluginDescriptionFile pdf;

    public void onEnable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " [disable]");
        getCommand("nofall").setExecutor(new NorthPoleCommand(this));
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " [disabled]");
    }

    @EventHandler
    public void onFall(EntityDamageEvent ev) {
        if (ev.getCause() == EntityDamageEvent.DamageCause.FALL && ev.getEntity() instanceof Player) {
            if (reindeers.contains(((Player) ev.getEntity()).getPlayer().getUniqueId())) ev.setCancelled(true);
        }
    }

}
