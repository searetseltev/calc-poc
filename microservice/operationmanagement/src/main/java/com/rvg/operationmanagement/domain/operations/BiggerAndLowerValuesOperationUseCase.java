package com.rvg.operationmanagement.domain.operations;

import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.exceptions.BadOperandsException;

import java.math.BigDecimal;
import java.util.List;

public interface BiggerAndLowerValuesOperationUseCase {
    /**
     * Returns the bigger and lower values of the list without duplicates.
     * @param values List of values
     * @return The bigger and lower values of the list.
     * @throws BadOperandsException if the values are not valid.
     */
    OperationResult calculate(List<BigDecimal> values) throws BadOperandsException;
}
