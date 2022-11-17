package com.rvg.operationmanagement.operations;

import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.operations.SubtractOperationUseCase;
import com.rvg.operationmanagement.exceptions.BadOperandsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class SubtractOperationUseCaseImpl implements SubtractOperationUseCase {

    @Override
    public OperationResult calculate(List<BigDecimal> values) throws BadOperandsException {
        log.info("calculate({})", values);

        if (!operationHasOperandsValid(values)) {
            throw new BadOperandsException("Operands are not valid");
        }

        BigDecimal head = values.get(0);
        List<BigDecimal> tail = values.subList(1, values.size());
        BigDecimal result = tail.stream().reduce(head, BigDecimal::subtract);

        return new OperationResult(Collections.singletonList(result));
    }

    private boolean operationHasOperandsValid(List<BigDecimal> values) {
        return values != null && !values.contains(null) && values.size() >= 2;
    }
}
