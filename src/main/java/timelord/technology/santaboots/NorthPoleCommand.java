package timelord.technology.santaboots;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Lax on 5/21/2016.
 */
public class NorthPoleCommand implements CommandExecutor {

    SantaBoots santaBoots;

    public NorthPoleCommand(SantaBoots santaBoots) {
        this.santaBoots = santaBoots;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!sender.hasPermission("surprise.me")) {
            sender.sendMessage(ChatColor.RED + "You are on the naughty list!");
            return false;
        }
        Player p = (Player) sender;
        if (santaBoots.reindeers.contains(p.getUniqueId())) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',santaBoots.getConfig().getString("off")));
            santaBoots.reindeers.remove(p.getUniqueId());
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',santaBoots.getConfig().getString("on")));
            santaBoots.reindeers.add(p.getUniqueId());
        }
        return false;
    }
}
