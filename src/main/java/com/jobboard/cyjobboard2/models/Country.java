package com.jobboard.cyjobboard2.models;

public enum Country {
    USA("United States"),
    CANADA("Canada"),
    UK("United Kingdom"),
    JAPAN("Japan");
    // can later add more countries as needed

    private final String displayName;

    Country(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Country findByName(String name) {
        for (Country country : values()) {
            if (country.name().equalsIgnoreCase(name)) {
                return country;
            }
        }
        return null;
    }
}
