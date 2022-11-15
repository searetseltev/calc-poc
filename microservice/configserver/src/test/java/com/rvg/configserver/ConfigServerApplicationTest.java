package com.rvg.configserver;

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
@DisplayName("ConfigServerApplicationTest")
@ExtendWith(MockitoExtension.class)
class ConfigServerApplicationTest {

    @Test
    void create() {
         ConfigServerApplication newApplication = new ConfigServerApplication();
         assertNotNull(newApplication);
    }

    @Test
    void main() {
        String[] args = new String[] { "test1", "test2" };
        try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {

            mocked.when(() -> SpringApplication.run(ConfigServerApplication.class, args)).thenReturn(mock(ConfigurableApplicationContext.class));

            ConfigServerApplication.main(args);
            mocked.verify(() -> SpringApplication.run(ConfigServerApplication.class, args), times(1));
        }
    }
}
