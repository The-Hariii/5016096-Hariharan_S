package com.example.EmployeeManagementSystem1.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="departments")
@Data
@NamedQueries({
        @NamedQuery(name = "Department.findByDepartmentName", query = "SELECT d FROM Department d WHERE d.name = :name")
})
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long did;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;


}
