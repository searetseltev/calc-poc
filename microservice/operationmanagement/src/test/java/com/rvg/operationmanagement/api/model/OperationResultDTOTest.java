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
@DisplayName("OperationResultDTOTest")
@ExtendWith(MockitoExtension.class)
class OperationResultDTOTest {

    private static final BigDecimal NUMBER = BigDecimal.ONE;
    private final List<BigDecimal> values = Arrays.asList(NUMBER, NUMBER);

    @Test
    void create() {
        OperationResultDTO newOperationResultDTO = new OperationResultDTO();
        assertNotNull(newOperationResultDTO);
    }

    @Test
    void params() {
        OperationResultDTO operationResultDTO = new OperationResultDTO();
        assertNotNull(operationResultDTO);

        operationResultDTO.setResults(values);

        assertEquals(values.get(0), operationResultDTO.getResults().get(0));
    }

    @Test
    void methods() {
        OperationResultDTO operationResultDTO = new OperationResultDTO();
        assertNotNull(operationResultDTO);

        operationResultDTO.results(values);
        assertEquals(values.get(0), operationResultDTO.getResults().get(0));

        operationResultDTO.setResults(null);
        operationResultDTO.addResultsItem(NUMBER);
        assertNotNull(operationResultDTO.getResults());
        assertEquals(NUMBER, operationResultDTO.getResults().get(0));
    }

    @Test
    void equals() {
        OperationResultDTO operationResultDTO1 = new OperationResultDTO();
        assertNotNull(operationResultDTO1);

        OperationResultDTO operationResultDTO2 = new OperationResultDTO();
        assertNotNull(operationResultDTO2);

        operationResultDTO1.setResults(values);
        operationResultDTO2.setResults(values);

        assertEquals(operationResultDTO1, operationResultDTO2);
        assertEquals(operationResultDTO1.hashCode(), operationResultDTO2.hashCode());

        operationResultDTO2.setResults(null);
        assertNotEquals(operationResultDTO1, operationResultDTO2);

        assertNotEquals(operationResultDTO2, null);
        assertNotEquals(null, operationResultDTO2);
    }

    @Test
    void toStringTest() {
        OperationResultDTO operationResultDTO = new OperationResultDTO();
        assertNotNull(operationResultDTO);

        operationResultDTO.setResults(values);
        String toString1 = operationResultDTO.toString();
        assertNotNull(toString1);
        assertFalse(toString1.isEmpty());

        operationResultDTO.setResults(null);

        String toString2 = operationResultDTO.toString();
        assertNotNull(toString2);
        assertTrue(toString2.contains("null"));

    }

}
