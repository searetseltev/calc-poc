package com.rvg.operationmanagement.operations;

import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.operations.AddOperationUseCase;
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
@DisplayName("AddOperationUseCaseImplTest")
@ExtendWith(MockitoExtension.class)
class AddOperationUseCaseImplTest {
    @InjectMocks
    final AddOperationUseCase addOperationUseCase = new AddOperationUseCaseImpl();

    private static final BigDecimal FIRST_OPERAND = new BigDecimal(10);
    private static final BigDecimal SECOND_OPERAND = new BigDecimal(5);
    private static final BigDecimal THIRD_OPERAND = new BigDecimal(2);
    private static final BigDecimal ADD_RESULT = new BigDecimal(15);
    private static final BigDecimal ADD_RESULT_WITH_THREE_OPERANDS = new BigDecimal(17);

    private final List<BigDecimal> values = Arrays.asList(FIRST_OPERAND, SECOND_OPERAND);
    private final List<BigDecimal> valuesWithOneOperand = Collections.singletonList(FIRST_OPERAND);
    private final List<BigDecimal> valuesWithThreeOperands = Arrays.asList(FIRST_OPERAND, SECOND_OPERAND, THIRD_OPERAND);
    private final List<BigDecimal> valuesWithNulls = Arrays.asList(FIRST_OPERAND, null, THIRD_OPERAND);
    @Test
    void create() {
        AddOperationUseCase newUseCase = new AddOperationUseCaseImpl();
        assertNotNull(newUseCase);
    }

    @Test
    void calculate_add() {
        OperationResult result = addOperationUseCase.calculate(values);
        assertNotNull(result);
        assertEquals(ADD_RESULT, result.getResults().get(0));
    }

    @Test
    void calculate_addWithOneOperand() {
        assertThrows(BadOperandsException.class, () -> addOperationUseCase.calculate(valuesWithOneOperand));
    }

    @Test
    void calculate_addWithThreeOperands() {
        OperationResult result = addOperationUseCase.calculate(valuesWithThreeOperands);
        assertNotNull(result);
        assertEquals(ADD_RESULT_WITH_THREE_OPERANDS, result.getResults().get(0));
    }

    @Test
    void calculate_addWithNulls() {
        assertThrows(BadOperandsException.class, () -> addOperationUseCase.calculate(valuesWithNulls));
    }

}
