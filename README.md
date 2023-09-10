# SimpleMaintenance-Spigot
Maintenance + MOTD Plugin for Bukkit/Spigot.
WARNING: This is an old project and officially discontinued.

## Information & Features
- ProtocolLib is required!
- Java 17 is required!
- Plugin was tested with 1.20
- MotD
- PlayerCountMessagee (VersionName)
- Maintenance System

## Default Configuration
```yaml
# Weather the Server is currently in maintenance mode or not.
MaintenanceStatus: false

# The message when the player gets kicked when connecting to the server while in maintenance mode.
DisconnectMessage: "&cThe Server is currently undergoing maintenance."

#---------------------------------------------------
#|                   Permissions                   |
#---------------------------------------------------
# Permission to join while server is in maintenance
JoinPermission: "maintenance.join"
# Permission to toggle Maintenance Mode
TogglePermission: "maintenance.toggle"

#---------------------------------------------------
#|                      MotD                       |
#---------------------------------------------------
# Line1 - First line of MotD
# Line2 - Second line of MotD
# PlayerCountMessage - Custom Text where the player count normally is.
# Placeholders:
# %online% - Online Players
# %max_online% - Max. Online Players
# %player% - Name of the player
Default:
  Line1: "&aServer.net &8- &e1.20.1 Server"
  Line2: "&b&lJOIN NOW! &e&lNEW: Survival"
  PlayerCountMessage: "&a%online%&8/&7%max_online%"
Maintenance:
  Line1: "&aServer.net &8- &e1.20.1 Survival"
  Line2: "&cCurrently in Maintenance..."
  PlayerCountMessage: "&cMaintenance Mode"

```
