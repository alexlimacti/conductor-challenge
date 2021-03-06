package com.muxichallenge.api.exception;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_DATA("Invalid data"),
    SYSTEM_ERROR("System error"),
    INVALID_PARAMETER("Invalid parameter"),
    INCOMPRESIBLE_MESSAGE("Incomprehensible message"),
    RESOURCE_EXISTING("Resource existing"),
    RESOURCE_NOT_FOUND("Resource not found");

    private String title;

    ProblemType(String title) {
        this.title = title;
    }

}
