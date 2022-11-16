package com.rvg.operationmanagement.api.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("OperationRequestDTOTest")
@ExtendWith(MockitoExtension.class)
class OperationRequestDTOTest {

    private static final BigDecimal NUMBER = BigDecimal.ONE;
    private static final OperationTypeDTO OPERATION_TYPE = OperationTypeDTO.ADD;

    @Test
    void create() {
        OperationRequestDTO newOperationRequestDTO = new OperationRequestDTO();
        assertNotNull(newOperationRequestDTO);
    }

    @Test
    void params() {
        OperationRequestDTO operationRequestDTO = new OperationRequestDTO();
        assertNotNull(operationRequestDTO);

        operationRequestDTO.setOperation(OPERATION_TYPE);
        operationRequestDTO.setFirstOperand(NUMBER);
        operationRequestDTO.setSecondOperand(NUMBER);

        assertEquals(OPERATION_TYPE, operationRequestDTO.getOperation());
        assertEquals(NUMBER, operationRequestDTO.getFirstOperand());
        assertEquals(NUMBER, operationRequestDTO.getSecondOperand());
    }

    @Test
    void methods() {
        OperationRequestDTO operationRequestDTO = new OperationRequestDTO();
        assertNotNull(operationRequestDTO);

        operationRequestDTO.operation(OPERATION_TYPE);
        operationRequestDTO.firstOperand(NUMBER);
        operationRequestDTO.secondOperand(NUMBER);

        assertEquals(OPERATION_TYPE, operationRequestDTO.getOperation());
        assertEquals(NUMBER, operationRequestDTO.getFirstOperand());
        assertEquals(NUMBER, operationRequestDTO.getSecondOperand());
    }

    @Test
    void equals() {
        OperationRequestDTO operationRequestDTO1 = new OperationRequestDTO();
        operationRequestDTO1.setOperation(OPERATION_TYPE);
        operationRequestDTO1.setFirstOperand(NUMBER);
        operationRequestDTO1.setSecondOperand(NUMBER);

        OperationRequestDTO operationRequestDTO2 = new OperationRequestDTO();
        operationRequestDTO2.setOperation(OPERATION_TYPE);
        operationRequestDTO2.setFirstOperand(NUMBER);
        operationRequestDTO2.setSecondOperand(NUMBER);

        assertEquals(operationRequestDTO1, operationRequestDTO2);
        assertEquals(operationRequestDTO1.hashCode(), operationRequestDTO2.hashCode());

        operationRequestDTO2.setFirstOperand(null);
        assertNotEquals(operationRequestDTO1, operationRequestDTO2);

        assertNotEquals(null, operationRequestDTO2);
    }

    @Test
    void toStringTest() {
        OperationRequestDTO operationRequestDTO = new OperationRequestDTO();
        assertNotNull(operationRequestDTO);

        operationRequestDTO.setOperation(OPERATION_TYPE);
        operationRequestDTO.setFirstOperand(NUMBER);
        operationRequestDTO.setSecondOperand(null);

        String toString = operationRequestDTO.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("null"));

    }

}
