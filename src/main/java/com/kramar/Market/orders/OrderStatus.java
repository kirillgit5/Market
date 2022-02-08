package com.kramar.Market.orders;

public enum OrderStatus {
    New(0),
    CANCELED(1),
    PERFORMED(2),
    FINISHED(3);

    final int rawValue;

    OrderStatus(int rawValue) {
        this.rawValue = rawValue;
    }

    static OrderStatus getCase(int rawValue) {
        switch (rawValue) {
            case 0:
                return New;
            case 1:
                return CANCELED;
            case 2:
                return PERFORMED;
            case 3:
                return FINISHED;
            default:
                return New;
        }
    }
}
