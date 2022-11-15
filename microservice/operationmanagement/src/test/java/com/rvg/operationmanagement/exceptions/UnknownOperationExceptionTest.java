package com.rvg.operationmanagement.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("UnitTest")
@DisplayName("RestResponseEntityExceptionHandlerTest")
@ExtendWith(MockitoExtension.class)
class UnknownOperationExceptionTest {

    @Test
    void create() {
        UnknownOperationException unknownOperationException = new UnknownOperationException("test");
        assertNotNull(unknownOperationException);
        assertEquals("test", unknownOperationException.getMessage());
    }
}
