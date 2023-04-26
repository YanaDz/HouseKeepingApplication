package com.dziadkouskaya.housekeeping.entity.enumerations;

public enum FlatType implements BaseEnum<FlatType>{
    PERSONAL(1),
    COMMERCIAL(2);

    private final int code;

    FlatType(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    public static class Converter extends BaseConverter<FlatType> {

        public Converter() {
            super(FlatType.class);
        }
    }
}
