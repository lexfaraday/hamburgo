package test.backend.www.model.hotelbeds.sdk.types;

/**
 * Copyright (c) Hotelbeds Technology S.L.U.  All rights reserved.
 */
public enum FilterType {
    //E: Search for checkin date
    CHECKIN("E"),
    //C: Search for creation date
    CREATION("C");

    private String type;

    FilterType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
