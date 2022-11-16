package com.rvg.operationmanagement.api.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("OperationTypeDTOTest")
@ExtendWith(MockitoExtension.class)
class OperationTypeDTOTest {

    @Test
    void fromValue() {
        OperationTypeDTO operationTypeDTO = OperationTypeDTO.fromValue("add");
        assertNotNull(operationTypeDTO);
        assertEquals(OperationTypeDTO.ADD, operationTypeDTO);
    }

    @Test
    void fromValue_Exception() {
        assertThrows(IllegalArgumentException.class, () -> OperationTypeDTO.fromValue("invalid"));
    }

}
