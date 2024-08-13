package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.Entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class DepartmentService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Department> findDepartmentsByNameFragment(String nameFragment) {
        String wildcardFragment = "%" + nameFragment + "%";
        TypedQuery<Department> query = entityManager.createNamedQuery("Department.findByNameFragment", Department.class);
        query.setParameter("nameFragment", wildcardFragment);
        return query.getResultList();
    }
}
