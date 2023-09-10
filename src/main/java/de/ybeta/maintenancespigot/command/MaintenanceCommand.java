package de.ybeta.maintenancespigot.command;

import de.ybeta.maintenancespigot.MaintenancePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MaintenanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String l, String[] args) {
        if (!sender.hasPermission(MaintenancePlugin.getInstance().getTogglePermission())) {
            sender.sendMessage("§cNo Permission!");
            return false;
        }
        if (args.length != 1) {
            sender.sendMessage("§cUsage: /maintenance <on/off/status/reload>");
            return false;
        }
        if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable")) {
            MaintenancePlugin.getInstance().setMaintenanceMode(true);
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (!player.hasPermission(MaintenancePlugin.getInstance().getJoinPermission()) && !player.isOp()) {
                    player.kickPlayer(MaintenancePlugin.getInstance().getDisconnectMessage());
                }
            });
            sender.sendMessage("§7Maintenance Mode has been §aenabled§7.");
        } else if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable")) {
            MaintenancePlugin.getInstance().setMaintenanceMode(false);
            sender.sendMessage("§7Maintenance Mode has been §cdisabled§7.");
        } else if (args[0].equalsIgnoreCase("status") || args[0].equalsIgnoreCase("check")) {
            boolean maintenanceMode = MaintenancePlugin.getInstance().isMaintenanceMode();
            if (maintenanceMode) {
                sender.sendMessage("§7Server Maintenance: §aEnabled");
            } else {
                sender.sendMessage("§7Server Maintenance: §cDisabled");
            }
        } else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
            MaintenancePlugin.getInstance().reload();
            sender.sendMessage("§7The plugin has been reloaded.");
        } else {
            sender.sendMessage("§cUsage: /maintenance <on/off/status/reload>");
        }
        return false;
    }
}
