package com.rvg.operationmanagement.api.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("OperationRequestDTOTest")
@ExtendWith(MockitoExtension.class)
class OperationRequestDTOTest {

    private static final BigDecimal NUMBER = BigDecimal.ONE;
    private static final OperationTypeDTO OPERATION_TYPE = OperationTypeDTO.ADD;
    private final List<BigDecimal> values = Arrays.asList(NUMBER, NUMBER);

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
        operationRequestDTO.setValues(values);

        assertEquals(OPERATION_TYPE, operationRequestDTO.getOperation());
        assertEquals(NUMBER, operationRequestDTO.getValues().get(0));
        assertEquals(NUMBER, operationRequestDTO.getValues().get(1));
    }

    @Test
    void methods() {
        OperationRequestDTO operationRequestDTO = new OperationRequestDTO();
        assertNotNull(operationRequestDTO);

        operationRequestDTO.operation(OPERATION_TYPE);
        operationRequestDTO.values(values);

        assertEquals(OPERATION_TYPE, operationRequestDTO.getOperation());
        assertEquals(NUMBER, operationRequestDTO.getValues().get(0));
        assertEquals(NUMBER, operationRequestDTO.getValues().get(1));
    }

    @Test
    void equals() {
        OperationRequestDTO operationRequestDTO1 = new OperationRequestDTO();
        operationRequestDTO1.setOperation(OPERATION_TYPE);
        operationRequestDTO1.setValues(values);

        OperationRequestDTO operationRequestDTO2 = new OperationRequestDTO();
        operationRequestDTO2.setOperation(OPERATION_TYPE);
        operationRequestDTO2.setValues(values);

        assertEquals(operationRequestDTO1, operationRequestDTO2);
        assertEquals(operationRequestDTO1.hashCode(), operationRequestDTO2.hashCode());

        operationRequestDTO2.setValues(null);
        assertNotEquals(operationRequestDTO1, operationRequestDTO2);

        assertNotEquals(null, operationRequestDTO2);
    }

    @Test
    void toStringTest() {
        OperationRequestDTO operationRequestDTO = new OperationRequestDTO();
        assertNotNull(operationRequestDTO);

        operationRequestDTO.setOperation(OPERATION_TYPE);
        operationRequestDTO.setValues(null);

        String toString = operationRequestDTO.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("null"));

    }

}
