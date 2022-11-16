package com.rvg.operationmanagement.api.mappers;

import com.rvg.operationmanagement.api.model.OperationRequestDTO;
import com.rvg.operationmanagement.api.model.OperationTypeDTO;
import com.rvg.operationmanagement.domain.enums.OperationsEnum;
import com.rvg.operationmanagement.domain.model.OperationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.ObjectUtils;

@Mapper(componentModel = "spring")
public interface OperationRequestMapper {

    @Mapping(source = "operation", target = "operation", qualifiedByName = "mapOperation")
    OperationRequest toOperationRequest(OperationRequestDTO operationRequestDTO);

    @Named("mapOperation")
    default OperationsEnum toOperationsEnum(OperationTypeDTO operationTypeDTO) {
        if(operationTypeDTO != null && ObjectUtils.containsConstant(OperationsEnum.values(), operationTypeDTO.getValue(), false )) {
            return OperationsEnum.valueOf(operationTypeDTO.getValue().toUpperCase());
        }
        return OperationsEnum.UNKNOWN;
    }
}
