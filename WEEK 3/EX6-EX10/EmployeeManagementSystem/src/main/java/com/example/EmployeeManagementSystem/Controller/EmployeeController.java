package com.example.EmployeeManagementSystem.Controller;



import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all employees with pagination and sorting
    @GetMapping
    public Page<Employee> getAllEmployees(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Create a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update an existing employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        return employeeRepository.save(existingEmployee);
    }

    // Delete an employee by ID
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    // Get employees by first name with pagination and sorting
    @GetMapping("/first-name")
    public Page<Employee> getEmployeesByFirstName(
            @RequestParam String name,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return employeeRepository.findByName(name, pageable);
    }

    // Get employees by email with pagination and sorting
    @GetMapping("/email")
    public Page<Employee> getEmployeesByEmail(
            @RequestParam String email,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return employeeRepository.findEmployeesByEmail(email, pageable);
    }

    // Get employees by department ID with pagination and sorting
    @GetMapping("/department/{departmentId}")
    public Page<Employee> getEmployeesByDepartmentId(
            @PathVariable Long departmentId,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return employeeRepository.findEmployeesByDepartmentId(departmentId, pageable);
    }

    // Get employees by email domain with pagination and sorting
    @GetMapping("/email-domain")
    public Page<Employee> getEmployeesByEmailDomain(
            @RequestParam String emailDomain,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return employeeRepository.findEmployeesByEmailDomain(emailDomain, pageable);
    }
}

