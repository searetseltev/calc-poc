package com.rvg.operationmanagement.api.mappers;

import com.rvg.operationmanagement.api.model.OperationResultDTO;
import com.rvg.operationmanagement.domain.model.OperationResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationResultMapper {
    OperationResultDTO toOperationResultDTO(OperationResult operationResult);
}
