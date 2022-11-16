package com.rvg.operationmanagement.services;

import com.rvg.operationmanagement.domain.model.OperationRequest;
import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.services.OperationsService;
import com.rvg.operationmanagement.exceptions.BadOperandsException;
import com.rvg.operationmanagement.exceptions.UnknownOperationException;
import io.corp.calculator.TracerImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class OperationsServiceImpl implements OperationsService {

    @Autowired
    TracerImpl tracer;

    @Override
    public OperationResult calculate(OperationRequest operationRequest) throws UnknownOperationException, BadOperandsException {
        log.info("calculate({})", operationRequest);

        if (!operationHasOperandsValid(operationRequest)) {
            throw new BadOperandsException("Operands are not valid");
        }

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

        tracer.trace(result);
        return new OperationResult(result);
    }


    private boolean operationHasOperandsValid(OperationRequest operationRequest) {
        return ObjectUtils.allNotNull(operationRequest, operationRequest.getFirstOperand(), operationRequest.getSecondOperand());
    }
}
