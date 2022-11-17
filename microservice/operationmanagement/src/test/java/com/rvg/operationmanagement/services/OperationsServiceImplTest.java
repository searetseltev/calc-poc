package com.rvg.operationmanagement.services;

import com.rvg.operationmanagement.domain.enums.OperationsEnum;
import com.rvg.operationmanagement.domain.model.OperationRequest;
import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.operations.AddOperationUseCase;
import com.rvg.operationmanagement.domain.operations.BiggerAndLowerValuesOperationUseCase;
import com.rvg.operationmanagement.domain.operations.SubtractOperationUseCase;
import com.rvg.operationmanagement.domain.services.OperationsService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("UnitTest")
@DisplayName("OperationsServiceImplTest")
@ExtendWith(MockitoExtension.class)
class OperationsServiceImplTest {
    @InjectMocks
    final OperationsService operationsService = new OperationsServiceImpl();

    @Mock
    AddOperationUseCase addOperationUseCase;

    @Mock
    SubtractOperationUseCase subtractOperationUseCase;

    @Mock
    BiggerAndLowerValuesOperationUseCase biggerAndLowerValuesOperationUseCase;

    @Mock
    TracerImpl tracer;

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
        when(addOperationUseCase.calculate(values)).thenReturn(new OperationResult(Collections.singletonList(ADD_RESULT)));

        OperationRequest operationRequest = new OperationRequest(OperationsEnum.ADD, values);
        OperationResult result = operationsService.calculate(operationRequest);
        assertNotNull(result);
        assertEquals(ADD_RESULT, result.getResults().get(0));
    }

    @Test
    void calculate_subtract() {
        when(subtractOperationUseCase.calculate(values)).thenReturn(new OperationResult(Collections.singletonList(SUBTRACT_RESULT)));

        OperationRequest operationRequest = new OperationRequest(OperationsEnum.SUBTRACT, values);
        OperationResult result = operationsService.calculate(operationRequest);
        assertNotNull(result);
        assertEquals(SUBTRACT_RESULT, result.getResults().get(0));
    }

    @Test
    void calculate_biggerAndLower() {
        when(biggerAndLowerValuesOperationUseCase.calculate(valuesWithThreeOperands)).thenReturn(new OperationResult(BIGGER_AND_LOWER_RESULT));

        OperationRequest operationRequest = new OperationRequest(OperationsEnum.BIGGER_AND_LOWER, valuesWithThreeOperands);
        OperationResult result = operationsService.calculate(operationRequest);
        assertNotNull(result);
        assertEquals(BIGGER_AND_LOWER_RESULT.get(0), result.getResults().get(0));
        assertEquals(BIGGER_AND_LOWER_RESULT.get(1), result.getResults().get(1));
    }

    @Test
    void calculate_unknown() {
        OperationRequest operationRequest = new OperationRequest(OperationsEnum.UNKNOWN, values);
        assertThrows(UnknownOperationException.class, () -> operationsService.calculate(operationRequest));
    }

}
