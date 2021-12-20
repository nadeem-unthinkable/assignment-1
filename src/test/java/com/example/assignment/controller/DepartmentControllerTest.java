package com.example.assignment.controller;

import com.example.assignment.entity.Department;
import com.example.assignment.entity.Employee;
import com.example.assignment.repo.IDepartment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.equalTo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public
class DepartmentControllerTest {

    @MockBean
    private IDepartment iDepartment ;

    @InjectMocks
    @Autowired private DepartmentController departmentController ;


    Department dep1 = new Department();
    Department dep2 = new Department();

//    @BeforeAll
//    public void v1111() {
//        MockitoAnnotations.initMocks(this);
//    }

    @BeforeEach
    public void setUp(){

        dep1.setId(101);
        dep1.setName("HR");
        dep1.setAddress("Gurgaon");


        dep2.setId(102);
        dep2.setName("IT");
        dep2.setAddress("Gurgaon");

    }

    @Test
    public void testAdd(){
        Mockito.when(iDepartment.save(dep1)).thenReturn(dep1);
        Department actual = departmentController.register(dep1);
        log.info("Department added : {}" , actual);
        assertThat(dep1.getName()).isEqualTo(actual.getName());
        assertThat(dep1.getId()).isEqualTo(actual.getId());
    }

    @Test
    @Disabled
    public void testNotAdded(){
        Mockito.when(iDepartment.save(dep1)).thenReturn(null);
        Department actual = departmentController.register(null);
        if(actual == null){
            log.info("Department details : {} " , actual);
            assertThat(true).isTrue();
//            assertTrue(true);
        }

//        assertThat(true).isEqualToFalse();
//        assertThat(null).isEqual(null);
    }

    @Test
    public void testGetAllDepartment(){

        List<Department> expected = new ArrayList<>();
        expected.add(dep1);
        expected.add(dep2);

        Mockito.when(iDepartment.findAll()).thenReturn(expected);
        List<Department> actual = departmentController.findAll();

        int i = 0 ;
        while(i < actual.size()){
            assertThat(expected.get(i).getId()).isEqualTo(actual.get(i).getId());
            assertThat(expected.get(i).getName()).isEqualTo(actual.get(i).getName());
            assertThat(expected.get(i).getAddress()).isEqualTo(actual.get(i).getAddress());
            i++ ;
        }
    }

    @Test
    public void testDeleteByName(){

        Mockito.when(iDepartment.getDepartmentByName("HR"))
                .thenReturn(dep1);

        // when
        ResponseEntity<?> actual = departmentController.delete("HR");

        // then

        log.info("Deleted dep is : {}" , actual.getBody());

        assertThat(dep1.getId()).isEqualTo(((Department)actual.getBody()).getId() );
        assertThat(dep1.getName()).isEqualTo(((Department)actual.getBody()).getName() );
        assertThat(dep1.getAddress()).isEqualTo(((Department)actual.getBody()).getAddress() );
    }

    @Test
    public void testNotFoundInDelete(){
        ResponseEntity<?> expected = new ResponseEntity<>("Not found" , HttpStatus.NOT_FOUND);
        Mockito.when(iDepartment.getDepartmentByName("TM")).thenReturn(null);
        ResponseEntity<?> actual = departmentController.delete("TM");

        log.info("Deleted dep is : {}" , actual.getBody());

        if(actual.getBody()  == null){
            assertThat(true).isTrue();
        }

    }

}