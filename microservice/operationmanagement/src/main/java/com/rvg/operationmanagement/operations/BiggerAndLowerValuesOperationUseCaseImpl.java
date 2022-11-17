package com.rvg.operationmanagement.operations;

import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.operations.BiggerAndLowerValuesOperationUseCase;
import com.rvg.operationmanagement.exceptions.BadOperandsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class BiggerAndLowerValuesOperationUseCaseImpl implements BiggerAndLowerValuesOperationUseCase {

    @Override
    public OperationResult calculate(List<BigDecimal> values) throws BadOperandsException {
        log.info("calculate({})", values);

        if (!operationHasOperandsValid(values)) {
            throw new BadOperandsException("Operands are not valid");
        }

        Optional<BigDecimal> bigger = values.stream().max(BigDecimal::compareTo);
        Optional<BigDecimal> lower = values.stream().min(BigDecimal::compareTo);

        if(!bigger.isPresent()) {
            throw new BadOperandsException("Bad operands values. Too big?");
        }

        // If bigger is present, lower is present too.
        return new OperationResult(Arrays.asList(bigger.get(), lower.get()));
    }

    private boolean operationHasOperandsValid(List<BigDecimal> values) {
        return values != null && !values.contains(null) && values.size() >= 2;
    }
}
