package com.kramar.Market.orders;

public enum Payment {
    ONLINE(0),
    MONEY(1);

    final int rawValue;

    Payment(int rawValue) {
        this.rawValue = rawValue;
    }

    static Payment getCase(int rawValue) {
        switch (rawValue) {
            case 0:
                return ONLINE;
            case 1:
                return MONEY;
            default:
                return MONEY;
        }
    }
}
