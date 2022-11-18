package com.rvg.operationmanagement.api.mappers;

import com.rvg.operationmanagement.api.model.OperationRequestDTO;
import com.rvg.operationmanagement.domain.model.OperationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationRequestMapper {

    OperationRequest toOperationRequest(OperationRequestDTO operationRequestDTO);

}
