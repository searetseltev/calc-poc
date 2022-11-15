package com.rvg.operationmanagement.api;

import com.rvg.operationmanagement.api.mappers.OperationRequestMapper;
import com.rvg.operationmanagement.api.mappers.OperationResultMapper;
import com.rvg.operationmanagement.api.model.OperationRequestDTO;
import com.rvg.operationmanagement.api.model.OperationResultDTO;
import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.services.OperationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OperationApiImpl implements OperationApi{

    @Autowired
    OperationsService operationsService;

    @Autowired
    OperationRequestMapper operationRequestMapper;

    @Autowired
    OperationResultMapper operationResultMapper;

    @Override
    public ResponseEntity<OperationResultDTO> getOperationResult(OperationRequestDTO operationRequestDTO) {
        log.info("getOperationResult({})", operationRequestDTO);
        OperationResult operationResult = operationsService.calculate(operationRequestMapper.toOperationRequest(operationRequestDTO));
        return ResponseEntity.ok(operationResultMapper.toOperationResultDTO(operationResult));
    }

}
