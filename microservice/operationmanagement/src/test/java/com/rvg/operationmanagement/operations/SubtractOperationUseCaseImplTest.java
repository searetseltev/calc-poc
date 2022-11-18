package com.rvg.operationmanagement.operations;

import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.operations.OperationUseCase;
import com.rvg.operationmanagement.exceptions.BadOperandsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("SubtractOperationUseCaseImplTest")
@ExtendWith(MockitoExtension.class)
class SubtractOperationUseCaseImplTest {
    @InjectMocks
    final OperationUseCase subtractOperationUseCase = new SubtractOperationUseCaseImpl();

    private static final BigDecimal FIRST_OPERAND = new BigDecimal(10);
    private static final BigDecimal SECOND_OPERAND = new BigDecimal(5);
    private static final BigDecimal THIRD_OPERAND = new BigDecimal(2);
    private static final BigDecimal SUBTRACT_RESULT = new BigDecimal(5);
    private static final BigDecimal SUBTRACT_RESULT_WITH_THREE_OPERANDS = new BigDecimal(3);

    private final List<BigDecimal> values = Arrays.asList(FIRST_OPERAND, SECOND_OPERAND);
    private final List<BigDecimal> valuesWithOneOperand = Collections.singletonList(FIRST_OPERAND);
    private final List<BigDecimal> valuesWithThreeOperands = Arrays.asList(FIRST_OPERAND, SECOND_OPERAND, THIRD_OPERAND);
    private final List<BigDecimal> valuesWithNulls = Arrays.asList(FIRST_OPERAND, null, THIRD_OPERAND);
    @Test
    void create() {
        OperationUseCase newUseCase = new SubtractOperationUseCaseImpl();
        assertNotNull(newUseCase);
    }

    @Test
    void calculate_subtract() {
        OperationResult result = subtractOperationUseCase.calculate(values);
        assertNotNull(result);
        assertEquals(SUBTRACT_RESULT, result.getResults().get(0));
    }

    @Test
    void calculate_subtractWithOneOperand() {
        assertThrows(BadOperandsException.class, () -> subtractOperationUseCase.calculate(valuesWithOneOperand));
    }

    @Test
    void calculate_subtractWithThreeOperands() {
        OperationResult result = subtractOperationUseCase.calculate(valuesWithThreeOperands);
        assertNotNull(result);
        assertEquals(SUBTRACT_RESULT_WITH_THREE_OPERANDS, result.getResults().get(0));
    }

    @Test
    void calculate_subtractWithNulls() {
        assertThrows(BadOperandsException.class, () -> subtractOperationUseCase.calculate(valuesWithNulls));
    }

}
