package com.rvg.operationmanagement.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("UnitTest")
@DisplayName("BadOperandsExceptionTest")
@ExtendWith(MockitoExtension.class)
class BadOperandsExceptionTest {

    @Test
    void create() {
        BadOperandsException badOperandsException = new BadOperandsException("test");
        assertNotNull(badOperandsException);
        assertEquals("test", badOperandsException.getMessage());
    }
}
