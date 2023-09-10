package de.ybeta.maintenancespigot.entry;

import sun.security.krb5.internal.KdcErrException;

public class MotdEntry {

    String default1;
    String default2;
    String maintenance1;
    String maintenance2;
    String defaultVersionName;
    String maintenanceVersionName;

    public MotdEntry(String default1, String default2, String maintenance1, String maintenance2, String defaultVersionName, String maintenanceVersionName) {
        this.default1 = default1;
        this.default2 = default2;
        this.maintenance1 = maintenance1;
        this.maintenance2 = maintenance2;
        this.defaultVersionName = defaultVersionName;
        this.maintenanceVersionName = maintenanceVersionName;
    }

    public String getDefault1() {
        return default1;
    }

    public String getDefault2() {
        return default2;
    }

    public String getMaintenance1() {
        return maintenance1;
    }

    public String getMaintenance2() {
        return maintenance2;
    }

    public String getDefaultVersionName() {
        return defaultVersionName;
    }

    public String getMaintenanceVersionName() {
        return maintenanceVersionName;
    }
}
