package com.rvg.operationmanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequest {
    private String operation;
    private List<BigDecimal> values;
}
