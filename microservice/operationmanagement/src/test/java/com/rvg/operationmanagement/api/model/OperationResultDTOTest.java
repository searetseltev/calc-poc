package com.rvg.operationmanagement.api.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("OperationResultDTOTest")
@ExtendWith(MockitoExtension.class)
class OperationResultDTOTest {

    private static final BigDecimal NUMBER = BigDecimal.ONE;

    @Test
    void create() {
        OperationResultDTO newOperationResultDTO = new OperationResultDTO();
        assertNotNull(newOperationResultDTO);
    }

    @Test
    void params() {
        OperationResultDTO operationResultDTO = new OperationResultDTO();
        assertNotNull(operationResultDTO);

        operationResultDTO.setResult(NUMBER);

        assertEquals(NUMBER, operationResultDTO.getResult());
    }

    @Test
    void methods() {
        OperationResultDTO operationResultDTO = new OperationResultDTO();
        assertNotNull(operationResultDTO);

        operationResultDTO.result(NUMBER);

        assertEquals(NUMBER, operationResultDTO.getResult());
    }

    @Test
    void equals() {
        OperationResultDTO operationResultDTO1 = new OperationResultDTO();
        assertNotNull(operationResultDTO1);

        OperationResultDTO operationResultDTO2 = new OperationResultDTO();
        assertNotNull(operationResultDTO2);

        operationResultDTO1.setResult(NUMBER);
        operationResultDTO2.setResult(NUMBER);

        assertEquals(operationResultDTO1, operationResultDTO2);
        assertEquals(operationResultDTO1.hashCode(), operationResultDTO2.hashCode());

        operationResultDTO2.setResult(null);
        assertNotEquals(operationResultDTO1, operationResultDTO2);

        assertNotEquals(null, operationResultDTO2);
    }

    @Test
    void toStringTest() {
        OperationResultDTO operationResultDTO = new OperationResultDTO();
        assertNotNull(operationResultDTO);

        operationResultDTO.setResult(null);

        String toString = operationResultDTO.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("null"));

    }

}
