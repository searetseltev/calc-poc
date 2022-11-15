package com.rvg.operationmanagement.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OperationsEnum {
    @JsonProperty("add")
    ADD,

    @JsonProperty("subtract")
    SUBTRACT,

    @JsonProperty("unknown")
    UNKNOWN
}

