package com.example.EmployeeManagementSystem.Projection;

import java.time.LocalDateTime;

public class DepartmentProjectionClass {

    private final Long id;
    private final String name;
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;

    public DepartmentProjectionClass(Long id, String name, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public LocalDateTime getLastModifiedDate() { return lastModifiedDate; }
}
