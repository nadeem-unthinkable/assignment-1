package com.example.assignment.service;

import com.example.assignment.entity.Employee;
import com.example.assignment.repo.IEmployee;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

    @Mock
    private IEmployee iEmployee ;

    @InjectMocks
    private EmployeeService employeeService ;


    @Test
    @Disabled
    public void testAdd(){
        Employee emp = new Employee();
//        emp.setId(1);
        emp.setName("Jafar");
        emp.setEmail("jafarnadeem01");
        emp.setAddress("Delhi");
        emp.setSalary(10000);

        Employee value = null;
        Mockito.when(iEmployee.save(emp)).thenReturn(value);

        boolean flag = iEmployee.existsEmployeeByName(value.getName());
        assertFalse(flag);

    }
}