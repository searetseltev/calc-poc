package com.rvg.operationmanagement.api;

import com.rvg.operationmanagement.api.mappers.OperationRequestMapper;
import com.rvg.operationmanagement.api.mappers.OperationResultMapper;
import com.rvg.operationmanagement.api.model.OperationRequestDTO;
import com.rvg.operationmanagement.api.model.OperationResultDTO;
import com.rvg.operationmanagement.api.model.OperationTypeDTO;
import com.rvg.operationmanagement.domain.enums.OperationsEnum;
import com.rvg.operationmanagement.domain.model.OperationRequest;
import com.rvg.operationmanagement.domain.model.OperationResult;
import com.rvg.operationmanagement.domain.services.OperationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@Tag("UnitTest")
@DisplayName("OperationApiDelegateImplTest")
@ExtendWith(MockitoExtension.class)
class OperationApiImplTest {

    @InjectMocks
    OperationApi operationApi = new OperationApiImpl();

    @Mock
    OperationsService operationsService;

    @Mock
    OperationRequestMapper operationRequestMapper;

    @Mock
    OperationResultMapper operationResultMapper;

    private OperationRequest operationRequest;
    private OperationRequestDTO operationRequestDTO;
    private OperationResult operationResult;
    private OperationResultDTO operationResultDTO;

    @BeforeEach
    public void setUp() {
        operationRequest = new OperationRequest();
        operationRequest.setOperation(OperationsEnum.ADD);
        operationRequest.setFirstOperand(BigDecimal.ONE);
        operationRequest.setSecondOperand(BigDecimal.ONE);

        operationRequestDTO = new OperationRequestDTO();
        operationRequestDTO.setOperation(OperationTypeDTO.ADD);
        operationRequestDTO.setFirstOperand(BigDecimal.ONE);
        operationRequestDTO.setSecondOperand(BigDecimal.ONE);

        operationResult = new OperationResult();
        operationResult.setResult(new BigDecimal(2));

        operationResultDTO = new OperationResultDTO();
        operationResultDTO.setResult(new BigDecimal(2));
    }

    @Test
    void create() {
        OperationApi newController = new OperationApiImpl();
        assertNotNull(newController);
    }

    @Test
    void getOperationResult() {
        when(operationRequestMapper.toOperationRequest(operationRequestDTO)).thenReturn(operationRequest);
        when(operationsService.calculate(operationRequest)).thenReturn(operationResult);
        when(operationResultMapper.toOperationResultDTO(operationResult)).thenReturn(operationResultDTO);

        ResponseEntity<OperationResultDTO> result = operationApi.getOperationResult(operationRequestDTO);
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(operationResultDTO, result.getBody());
        assertEquals(operationResultDTO.getResult(), result.getBody().getResult());
    }
}
