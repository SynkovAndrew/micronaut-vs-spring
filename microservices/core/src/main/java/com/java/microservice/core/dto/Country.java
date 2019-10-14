package com.java.microservice.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Country {
    RUSSIA("RUSSIA"),
    ENGLAND("ENGLAND"),
    FRANCE("FRANCE"),
    USA("USA"),
    ESTONIA("ESTONIA"),
    SWEDEN("SWEDEN"),
    UKRAINE("UKRAINE"),
    ZAMBIA("ZAMBIA"),
    TURKEY("TURKEY"),
    GERMANY("GERMANY");

    private final static Map<String, Country> ENUM_CODE_MAP;

    static {
        ENUM_CODE_MAP = Arrays.stream(Country.values())
                .collect(Collectors.toMap(
                        Country::getCode,
                        Function.identity()));
    }

    private final String code;

    Country(String code) {
        this.code = code;
    }

    @JsonCreator
    public static Country fromCode(String code) {
        return ENUM_CODE_MAP.get(code);
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
