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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("OperationResquestMapperTest")
@ExtendWith(MockitoExtension.class)
class OperationResquestMapperTest {
    @InjectMocks
    final OperationRequestMapper operationRequestMapper = new OperationRequestMapperImpl();

    private OperationRequestDTO operationRequestDTO;
    private List<BigDecimal> values;

    @BeforeEach
    public void setUp() {
        values = new ArrayList<>();
        values.add(BigDecimal.ONE);
        values.add(BigDecimal.TEN);
        values.add(BigDecimal.valueOf(100L));

        operationRequestDTO = new OperationRequestDTO();
        operationRequestDTO.setOperation(OperationTypeDTO.ADD);
        operationRequestDTO.setValues(values);
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
        assertEquals(values.size(), operationRequest.getValues().size());
    }

    @Test
    void toOperationRequest_subtract() {
        operationRequestDTO.setOperation(OperationTypeDTO.SUBTRACT);

        OperationRequest operationRequest = operationRequestMapper.toOperationRequest(operationRequestDTO);
        assertNotNull(operationRequest);
        assertEquals(OperationsEnum.SUBTRACT, operationRequest.getOperation());
        assertEquals(values.size(), operationRequest.getValues().size());
    }

    @Test
    void  toOperationRequest_BiggerAndLower() {
        operationRequestDTO.setOperation(OperationTypeDTO.BIGGER_AND_LOWER);

        OperationRequest operationRequest = operationRequestMapper.toOperationRequest(operationRequestDTO);
        assertNotNull(operationRequest);
        assertEquals(OperationsEnum.BIGGER_AND_LOWER, operationRequest.getOperation());
        assertEquals(values.size(), operationRequest.getValues().size());
    }

    @Test
    void toOperationRequest_unknown() {
        operationRequestDTO.setOperation(null);

        OperationRequest operationRequest = operationRequestMapper.toOperationRequest(operationRequestDTO);
        assertNotNull(operationRequest);
        assertEquals(OperationsEnum.UNKNOWN, operationRequest.getOperation());
        assertEquals(values.size(), operationRequest.getValues().size());
    }



    @Test
    void toOperationRequest_nullValues() {
        operationRequestDTO.setValues(null);

        OperationRequest operationRequest = operationRequestMapper.toOperationRequest(operationRequestDTO);
        assertNotNull(operationRequest);
        assertEquals(OperationsEnum.ADD, operationRequest.getOperation());
        assertNull(operationRequest.getValues());
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

        OperationsEnum biggerAndLower = operationRequestMapper.toOperationsEnum(OperationTypeDTO.BIGGER_AND_LOWER);
        assertEquals(OperationsEnum.BIGGER_AND_LOWER, biggerAndLower);

        OperationsEnum unknown = operationRequestMapper.toOperationsEnum(null);
        assertEquals(OperationsEnum.UNKNOWN, unknown);
    }

}
