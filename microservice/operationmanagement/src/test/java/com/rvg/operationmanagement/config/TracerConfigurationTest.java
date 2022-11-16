package com.rvg.operationmanagement.config;

import io.corp.calculator.TracerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("UnitTest")
@DisplayName("TracerConfigurationTest")
@ExtendWith(MockitoExtension.class)
class TracerConfigurationTest {
    @InjectMocks
    final TracerConfiguration tracerConfiguration = new TracerConfiguration();

    @Test
    void create() {
        TracerConfiguration newConfig = new TracerConfiguration();
        assertNotNull(newConfig);
    }

    @Test
    void getTracerImpl() {
        TracerImpl result = tracerConfiguration.getTracerImpl();
        assertNotNull(result);
    }
}
