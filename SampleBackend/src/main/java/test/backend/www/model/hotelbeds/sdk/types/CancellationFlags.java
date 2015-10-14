package test.backend.www.model.hotelbeds.sdk.types;

/**
 * Copyright (c) Hotelbeds Technology S.L.U.  All rights reserved.
 */
public enum CancellationFlags {
    /* CANCELLATION: Used for canceling the booking */
    CANCELLATION("C"),
    /* SIMULATION : Used for a booking cancellation sumulation. */
    SIMULATION("S");

    private String flag;

    CancellationFlags(final String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public static CancellationFlags getCancellationFlag(final String value) {
        if (value != null && value.equalsIgnoreCase(CancellationFlags.SIMULATION.name())) {
            return CancellationFlags.SIMULATION;
        } else {
            return CancellationFlags.CANCELLATION;
        }
    }

}

