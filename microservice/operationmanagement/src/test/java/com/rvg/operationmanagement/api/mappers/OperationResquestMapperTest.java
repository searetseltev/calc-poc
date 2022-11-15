package com.rvg.operationmanagement.api.mappers;

import com.rvg.operationmanagement.api.model.OperationRequestDTO;
import com.rvg.operationmanagement.api.model.OperationTypeDTO;
import com.rvg.operationmanagement.domain.enums.OperationsEnum;
import com.rvg.operationmanagement.domain.model.OperationRequest;
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
@DisplayName("OperationResquestMapperTest")
@ExtendWith(MockitoExtension.class)
class OperationResquestMapperTest {
    @InjectMocks
    OperationRequestMapper operationRequestMapper = new OperationRequestMapperImpl();

    private OperationRequestDTO operationRequestDTO;

    @BeforeEach
    public void setUp() {
        operationRequestDTO = new OperationRequestDTO();
        operationRequestDTO.setOperation(OperationTypeDTO.ADD);
        operationRequestDTO.setFirstOperand(BigDecimal.ONE);
        operationRequestDTO.setSecondOperand(BigDecimal.ONE);
    }

    @Test
    void create() {
        OperationRequestMapper newMapper = new OperationRequestMapperImpl();
        assertNotNull(newMapper);
    }

    @Test
    void toOperationRequest_add() {
        OperationRequest operationRequest = operationRequestMapper.toOperationRequest(operationRequestDTO);
        assertNotNull(operationRequest);
        assertEquals(OperationsEnum.ADD, operationRequest.getOperation());
        assertEquals(BigDecimal.ONE, operationRequest.getFirstOperand());
        assertEquals(BigDecimal.ONE, operationRequest.getSecondOperand());
    }

    @Test
    void toOperationRequest_subtract() {
        operationRequestDTO.setOperation(OperationTypeDTO.SUBTRACT);

        OperationRequest operationRequest = operationRequestMapper.toOperationRequest(operationRequestDTO);
        assertNotNull(operationRequest);
        assertEquals(OperationsEnum.SUBTRACT, operationRequest.getOperation());
        assertEquals(BigDecimal.ONE, operationRequest.getFirstOperand());
        assertEquals(BigDecimal.ONE, operationRequest.getSecondOperand());
    }

    @Test
    void toOperationRequest_unknown() {
        operationRequestDTO.setOperation(null);

        OperationRequest operationRequest = operationRequestMapper.toOperationRequest(operationRequestDTO);
        assertNotNull(operationRequest);
        assertEquals(OperationsEnum.UNKNOWN, operationRequest.getOperation());
        assertEquals(BigDecimal.ONE, operationRequest.getFirstOperand());
        assertEquals(BigDecimal.ONE, operationRequest.getSecondOperand());
    }

    @Test
    void toOperationRequest_noFirstOperand() {
        operationRequestDTO.setFirstOperand(null);

        OperationRequest operationRequest = operationRequestMapper.toOperationRequest(operationRequestDTO);
        assertNotNull(operationRequest);
        assertEquals(OperationsEnum.ADD, operationRequest.getOperation());
        assertEquals(BigDecimal.ZERO, operationRequest.getFirstOperand());
        assertEquals(BigDecimal.ONE, operationRequest.getSecondOperand());
    }

    @Test
    void toOperationRequest_noSecondOperand() {
        operationRequestDTO.setSecondOperand(null);

        OperationRequest operationRequest = operationRequestMapper.toOperationRequest(operationRequestDTO);
        assertNotNull(operationRequest);
        assertEquals(OperationsEnum.ADD, operationRequest.getOperation());
        assertEquals(BigDecimal.ONE, operationRequest.getFirstOperand());
        assertEquals(BigDecimal.ZERO, operationRequest.getSecondOperand());
    }

    @Test
    void toOperationRequest_null() {
        OperationRequest nullOperationRequest = operationRequestMapper.toOperationRequest(null);
        assertNull(nullOperationRequest);
    }

    @Test
    void toOperationsEnum() {
        OperationsEnum add = operationRequestMapper.toOperationsEnum(OperationTypeDTO.ADD);
        assertEquals(OperationsEnum.ADD, add);

        OperationsEnum subtract = operationRequestMapper.toOperationsEnum(OperationTypeDTO.SUBTRACT);
        assertEquals(OperationsEnum.SUBTRACT, subtract);

        OperationsEnum unknown = operationRequestMapper.toOperationsEnum(null);
        assertEquals(OperationsEnum.UNKNOWN, unknown);
    }

    @Test
    void toBigDecimal() {
        BigDecimal bigDecimal = operationRequestMapper.toBigDecimal(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, bigDecimal);

        BigDecimal bigDecimalNull = operationRequestMapper.toBigDecimal(null);
        assertEquals(BigDecimal.ZERO, bigDecimalNull);
    }
}
