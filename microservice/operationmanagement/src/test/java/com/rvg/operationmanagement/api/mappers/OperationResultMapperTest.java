package com.rvg.operationmanagement.api.mappers;

import com.rvg.operationmanagement.api.model.OperationResultDTO;
import com.rvg.operationmanagement.domain.model.OperationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("OperationResultMapperTest")
@ExtendWith(MockitoExtension.class)
class OperationResultMapperTest {
    @InjectMocks
    final OperationResultMapper operationResultMapper = new OperationResultMapperImpl();

    private OperationResult operationResult;

    @BeforeEach
    public void setUp() {
        List<BigDecimal> results = Arrays.asList(new BigDecimal(10), new BigDecimal(5));
        operationResult = new OperationResult();
        operationResult.setResults(results);
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
        assertEquals(new BigDecimal(5), operationResultDTO.getResults().get(1));
    }

    @Test
    void toOperationResultDTO_null() {
        OperationResultDTO operationResultDTO = operationResultMapper.toOperationResultDTO(null);
        assertNull(operationResultDTO);
    }
}
