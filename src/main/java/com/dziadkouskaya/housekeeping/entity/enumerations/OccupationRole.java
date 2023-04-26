package com.dziadkouskaya.housekeeping.entity.enumerations;

public enum OccupationRole implements BaseEnum<OccupationRole> {
    OWNER(1),
    RESIDENT(2),
    RENTER(3);

    private final int code;

    OccupationRole(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    public static class Converter extends BaseConverter<OccupationRole> {

        public Converter() {
            super(OccupationRole.class);
        }
    }
}
