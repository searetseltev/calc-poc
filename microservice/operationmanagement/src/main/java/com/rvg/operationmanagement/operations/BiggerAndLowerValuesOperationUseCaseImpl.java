package com.rvg.operationmanagement.operations;

import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.operations.OperationUseCase;
import com.rvg.operationmanagement.exceptions.BadOperandsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Remember to add the component name to the Api description to inform to the clients.
 */
@Slf4j
@Component("bigger_and_lower")
public class BiggerAndLowerValuesOperationUseCaseImpl implements OperationUseCase {

    @Override
    public OperationResult calculate(List<BigDecimal> values) throws BadOperandsException {
        log.info("calculate({})", values);

        validateValues(values);

        Optional<BigDecimal> bigger = values.stream().max(BigDecimal::compareTo);
        Optional<BigDecimal> lower = values.stream().min(BigDecimal::compareTo);

        if(!bigger.isPresent()) {
            throw new BadOperandsException("Bad operands values. Too big?");
        }

        // If bigger is present, lower is present too.
        return new OperationResult(Arrays.asList(bigger.get(), lower.get()));
    }

    private void validateValues(List<BigDecimal> values) {
        if (values == null || values.contains(null) || values.size() < 2) {
            throw new BadOperandsException("Operands are not valid");
        }
    }
}
