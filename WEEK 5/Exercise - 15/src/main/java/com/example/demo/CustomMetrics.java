package com.example.demo;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {

    @Autowired
    public CustomMetrics(MeterRegistry meterRegistry) {
        meterRegistry.gauge("custom.metric", 1); // Create a custom gauge metric
    }
}
