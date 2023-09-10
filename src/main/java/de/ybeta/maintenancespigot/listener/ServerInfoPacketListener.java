package de.ybeta.maintenancespigot.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import de.ybeta.maintenancespigot.MaintenancePlugin;
import de.ybeta.maintenancespigot.entry.MotdEntry;
import org.bukkit.Bukkit;

public class ServerInfoPacketListener extends PacketAdapter {

    private MaintenancePlugin plugin;

    public ServerInfoPacketListener(MaintenancePlugin plugin) {
        super(plugin, ListenerPriority.HIGH, PacketType.Status.Server.SERVER_INFO);
        this.plugin = plugin;
    }

    @Override
    public void onPacketSending(PacketEvent packetEvent) {
        PacketContainer packet = packetEvent.getPacket();
        MotdEntry motdEntry = plugin.getMotdEntry();

        WrappedServerPing ping = packet.getServerPings().read(0);
        ping.setVersionProtocol(1);

        if (plugin.isMaintenanceMode()) {
            ping.setMotD(replaceVars(motdEntry.getMaintenance1() + "\n" + motdEntry.getMaintenance2(), packetEvent));
            ping.setVersionName(replaceVars(motdEntry.getMaintenanceVersionName(), packetEvent));
        } else {
            ping.setMotD(replaceVars(motdEntry.getDefault1() + "\n" + motdEntry.getDefault2(), packetEvent));
            ping.setVersionName(replaceVars(motdEntry.getDefaultVersionName(), packetEvent));
        }
        packet.getServerPings().write(0, ping);
    }

    private String replaceVars(String text, PacketEvent packetEvent) {
        return text.replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%max_online%", String.valueOf(Bukkit.getMaxPlayers()))
                .replace("%player%", packetEvent.getPlayer().getName());
    }

}
