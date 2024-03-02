package com.jobboard.cyjobboard2.models;

public enum JobType {
    FULL_TIME("Full-time"),
    PART_TIME("Part-time");

    private final String displayName;

    JobType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static JobType findByName(String name) {
        for (JobType type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}