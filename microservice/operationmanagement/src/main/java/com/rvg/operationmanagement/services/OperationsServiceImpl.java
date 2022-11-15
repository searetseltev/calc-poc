package com.rvg.operationmanagement.services;

import com.rvg.operationmanagement.domain.model.OperationRequest;
import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.services.OperationsService;
import com.rvg.operationmanagement.exceptions.UnknownOperationException;
import io.corp.calculator.TracerAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class OperationsServiceImpl implements OperationsService {

    @Autowired
    TracerAPI tracerAPI;

    @Override
    public OperationResult calculate(OperationRequest operationRequest) throws UnknownOperationException {
        log.info("calculate({})", operationRequest);

        BigDecimal result;
        switch(operationRequest.getOperation()) {
            case ADD:
                result = operationRequest.getFirstOperand().add(operationRequest.getSecondOperand());
                break;
            case SUBTRACT:
                result = operationRequest.getFirstOperand().subtract(operationRequest.getSecondOperand());
                break;
            default:
                throw new UnknownOperationException("Unknown operation: " + operationRequest.getOperation());
        }

        tracerAPI.trace(result);
        return new OperationResult(result);
    }
}
