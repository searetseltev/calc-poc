package com.rvg.operationmanagement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@Tag("UnitTest")
@DisplayName("OperationManagementApplicationTest")
@ExtendWith(MockitoExtension.class)
class OperationManagementApplicationTest {

    @Test
    void create() {
        OperationManagementApplication newApplication = new OperationManagementApplication();
        assertNotNull(newApplication);
    }

    @Test
    void main() {
        String[] args = new String[] { "test1", "test2" };
        try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {

            mocked.when(() -> SpringApplication.run(OperationManagementApplication.class, args)).thenReturn(mock(ConfigurableApplicationContext.class));

            OperationManagementApplication.main(args);
            mocked.verify(() -> SpringApplication.run(OperationManagementApplication.class, args), times(1));
        }
    }
}
