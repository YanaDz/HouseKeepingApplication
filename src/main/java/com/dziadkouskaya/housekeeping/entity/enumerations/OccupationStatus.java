package com.dziadkouskaya.housekeeping.entity.enumerations;

public enum OccupationStatus implements BaseEnum<OccupationStatus>{
    ACTIVE(1),
    NON_ACTIVE(2),
    TEMPORARY_RETIRED(3);

    private final int code;

    OccupationStatus(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    public static class Converter extends BaseConverter<OccupationStatus> {

        public Converter() {
            super(OccupationStatus.class);
        }
    }


}
