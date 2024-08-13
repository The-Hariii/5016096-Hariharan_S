package com.example.EmployeeManagementSystem1.Repository;

import com.example.EmployeeManagementSystem1.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    // Optional<Employee> findById(Long id);

    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartmentName(String name);
}
