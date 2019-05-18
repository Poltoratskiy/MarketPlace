package ru.marketplace.entity.enums;

public enum CurrencyEnum {
    /**
     * Ruble.
     */
    RUB("Ruble"),
    /**
     * Dollar.
     */
    USD("US Dollar");
    private final String name;

    private CurrencyEnum(String name) {
        this.name = name;
    }

    /**
     * @return The string representation of this element in the enumeration.
     */
    public String getName() {
        return this.name;
    }

}
