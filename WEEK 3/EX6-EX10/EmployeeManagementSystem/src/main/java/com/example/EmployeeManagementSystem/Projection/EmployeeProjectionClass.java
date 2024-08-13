package com.example.EmployeeManagementSystem.Projection;

import java.time.LocalDateTime;

public class EmployeeProjectionClass {

    private final Long id;
    private final String name;
    private final String position;
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;

    public EmployeeProjectionClass(Long id, String name, String position, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public LocalDateTime getLastModifiedDate() { return lastModifiedDate; }
}
