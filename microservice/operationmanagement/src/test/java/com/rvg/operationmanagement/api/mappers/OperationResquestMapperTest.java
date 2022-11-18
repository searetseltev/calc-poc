package com.rvg.operationmanagement.api.mappers;

import com.rvg.operationmanagement.api.model.OperationRequestDTO;
import com.rvg.operationmanagement.domain.model.OperationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("OperationResquestMapperTest")
@ExtendWith(MockitoExtension.class)
class OperationResquestMapperTest {
    @InjectMocks
    final OperationRequestMapper operationRequestMapper = new OperationRequestMapperImpl();

    private static final String ADD = "ADD";
    private static final String SUBTRACT = "SUBTRACT";
    private static final String BIGGER_AND_LOWER = "BIGGER_AND_LOWER";

    private OperationRequestDTO operationRequestDTO;
    private List<BigDecimal> values;

    @BeforeEach
    public void setUp() {
        values = new ArrayList<>();
        values.add(BigDecimal.ONE);
        values.add(BigDecimal.TEN);
        values.add(BigDecimal.valueOf(100L));

        operationRequestDTO = new OperationRequestDTO();
        operationRequestDTO.setOperation(ADD);
        operationRequestDTO.setValues(values);
    }

    @Test
    void create() {
        OperationRequestMapper newMapper = new OperationRequestMapperImpl();
        assertNotNull(newMapper);
    }

    @Test
    void toOperationRequest() {
        OperationRequest operationRequest = operationRequestMapper.toOperationRequest(operationRequestDTO);
        assertNotNull(operationRequest);
        assertEquals(ADD, operationRequest.getOperation());
        assertEquals(values.size(), operationRequest.getValues().size());
    }

    @Test
    void toOperationRequest_nullValues() {
        operationRequestDTO.setValues(null);

        OperationRequest operationRequest = operationRequestMapper.toOperationRequest(operationRequestDTO);
        assertNotNull(operationRequest);
        assertEquals(ADD, operationRequest.getOperation());
        assertNull(operationRequest.getValues());
    }

    @Test
    void toOperationRequest_null() {
        OperationRequest nullOperationRequest = operationRequestMapper.toOperationRequest(null);
        assertNull(nullOperationRequest);
    }

}
