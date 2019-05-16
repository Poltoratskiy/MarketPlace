package ru.marketplace.entity.enums;

public enum GenderEnum {
    /**
     * Male.
     */
    M("Male"),
    /**
     * Female.
     */
    F("Female"),
    /**
     * GenderEnum is not known, or not specified.
     */
    UNKNOWN("Unknown");
    private final String name;

    private GenderEnum(String name) {
        this.name = name;
    }

    /**
     * @return The string representation of this element in the enumeration.
     */
    public String getName() {
        return this.name;
    }

    public GenderEnum getEnumGenderFromString(String s) {
        switch (s) {
            case ("M"):
                return GenderEnum.M;
            case ("F"):
                return GenderEnum.F;
        }

        return GenderEnum.UNKNOWN;
    }

}
