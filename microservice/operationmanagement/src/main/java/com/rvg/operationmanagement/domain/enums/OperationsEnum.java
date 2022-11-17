package com.rvg.operationmanagement.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OperationsEnum {
    @JsonProperty("add")
    ADD,

    @JsonProperty("subtract")
    SUBTRACT,

    @JsonProperty("bigger_and_lower")
    BIGGER_AND_LOWER,

    @JsonProperty("unknown")
    UNKNOWN
}

