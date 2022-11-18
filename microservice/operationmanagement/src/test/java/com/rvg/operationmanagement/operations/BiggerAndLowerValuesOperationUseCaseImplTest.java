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
@DisplayName("BiggerAndLowerValuesOperationUseCaseImplTest")
@ExtendWith(MockitoExtension.class)
class BiggerAndLowerValuesOperationUseCaseImplTest {
    @InjectMocks
    final OperationUseCase biggerAndLowerValuesOperationUseCase = new BiggerAndLowerValuesOperationUseCaseImpl();

    private static final BigDecimal FIRST_OPERAND = new BigDecimal(10);
    private static final BigDecimal SECOND_OPERAND = new BigDecimal(5);
    private static final BigDecimal THIRD_OPERAND = new BigDecimal(2);
    private static final List<BigDecimal> BIGGER_AND_LOWER_VALUES_RESULT = Arrays.asList(FIRST_OPERAND, SECOND_OPERAND);
    private static final List<BigDecimal> BIGGER_AND_LOWER_VALUES_RESULT_WITH_THREE_OPERANDS = Arrays.asList(FIRST_OPERAND, THIRD_OPERAND);

    private final List<BigDecimal> values = Arrays.asList(FIRST_OPERAND, SECOND_OPERAND);
    private final List<BigDecimal> valuesWithOneOperand = Collections.singletonList(FIRST_OPERAND);
    private final List<BigDecimal> valuesWithThreeOperands = Arrays.asList(FIRST_OPERAND, SECOND_OPERAND, THIRD_OPERAND);
    private final List<BigDecimal> valuesWithNulls = Arrays.asList(FIRST_OPERAND, null, THIRD_OPERAND);
    @Test
    void create() {
        OperationUseCase newUseCase = new BiggerAndLowerValuesOperationUseCaseImpl();
        assertNotNull(newUseCase);
    }

    @Test
    void calculate_biggerAndLowerValues() {
        OperationResult result = biggerAndLowerValuesOperationUseCase.calculate(values);
        assertNotNull(result);
        assertEquals(BIGGER_AND_LOWER_VALUES_RESULT.get(0), result.getResults().get(0));
        assertEquals(BIGGER_AND_LOWER_VALUES_RESULT.get(1), result.getResults().get(1));
    }

    @Test
    void calculate_biggerAndLowerValuesWithOneOperand() {
        assertThrows(BadOperandsException.class, () -> biggerAndLowerValuesOperationUseCase.calculate(valuesWithOneOperand));
    }

    @Test
    void calculate_biggerAndLowerValuesWithThreeOperands() {
        OperationResult result = biggerAndLowerValuesOperationUseCase.calculate(valuesWithThreeOperands);
        assertNotNull(result);
        assertEquals(BIGGER_AND_LOWER_VALUES_RESULT_WITH_THREE_OPERANDS.get(0), result.getResults().get(0));
        assertEquals(BIGGER_AND_LOWER_VALUES_RESULT_WITH_THREE_OPERANDS.get(1), result.getResults().get(1));
    }

    @Test
    void calculate_biggerAndLowerValuesWithNulls() {
        assertThrows(BadOperandsException.class, () -> biggerAndLowerValuesOperationUseCase.calculate(valuesWithNulls));
    }

}
