package com.rvg.operationmanagement.domain.operations;

import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.exceptions.BadOperandsException;

import java.math.BigDecimal;
import java.util.List;

public interface OperationUseCase {
    /**
     * Calculates the result of an operation
     * @param values The list of values needed to do the calc.
     * @return The result of the operation.
     * @throws BadOperandsException if the values are not valid.
     */
    OperationResult calculate(List<BigDecimal> values) throws BadOperandsException;

}
