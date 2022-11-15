package com.rvg.operationmanagement.api.mappers;

import com.rvg.operationmanagement.api.model.OperationRequestDTO;
import com.rvg.operationmanagement.api.model.OperationResultDTO;
import com.rvg.operationmanagement.api.model.OperationTypeDTO;
import com.rvg.operationmanagement.domain.enums.OperationsEnum;
import com.rvg.operationmanagement.domain.model.OperationRequest;
import com.rvg.operationmanagement.domain.model.OperationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("OperationResultMapperTest")
@ExtendWith(MockitoExtension.class)
class OperationResultMapperTest {
    @InjectMocks
    OperationResultMapper operationResultMapper = new OperationResultMapperImpl();

    private OperationResult operationResult;

    @BeforeEach
    public void setUp() {
        operationResult = new OperationResult();
        operationResult.setResult(BigDecimal.ONE);
    }

    @Test
    void create() {
        OperationResultMapper newMapper = new OperationResultMapperImpl();
        assertNotNull(newMapper);
    }

    @Test
    void toOperationResultDTO() {
        OperationResultDTO operationResultDTO = operationResultMapper.toOperationResultDTO(operationResult);
        assertNotNull(operationResultDTO);
        assertEquals(BigDecimal.ONE, operationResultDTO.getResult());
    }

    @Test
    void toOperationResultDTO_null() {
        OperationResultDTO operationResultDTO = operationResultMapper.toOperationResultDTO(null);
        assertNull(operationResultDTO);
    }
}
