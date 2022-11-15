package com.rvg.operationmanagement.services;

import com.rvg.operationmanagement.domain.model.OperationRequest;
import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.services.OperationsService;
import com.rvg.operationmanagement.exceptions.UnknownOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OperationsServiceImpl implements OperationsService {

    @Override
    public OperationResult calculate(OperationRequest operationRequest) throws UnknownOperationException {
        log.info("calculate({})", operationRequest);
        switch(operationRequest.getOperation()) {
            case ADD:
                return new OperationResult(operationRequest.getFirstOperand().add(operationRequest.getSecondOperand()));
            case SUBTRACT:
                return new OperationResult(operationRequest.getFirstOperand().subtract(operationRequest.getSecondOperand()));
            default:
                throw new UnknownOperationException("Unknown operation: " + operationRequest.getOperation());
        }
    }
}
