package com.rvg.operationmanagement.domain.model;

import com.rvg.operationmanagement.domain.enums.OperationsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequest {
    private OperationsEnum operation;
    private List<BigDecimal> values;
}
