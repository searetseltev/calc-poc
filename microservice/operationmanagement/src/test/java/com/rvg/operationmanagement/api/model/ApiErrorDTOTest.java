package com.rvg.operationmanagement.api.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("ApiErrorDTOTest")
@ExtendWith(MockitoExtension.class)
class ApiErrorDTOTest {

    private static final String TEXT = "test";

    @Test
    void create() {
        ApiErrorDTO newApiErrorDTO = new ApiErrorDTO();
        assertNotNull(newApiErrorDTO);
    }

    @Test
    void params() {
        ApiErrorDTO apiErrorDTO = new ApiErrorDTO();
        assertNotNull(apiErrorDTO);

        apiErrorDTO.setError(TEXT);
        apiErrorDTO.setMessage(TEXT);
        apiErrorDTO.setPath(TEXT);
        apiErrorDTO.setStatus(1);
        apiErrorDTO.setTimestamp(TEXT);

        assertEquals(TEXT, apiErrorDTO.getError());
        assertEquals(TEXT, apiErrorDTO.getMessage());
        assertEquals(TEXT, apiErrorDTO.getPath());
        assertEquals(1, apiErrorDTO.getStatus());
        assertEquals(TEXT, apiErrorDTO.getTimestamp());
    }

    @Test
    void methods() {
        ApiErrorDTO apiErrorDTO = new ApiErrorDTO();
        assertNotNull(apiErrorDTO);

        apiErrorDTO.error(TEXT);
        apiErrorDTO.message(TEXT);
        apiErrorDTO.path(TEXT);
        apiErrorDTO.status(1);
        apiErrorDTO.timestamp(TEXT);

        assertEquals(TEXT, apiErrorDTO.getError());
        assertEquals(TEXT, apiErrorDTO.getMessage());
        assertEquals(TEXT, apiErrorDTO.getPath());
        assertEquals(1, apiErrorDTO.getStatus());
        assertEquals(TEXT, apiErrorDTO.getTimestamp());
    }

    @Test
    void equals() {
        ApiErrorDTO apiErrorDTO1 = new ApiErrorDTO();
        apiErrorDTO1.setError(TEXT);
        apiErrorDTO1.setMessage(TEXT);
        apiErrorDTO1.setPath(TEXT);
        apiErrorDTO1.setStatus(1);
        apiErrorDTO1.setTimestamp(TEXT);

        ApiErrorDTO apiErrorDTO2 = new ApiErrorDTO();
        apiErrorDTO2.setError(TEXT);
        apiErrorDTO2.setMessage(TEXT);
        apiErrorDTO2.setPath(TEXT);
        apiErrorDTO2.setStatus(1);
        apiErrorDTO2.setTimestamp(TEXT);

        assertEquals(apiErrorDTO1, apiErrorDTO1);
        assertEquals(apiErrorDTO1, apiErrorDTO2);
        assertEquals(apiErrorDTO1.hashCode(), apiErrorDTO2.hashCode());

        apiErrorDTO2.setError(null);
        assertNotEquals(apiErrorDTO1, apiErrorDTO2);
        assertNotEquals(apiErrorDTO2, null);

        assertNotEquals(null, apiErrorDTO2);
    }

    @Test
    void toStringTest() {
        ApiErrorDTO apiErrorDTO = new ApiErrorDTO();
        assertNotNull(apiErrorDTO);

        apiErrorDTO.setError(TEXT);
        apiErrorDTO.setMessage(TEXT);
        apiErrorDTO.setPath(TEXT);
        apiErrorDTO.setStatus(1);
        apiErrorDTO.setTimestamp(null);

        String toString = apiErrorDTO.toString();
        assertNotNull(toString);
        assertTrue(toString.contains(TEXT));

    }

}
