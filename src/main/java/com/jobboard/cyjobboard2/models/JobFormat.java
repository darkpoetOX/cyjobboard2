package com.jobboard.cyjobboard2.models;

public enum JobFormat {
    IN_PERSON("In-person"),
    REMOTE("Remote");

    private final String displayName;

    JobFormat(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static JobFormat findByName(String name) {
        for (JobFormat format : values()) {
            if (format.name().equalsIgnoreCase(name)) {
                return format;
            }
        }
        return null; //
    }

}
