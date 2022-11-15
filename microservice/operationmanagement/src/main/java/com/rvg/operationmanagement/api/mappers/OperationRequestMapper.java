package com.rvg.operationmanagement.api.mappers;

import com.rvg.operationmanagement.api.model.OperationRequestDTO;
import com.rvg.operationmanagement.api.model.OperationTypeDTO;
import com.rvg.operationmanagement.domain.enums.OperationsEnum;
import com.rvg.operationmanagement.domain.model.OperationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface OperationRequestMapper {

    @Mapping(source = "operation", target = "operation", qualifiedByName = "mapOperation")
    @Mapping(source = "firstOperand", target = "firstOperand", qualifiedByName = "mapBigDecimal")
    @Mapping(source = "secondOperand", target = "secondOperand", qualifiedByName = "mapBigDecimal")
    OperationRequest toOperationRequest(OperationRequestDTO operationRequestDTO);

    @Named("mapOperation")
    default OperationsEnum toOperationsEnum(OperationTypeDTO operationTypeDTO) {
        if(operationTypeDTO != null && ObjectUtils.containsConstant(OperationsEnum.values(), operationTypeDTO.getValue(), false )) {
            return OperationsEnum.valueOf(operationTypeDTO.getValue().toUpperCase());
        }
        return OperationsEnum.UNKNOWN;
    }

    @Named("mapBigDecimal")
    default BigDecimal toBigDecimal(BigDecimal operand) {
        return operand != null ? operand : BigDecimal.ZERO;
    }

}
