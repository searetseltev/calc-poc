package com.rvg.operationmanagement.services;

import com.rvg.operationmanagement.domain.model.OperationRequest;
import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.operations.OperationUseCase;
import com.rvg.operationmanagement.domain.services.OperationsService;
import com.rvg.operationmanagement.exceptions.BadOperandsException;
import com.rvg.operationmanagement.exceptions.UnknownOperationException;
import io.corp.calculator.TracerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class OperationsServiceImpl implements OperationsService {

    @Autowired
    TracerImpl tracer;

    @Autowired
    Map<String, OperationUseCase> operations;

    @Override
    public OperationResult calculate(OperationRequest operationRequest) throws UnknownOperationException, BadOperandsException {
        log.info("calculate({})", operationRequest);
        log.info("calculate(): operations={}" , operations);


        if (operationRequest == null || operationRequest.getOperation() == null || !operations.containsKey(operationRequest.getOperation())) {
            throw new UnknownOperationException("Operation is not valid");
        }

        if (operationRequest.getValues() == null) {
            throw new BadOperandsException("Values is null");
        }

        OperationResult operationResult = operations.get(operationRequest.getOperation()).calculate(operationRequest.getValues());

        tracer.trace(operationResult.getResults());
        return operationResult;
    }

}
