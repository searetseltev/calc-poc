package com.rvg.operationmanagement.services;

import com.rvg.operationmanagement.domain.model.OperationRequest;
import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.operations.OperationUseCase;
import com.rvg.operationmanagement.domain.services.OperationsService;
import com.rvg.operationmanagement.exceptions.BadOperandsException;
import com.rvg.operationmanagement.exceptions.UnknownOperationException;
import io.corp.calculator.TracerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("UnitTest")
@DisplayName("OperationsServiceImplTest")
@ExtendWith(MockitoExtension.class)
class OperationsServiceImplTest {
    @InjectMocks
    final OperationsService operationsService = new OperationsServiceImpl();

    @Mock
    Map<String, OperationUseCase> operations;

    @Mock
    OperationUseCase operationUseCase;

    @Mock
    TracerImpl tracer;

    private static final String ADD = "add";
    private static final String SUBTRACT = "subtract";
    private static final String BIGGER_AND_LOWER = "bigger_and_lower";
    private static final String UNKNOWN = "unknown_operation";
    private static final BigDecimal FIRST_OPERAND = new BigDecimal(10);
    private static final BigDecimal SECOND_OPERAND = new BigDecimal(5);
    private static final BigDecimal THIRD_OPERAND = new BigDecimal(2);
    private static final BigDecimal ADD_RESULT = new BigDecimal(15);
    private static final BigDecimal SUBTRACT_RESULT = new BigDecimal(5);
    private static final List<BigDecimal> BIGGER_AND_LOWER_RESULT = Arrays.asList(FIRST_OPERAND, THIRD_OPERAND);

    private final List<BigDecimal> values = Arrays.asList(FIRST_OPERAND, SECOND_OPERAND);
    private final List<BigDecimal> valuesWithThreeOperands = Arrays.asList(FIRST_OPERAND, SECOND_OPERAND, THIRD_OPERAND);

    @Test
    void create() {
        OperationsService newService = new OperationsServiceImpl();
        assertNotNull(newService);
    }

    @Test
    void calculate_add() {
        when(operations.containsKey(ADD)).thenReturn(true);
        when(operations.get(ADD)).thenReturn(operationUseCase);
        when(operationUseCase.calculate(values)).thenReturn(new OperationResult(Collections.singletonList(ADD_RESULT)));

        OperationRequest operationRequest = new OperationRequest(ADD, values);
        OperationResult result = operationsService.calculate(operationRequest);
        assertNotNull(result);
        assertEquals(ADD_RESULT, result.getResults().get(0));
    }

    @Test
    void calculate_subtract() {
        when(operations.containsKey(SUBTRACT)).thenReturn(true);
        when(operations.get(SUBTRACT)).thenReturn(operationUseCase);
        when(operationUseCase.calculate(values)).thenReturn(new OperationResult(Collections.singletonList(SUBTRACT_RESULT)));

        OperationRequest operationRequest = new OperationRequest(SUBTRACT, values);
        OperationResult result = operationsService.calculate(operationRequest);
        assertNotNull(result);
        assertEquals(SUBTRACT_RESULT, result.getResults().get(0));
    }

    @Test
    void calculate_biggerAndLower() {
        when(operations.containsKey(BIGGER_AND_LOWER)).thenReturn(true);
        when(operations.get(BIGGER_AND_LOWER)).thenReturn(operationUseCase);
        when(operationUseCase.calculate(valuesWithThreeOperands)).thenReturn(new OperationResult(BIGGER_AND_LOWER_RESULT));

        OperationRequest operationRequest = new OperationRequest(BIGGER_AND_LOWER, valuesWithThreeOperands);
        OperationResult result = operationsService.calculate(operationRequest);
        assertNotNull(result);
        assertEquals(BIGGER_AND_LOWER_RESULT.get(0), result.getResults().get(0));
        assertEquals(BIGGER_AND_LOWER_RESULT.get(1), result.getResults().get(1));
    }

    @Test
    void calculate_nullValues() {
        when(operations.containsKey(UNKNOWN)).thenReturn(true);

        OperationRequest operationRequest = new OperationRequest(UNKNOWN, null);
        assertThrows(BadOperandsException.class, () -> operationsService.calculate(operationRequest));
    }

    @Test
    void calculate_unknown() {
        when(operations.containsKey(UNKNOWN)).thenReturn(false);

        OperationRequest operationRequest = new OperationRequest(UNKNOWN, values);
        assertThrows(UnknownOperationException.class, () -> operationsService.calculate(operationRequest));
    }

}
