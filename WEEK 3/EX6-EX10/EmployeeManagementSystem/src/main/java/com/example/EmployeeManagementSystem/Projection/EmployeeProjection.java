package com.example.EmployeeManagementSystem.Projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface EmployeeProjection {

    @Value("#{target.id}")
    Long getId();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.position}")
    String getPosition();

    @Value("#{target.createdDate}")
    LocalDateTime getCreatedDate();

    @Value("#{target.lastModifiedDate}")
    LocalDateTime getLastModifiedDate();
}
