package com.rvg.operationmanagement.services;

import com.rvg.operationmanagement.domain.model.OperationRequest;
import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.operations.AddOperationUseCase;
import com.rvg.operationmanagement.domain.operations.BiggerAndLowerValuesOperationUseCase;
import com.rvg.operationmanagement.domain.operations.SubtractOperationUseCase;
import com.rvg.operationmanagement.domain.services.OperationsService;
import com.rvg.operationmanagement.exceptions.BadOperandsException;
import com.rvg.operationmanagement.exceptions.UnknownOperationException;
import io.corp.calculator.TracerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OperationsServiceImpl implements OperationsService {

    @Autowired
    TracerImpl tracer;

    @Autowired
    AddOperationUseCase addOperationUseCase;

    @Autowired
    SubtractOperationUseCase subtractOperationUseCase;

    @Autowired
    BiggerAndLowerValuesOperationUseCase biggerAndLowerValuesOperationUseCase;

    @Override
    public OperationResult calculate(OperationRequest operationRequest) throws UnknownOperationException, BadOperandsException {
        log.info("calculate({})", operationRequest);

        if (operationRequest == null || operationRequest.getValues() == null) {
            throw new BadOperandsException("Operands are null");
        }

        OperationResult operationResult;
        switch(operationRequest.getOperation()) {
            case ADD:
                operationResult = addOperationUseCase.calculate(operationRequest.getValues());
                break;
            case SUBTRACT:
                operationResult = subtractOperationUseCase.calculate(operationRequest.getValues());
                break;
            case BIGGER_AND_LOWER:
                operationResult = biggerAndLowerValuesOperationUseCase.calculate(operationRequest.getValues());
                break;
            default:
                throw new UnknownOperationException("Unknown operation: " + operationRequest.getOperation());
        }

        tracer.trace(operationResult.getResults());
        return operationResult;
    }

}
