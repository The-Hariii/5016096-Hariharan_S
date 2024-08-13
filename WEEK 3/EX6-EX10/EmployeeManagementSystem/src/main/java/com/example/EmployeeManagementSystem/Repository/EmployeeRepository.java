package com.example.EmployeeManagementSystem.Repository;

import com.example.EmployeeManagementSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Page<Employee> findByName(String name, Pageable pageable);

    Page<Employee> findByEmail(String email, Pageable pageable);


    @Query(name = "Employee.findByEmail")
    Page<Employee> findEmployeesByEmail(String email, Pageable pageable);

    @Query(name = "Employee.findByDepartmentId")
    Page<Employee> findEmployeesByDepartmentId(Long departmentId, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.email LIKE %:emailDomain")
    Page<Employee> findEmployeesByEmailDomain(String emailDomain, Pageable pageable);

    void clear();
}
