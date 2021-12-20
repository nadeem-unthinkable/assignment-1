package com.example.assignment.repo;
import java.util.*;

import com.example.assignment.entity.Department;
import com.example.assignment.entity.Employee;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployee extends JpaRepository<Employee , Integer> {

    public Employee getEmployeeById(int id);

    public List<Employee> getEmployeesByDepartment(Department department);

    public boolean existsEmployeeByName(String name);

}
