package com.rvg.operationmanagement.operations;

import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.operations.OperationUseCase;
import com.rvg.operationmanagement.exceptions.BadOperandsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Remember to add the component name to the Api description to inform to the clients.
 */
@Slf4j
@Component("subtract")
public class SubtractOperationUseCaseImpl implements OperationUseCase {

    @Override
    public OperationResult calculate(List<BigDecimal> values) throws BadOperandsException {
        log.info("calculate({})", values);

        validateValues(values);

        BigDecimal head = values.get(0);
        List<BigDecimal> tail = values.subList(1, values.size());
        BigDecimal result = tail.stream().reduce(head, BigDecimal::subtract);

        return new OperationResult(Collections.singletonList(result));
    }

    private void validateValues(List<BigDecimal> values) {
        if (values == null || values.contains(null) || values.size() < 2) {
            throw new BadOperandsException("Operands are not valid");
        }
    }
}
