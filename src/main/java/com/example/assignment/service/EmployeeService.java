package com.example.assignment.service;

import com.example.assignment.entity.Department;
import com.example.assignment.entity.Employee;
import com.example.assignment.repo.IDepartment;
import com.example.assignment.repo.IEmployee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {
    @Autowired
    private IEmployee iEmployee ;

    @Autowired
    private IDepartment iDepartment;

    public Employee addEmployeeNew(Employee employee){
        String departmentName = employee.getDepartmentName();
        Department department = iDepartment.getDepartmentByName(departmentName);
        employee.setDepartment(department);

        return iEmployee.save(employee);
    }


    public void add(Employee employee) {
        if (employee != null) {
            if(!iDepartment.existsDepartmentByName(employee.getDepartment().getName())) {
                iDepartment.save(employee.getDepartment());
            }
            employee.setDepartmentName(employee.getDepartment().getName());
            iEmployee.save(employee);
            System.out.println("Successfull");
        } else {
            System.out.println("error occured");
        }
    }



    @Transactional
    public ResponseEntity<?> addEmployee(Employee employee) {
//        return iEmployee.save()
       try{
            return new ResponseEntity<>(iEmployee.save(employee), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Try Again", HttpStatus.BAD_REQUEST);
        }
    }

    public List<Employee> getAllEmployee(){
        return iEmployee.findAll();
    }

    //update
    public ResponseEntity update(Employee employee , int id) {

        try {
            Employee emp1 = iEmployee.getEmployeeById(id);
            log.info("Inside employee" + emp1);
            employee.setDepartment(emp1.getDepartment());
            employee.setId(id);

            return new ResponseEntity(iEmployee.save(employee), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);

        }



//        Department department = iDepartment.getDepartmentByName("IT");
//        employee.setDepartment(department);

//        return iEmployee.save(employee);
    }

    public List<Employee> getEmployeeListByDeptName(String name){
        Department department = iDepartment.getDepartmentByName(name);
        return iEmployee.getEmployeesByDepartment(department);
    }

    public void deleteEmployeeUsingId(int id){
        Employee employee = iEmployee.getEmployeeById(id);
        if(employee == null){
            log.error("---Error occured inside deleteEmployeeUsingId ---");
        }
        iEmployee.delete(employee);
        log.info("Successfully Deleted employee");
    }

    public Employee findById(int id){
        Employee employee = iEmployee.getEmployeeById(id);

        if(employee == null){
            return null ;
        }
//        return optional.get();
        return employee ;
    }


}
