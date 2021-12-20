package com.example.assignment.repo;

import com.example.assignment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartment extends JpaRepository<Department , Integer> {

    public boolean existsDepartmentByName(String name);

    public Department getDepartmentByName(String name);

}
