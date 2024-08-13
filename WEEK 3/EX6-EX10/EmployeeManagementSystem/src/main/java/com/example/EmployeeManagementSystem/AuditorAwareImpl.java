package com.example.EmployeeManagementSystem;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Replace with actual user fetching logic, e.g., from Spring Security
        return Optional.of("system"); // Placeholder for demonstration
    }
}

