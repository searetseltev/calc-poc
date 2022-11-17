package com.rvg.operationmanagement.operations;

import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.operations.AddOperationUseCase;
import com.rvg.operationmanagement.exceptions.BadOperandsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class AddOperationUseCaseImpl implements AddOperationUseCase {

    @Override
    public OperationResult calculate(List<BigDecimal> values) throws BadOperandsException {
        log.info("calculate({})", values);

        if (!operationHasOperandsValid(values)) {
            throw new BadOperandsException("Operands are not valid");
        }

        BigDecimal result = values.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OperationResult(Collections.singletonList(result));
    }


    private boolean operationHasOperandsValid(List<BigDecimal> values) {
        return values != null && !values.contains(null) && values.size() >= 2;
    }
}
