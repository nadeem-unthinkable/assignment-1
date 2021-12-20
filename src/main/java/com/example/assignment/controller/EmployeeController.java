package com.example.assignment.controller;

import com.example.assignment.entity.Employee;
import com.example.assignment.service.EmployeeService;
import jdk.jshell.Snippet;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.dom.DOMNodeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//    @GetMapping(value = "/check")
//    public String hq(){
//        return "Hello" ;
//    }


    //TESTED
    @PostMapping(value = "/register")
    public ResponseEntity<?> add(@RequestBody Employee employee){
        try{
            Employee emp1 = employeeService.addEmployeeNew(employee);
            return new ResponseEntity<>(emp1 , HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Not able to enter data"  , HttpStatus.BAD_REQUEST);
        }
//        return new ResponseEntity<>(employeeService.addEmployeeNew(employee) , HttpStatus.OK);
//        return new ResponseEntity<>(employeeService.add(employee));
    }


    //TESTED
    @GetMapping(value = "/get/all")
    public List<Employee> getAll(){
        try{
            List<Employee> list = employeeService.getAllEmployee();
            for(Employee employee : list){
                employee.setDepartmentName(employee.getDepartment().getName());
            }

//            return new ResponseEntity<>(list , HttpStatus.OK);
//            List<Employee> list2 =
            return list ;

        } catch (Exception e){
            e.printStackTrace();
            return null ;
//            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }
//        List<Employee> employeeService.getAllEmployee();
    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity updateEmployeeById(@PathVariable int id, @RequestBody Employee employee)  {

        return employeeService.update(employee , id);
//        try {
//            employee.setId(id);
//            return new ResponseEntity(employeeService.update(employee), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
//
//        }
    }

    //show all employee object of particular department
    @GetMapping(value = "/deparment/{name}/employee")
    public List<Employee> getEmployeeList(@PathVariable String name){
        return employeeService.getEmployeeListByDeptName(name);
    }


    @DeleteMapping(value = "/delete/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id){
        try {
            employeeService.deleteEmployeeUsingId(id);
            log.info("Successfully deleted employee");
            return new ResponseEntity<>("Successfully deleted employee" , HttpStatus.OK);

        } catch (Exception e){
            e.printStackTrace();
            log.error("Error occured inside deleteEmployee in employee controller");
            return new ResponseEntity<>("Error occured inside deleteEmployee in employee controller" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "find/by/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        Employee emp = employeeService.findById(id);
        if(emp == null){
            return new ResponseEntity<>("No Employee found with this id" , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(emp , HttpStatus.OK);
    }


}
