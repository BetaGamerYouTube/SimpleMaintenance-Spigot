package de.ybeta.maintenancespigot;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import de.ybeta.maintenancespigot.command.MaintenanceCommand;
import de.ybeta.maintenancespigot.entry.MotdEntry;
import de.ybeta.maintenancespigot.listener.LoginHandler;
import de.ybeta.maintenancespigot.listener.ServerInfoPacketListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class MaintenancePlugin extends JavaPlugin {

    private boolean maintenanceMode;
    private String disconnectMessage;
    private String joinPermission;
    private String togglePermission;
    private MotdEntry motdEntry;
    private static MaintenancePlugin instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        saveResource("config.yml", false);
        reload();
        Bukkit.getPluginManager().registerEvents(new LoginHandler(), this);
        getCommand("maintenance").setExecutor(new MaintenanceCommand());
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new ServerInfoPacketListener(this));
    }

    @Override
    public void onDisable() {
        getConfig().set("MaintenanceStatus", maintenanceMode);
        saveConfig();
    }

    public void reload() {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
        maintenanceMode = config.getBoolean("MaintenanceStatus");
        disconnectMessage = ChatColor.translateAlternateColorCodes('&', config.getString("DisconnectMessage"));
        joinPermission = config.getString("JoinPermission");
        togglePermission = config.getString("TogglePermission");
        motdEntry = new MotdEntry(
                ChatColor.translateAlternateColorCodes('&', config.getString("Default.Line1")),
                ChatColor.translateAlternateColorCodes('&', config.getString("Default.Line2")),
                ChatColor.translateAlternateColorCodes('&', config.getString("Maintenance.Line1")),
                ChatColor.translateAlternateColorCodes('&', config.getString("Maintenance.Line2")),
                ChatColor.translateAlternateColorCodes('&', config.getString("Default.PlayerCountMessage")),
                ChatColor.translateAlternateColorCodes('&', config.getString("Maintenance.PlayerCountMessage")));
    }

    public void setMaintenanceMode(boolean bool) {
        maintenanceMode = bool;
    }

    public static MaintenancePlugin getInstance() {
        return instance;
    }

    public boolean isMaintenanceMode() {
        return maintenanceMode;
    }

    public String getDisconnectMessage() {
        return disconnectMessage;
    }

    public String getJoinPermission() {
        return joinPermission;
    }

    public String getTogglePermission() {
        return togglePermission;
    }

    public MotdEntry getMotdEntry() {
        return motdEntry;
    }
}
