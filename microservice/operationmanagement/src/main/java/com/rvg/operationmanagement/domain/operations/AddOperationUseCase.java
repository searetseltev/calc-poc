package com.rvg.operationmanagement.domain.operations;

import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.exceptions.BadOperandsException;

import java.math.BigDecimal;
import java.util.List;

public interface AddOperationUseCase {
    /**
     * Calculates the result of an addition operation
     * @param values The list of values to be added. Must have at least 2 values.
     * @return The result of the operation.
     * @throws BadOperandsException if the values are not valid.
     */
    OperationResult calculate(List<BigDecimal> values) throws BadOperandsException;
}
