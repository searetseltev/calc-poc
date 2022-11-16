package com.rvg.operationmanagement.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("OperationTypeDTOTest")
@ExtendWith(MockitoExtension.class)
class OperationApiTest {

    @Test
    void getRequest() {
        OperationApi operationApi = new OperationApiImpl();
        assertNotNull(operationApi);

        Optional<NativeWebRequest> nativeWebRequest = operationApi.getRequest();
        assertFalse(nativeWebRequest.isPresent());
    }

}
