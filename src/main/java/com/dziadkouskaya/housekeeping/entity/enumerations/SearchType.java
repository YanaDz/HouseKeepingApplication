package com.dziadkouskaya.housekeeping.entity.enumerations;

public enum SearchType {

    START_WITH,
    END_WITH,
    ANY_MATCH,
    COMPLETE_MATCH;

    public String format(String token) {
        return switch (this) {
            case START_WITH -> token + "%";
            case END_WITH -> "%" + token;
            case ANY_MATCH -> "%" + token + "%";
            case COMPLETE_MATCH -> token;
        };
    }
}
