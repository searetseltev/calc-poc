package com.rvg.operationmanagement.api;


import com.rvg.operationmanagement.model.OperationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("UnitTest")
@DisplayName("OperationApiDelegateImplTest")
@ExtendWith(MockitoExtension.class)
class OperationApiImplTest {

    @InjectMocks
    OperationApi operationApi = new OperationApiImpl();

    @BeforeEach
    public void setUp() {
    }

    @Test
    void create() {
        OperationApi newController = new OperationApiImpl();
        assertNotNull(newController);
    }

    @Test
    void getAdd_ok_params() {
        ResponseEntity<OperationResult> response = operationApi.getAdd(BigDecimal.ONE, BigDecimal.ONE);
    }
}
