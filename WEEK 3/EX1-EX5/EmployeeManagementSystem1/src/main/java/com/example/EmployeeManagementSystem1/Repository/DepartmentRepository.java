package com.example.EmployeeManagementSystem1.Repository;

import com.example.EmployeeManagementSystem1.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {


    // Optional<Department> findByDepartmentId(Long id);

    @Query("SELECT d FROM Department d WHERE d.name = :name")
    Optional<Department> findByDepartmentName(String name);
}
