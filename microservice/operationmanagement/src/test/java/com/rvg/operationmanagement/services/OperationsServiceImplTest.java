package com.rvg.operationmanagement.services;

import com.rvg.operationmanagement.domain.enums.OperationsEnum;
import com.rvg.operationmanagement.domain.model.OperationRequest;
import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.services.OperationsService;
import com.rvg.operationmanagement.exceptions.UnknownOperationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("OperationsServiceImplTest")
@ExtendWith(MockitoExtension.class)
class OperationsServiceImplTest {
    @InjectMocks
    OperationsService operationsService = new OperationsServiceImpl();

    @Mock
    TracerAPI tracerAPI;

    private static final BigDecimal FIRST_OPERAND = new BigDecimal(10);
    private static final BigDecimal SECOND_OPERAND = new BigDecimal(5);
    private static final BigDecimal ADD_RESULT = new BigDecimal(15);
    private static final BigDecimal SUBTRACT_RESULT = new BigDecimal(5);

    @Test
    void create() {
        OperationsService newService = new OperationsServiceImpl();
        assertNotNull(newService);
    }

    @Test
    void calculate_add() {
        OperationRequest operationRequest = new OperationRequest(OperationsEnum.ADD, FIRST_OPERAND, SECOND_OPERAND);
        OperationResult result = operationsService.calculate(operationRequest);
        assertNotNull(result);
        assertEquals(ADD_RESULT, result.getResult());
    }

    @Test
    void calculate_subtract() {
        OperationRequest operationRequest = new OperationRequest(OperationsEnum.SUBTRACT, FIRST_OPERAND, SECOND_OPERAND);
        OperationResult result = operationsService.calculate(operationRequest);
        assertNotNull(result);
        assertEquals(SUBTRACT_RESULT, result.getResult());
    }

    @Test
    void calculate_unknown() {
        OperationRequest operationRequest = new OperationRequest(OperationsEnum.UNKNOWN, FIRST_OPERAND, SECOND_OPERAND);
        assertThrows(UnknownOperationException.class, () -> operationsService.calculate(operationRequest));
    }
}
