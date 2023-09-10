package de.ybeta.maintenancespigot.listener;

import de.ybeta.maintenancespigot.MaintenancePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginHandler implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        if (MaintenancePlugin.getInstance().isMaintenanceMode()) {
            String permission = MaintenancePlugin.getInstance().getJoinPermission();
            if (!player.hasPermission(permission) && !player.isOp()) {
                String disconnectMessage = MaintenancePlugin.getInstance().getDisconnectMessage();
                event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, disconnectMessage);
            }
        }
    }

}
