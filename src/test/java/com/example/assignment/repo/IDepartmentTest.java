package com.example.assignment.repo;

import com.example.assignment.entity.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.events.Event;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Disabled
@SpringBootTest
class IDepartmentTest {

    @Autowired
    private IDepartment iDepartment ;


    Department department = new Department(105 , "HRM" , "Delhi");
    ;
    @BeforeEach
    void setUp() {
//        department = new Department(105 , "HRM" , "Delhi");

    }



    @Test()
//    @Disabled
    public void testExistsDepartmentByName(){
        iDepartment.save(department);
        boolean flag = iDepartment.existsDepartmentByName("HRM");
        assertThat(flag).isTrue();
    }


    @Test
    public void testGetDepartmentByName(){
        iDepartment.save(department);
        Department dep = iDepartment.getDepartmentByName(department.getName());
        assertThat(dep.getName()).isEqualTo(department.getName());
    }

    @AfterEach
    void tearDown() {
        iDepartment.delete(department);
    }



}