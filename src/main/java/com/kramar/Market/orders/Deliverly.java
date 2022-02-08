package com.kramar.Market.orders;

public enum Deliverly {
    Need(0),
    NO(1);

    final int rawValue;

    Deliverly(int rawValue) {
        this.rawValue = rawValue;
    }

    static Deliverly getCase(int rawValue) {
        switch (rawValue) {
            case 0:
                return Need;
            case 1:
                return NO;
            default:
                return NO;
        }
    }
}
