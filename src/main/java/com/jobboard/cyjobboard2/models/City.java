package com.jobboard.cyjobboard2.models;

public enum City {
    NEW_YORK("New York"), //the displayName not showing in pgAdmin table; shows label name instead i.e. NEW_YORK
    TORONTO("Toronto"),
    LONDON("London"),
    TOKYO("Tokyo");

    // can later add more cities as needed

    private final String displayName;

    City(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    public static City findByName(String name) {
        for (City city : values()) {
            if (city.name().equalsIgnoreCase(name)) {
                return city;
            }
        }
        return null;
    }
}


