package com.rvg.discoveryserver;

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
@DisplayName("DiscoveryServerApplicationTest")
@ExtendWith(MockitoExtension.class)
class DiscoveryServerApplicationTest {

    @Test
    void create() {
        DiscoveryServerApplication newApplication = new DiscoveryServerApplication();
        assertNotNull(newApplication);
    }

    @Test
    void main() {
        String[] args = new String[] { "test1", "test2" };
        try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {

            mocked.when(() -> SpringApplication.run(DiscoveryServerApplication.class, args)).thenReturn(mock(ConfigurableApplicationContext.class));

            DiscoveryServerApplication.main(args);
            mocked.verify(() -> SpringApplication.run(DiscoveryServerApplication.class, args), times(1));
        }
    }
}

