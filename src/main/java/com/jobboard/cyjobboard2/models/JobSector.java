package com.jobboard.cyjobboard2.models;

public enum JobSector {
    IT("Information Technology"),
    FINANCE("Finance"),
    HEALTHCARE("Healthcare"),
    CONSTRUCTION("Construction");
    // Add more sectors as needed

    private final String displayName;

    JobSector(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static JobSector findByName(String name) {
        for (JobSector sector : values()) {
            if (sector.name().equalsIgnoreCase(name)) {
                return sector;
            }
        }
        return null; // one can also throw an exception if not found
    }
}