package com.java.microservice.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    private final static Map<String, Gender> ENUM_CODE_MAP;

    static {
        ENUM_CODE_MAP = Arrays.stream(Gender.values())
                .collect(Collectors.toMap(
                        Gender::getCode,
                        Function.identity()));
    }

    private final String code;

    Gender(String code) {
        this.code = code;
    }

    @JsonCreator
    public static Gender fromCode(String code) {
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
