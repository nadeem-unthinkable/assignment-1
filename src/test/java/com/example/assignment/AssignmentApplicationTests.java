package com.example.assignment;

import com.example.assignment.controller.DepartmentController;
import com.example.assignment.controller.DepartmentControllerTest;
import com.example.assignment.controller.EmployeeController;
import com.example.assignment.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.sql.Assignment;

@SpringBootTest

@RunWith(Suite.class)
//@RunWith(assignment.class) and
@Suite.SuiteClasses({DepartmentControllerTest.class , EmployeeController.class})

class AssignmentApplicationTests {

    @Test
    void contextLoads() {
    }

}
