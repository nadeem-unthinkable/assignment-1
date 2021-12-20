package com.example.assignment.controller;

import com.example.assignment.entity.Department;
import com.example.assignment.repo.IDepartment;
import com.example.assignment.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/department")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;


    //tested
    @PostMapping(value = "/add")
    public Department register(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    //tested
    @GetMapping(value = "/get/all")
    public List<Department> findAll(){
        return departmentService.getAllDepartment();
    }


    //tested
    @DeleteMapping(value = "/delete/by/name/{name}")
    public ResponseEntity<?> delete(String name) {
        try {
            Department dep = departmentService.deleteDepByName(name);
            log.info("Successfully deleted inside delete");
            return new ResponseEntity(dep , HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occured in department controller delete");
            return new ResponseEntity<>("Error occured while deleting" , HttpStatus.BAD_GATEWAY);
        }
    }

}
