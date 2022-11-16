package com.rvg.operationmanagement.domain.services;

import com.rvg.operationmanagement.domain.model.OperationRequest;
import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.exceptions.BadOperandsException;
import com.rvg.operationmanagement.exceptions.UnknownOperationException;

public interface OperationsService {
    OperationResult calculate(OperationRequest operationRequest) throws UnknownOperationException, BadOperandsException;
}
