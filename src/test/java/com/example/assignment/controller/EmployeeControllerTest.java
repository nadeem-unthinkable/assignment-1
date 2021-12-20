package com.example.assignment.controller;

import com.example.assignment.entity.Department;
import com.example.assignment.entity.Employee;
import com.example.assignment.repo.IEmployee;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @MockBean
    private IEmployee iEmployee ;

    @InjectMocks
    @Autowired private EmployeeController employeeController ;

    Employee emp1 = new Employee();
    Employee emp2 = new Employee();


    Department dep1 = new Department();
    Department dep2 = new Department();

//    @BeforeAll
//    public void v1111() {
//        MockitoAnnotations.initMocks(this);
//    }

    @BeforeEach
    public void setUp(){
        emp1.setId(1);
        emp1.setSalary(20000);
        emp1.setEmail("jafarnadeem01@gmail.com");
        emp1.setAddress("New Delhi");
        emp1.setName("Nadeem Jafar");
        emp1.setPhone("8287562626");

        dep1.setId(101);
        dep1.setName("HR");
        dep1.setAddress("Gurgaon");

        emp1.setDepartment(dep1);



        emp2.setId(2);
        emp2.setSalary(25000);
        emp2.setEmail("jafarnadeem03@gmail.com");
        emp2.setAddress("Gurgaon");
        emp2.setName("Jafar");
        emp2.setPhone("7503047619");

        dep2.setId(101);
        dep2.setName("HR");
        dep2.setAddress("Gurgaon");

        emp2.setDepartment(dep2);
    }

//    @Test
//    @Disabled
//    public void testGetAll(){
//
//        List<Employee> expected = new ArrayList();
//        expected.add(emp1);
//
////        Mockito.when(iEmployee.findAll()).thenReturn(expected);
//        Mockito.when(iEmployee.findAll()).thenReturn(expected);
//        List<ResponseEntity<>> actual = employeeController.getAll();
////        System.out.println(actual.get(0));
////        assertThat(expected.get(0).getId()).isEqualTo((Employee)(actual.get(0).getBody().getId());
//    }

    @Test
    public void TestAdd(){
        Mockito.when(iEmployee.save(emp1)).thenReturn(emp1);
        ResponseEntity<?> actual = employeeController.add(emp1);
        log.error("Employe {}",((Employee)actual.getBody()).getId());
//        System.out.println(();
        assertThat(emp1.getId()).isEqualTo(((Employee)actual.getBody()).getId());
    }




    @Test
    public void testGetAll(){
        List<Employee> list = new ArrayList<>();
        list.add(emp1);
        list.add(emp2);
        Mockito.when(iEmployee.findAll()).thenReturn(list);

        List<Employee> actual = employeeController.getAll();

//        ResponseEntity<?> actual = employeeController.getAll();

//        if(actual.getStatusCode() == HttpStatus.BAD_GATEWAY){
//
//        }

//        log.info("EMPLOYEES list :  {} " , (( actual.getBody()) ) );
//        log.info("Response {} " , ( (actual.getBody()).getName()));

        log.info("Employee {} " , list);
        assertThat(list.get(0).getId()).isEqualTo(actual.get(0).getId());
        assertThat(list.get(0).getName()).isEqualTo(actual.get(0).getName());

        assertThat(list.get(1).getId()).isEqualTo(actual.get(1).getId());
        assertThat(list.get(1).getName()).isEqualTo(actual.get(1).getName());
    }

    @Test
    public void testGetById(){
        Mockito.when(iEmployee.getEmployeeById(1)).thenReturn(emp1);

        ResponseEntity<?> actual = employeeController.getById(1);
        log.info("Employee {} " , actual.getBody() );



        assertThat(emp1.getId()).isEqualTo(((Employee) actual.getBody()).getId());
        assertThat(emp1.getEmail()).isEqualTo(((Employee) actual.getBody()).getEmail());
    }


    @Test
    public void testEmployeeNotFoundById(){

        String expected = "No Employee found with this id";
        ResponseEntity<?> responseEntity = new ResponseEntity<>(expected , HttpStatus.NOT_FOUND);
        Mockito.when(iEmployee.findById(10)).thenReturn(null);

        ResponseEntity<?> actual = employeeController.getById(10);

        log.info("Employee {} " , actual.getBody() );

        assertThat(actual.getBody()).isEqualTo(responseEntity.getBody());
//        assertThat(emp1.getId()).isEqualTo(((Employee) actual.getBody()).getId());
//        assertThat(emp1.getEmail()).isEqualTo(((Employee) actual.getBody()).getEmail());

    }

}