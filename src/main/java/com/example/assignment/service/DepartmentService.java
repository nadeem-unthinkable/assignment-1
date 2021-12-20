package com.example.assignment.service;

import com.example.assignment.entity.Department;
import com.example.assignment.entity.Employee;
import com.example.assignment.repo.IDepartment;
import com.example.assignment.repo.IEmployee;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Service
public class DepartmentService {

    @Autowired
    private IDepartment iDepartment ;

    @Autowired
    private IEmployee iEmployee ;

    public Department addDepartment(@RequestBody Department department){
        boolean flag = iDepartment.existsDepartmentByName(department.getName());
        if(flag == true){
            return null ;
        }
        return iDepartment.save(department);
    }

    public List<Department> getAllDepartment(){
        return iDepartment.findAll();
    }

    public Department deleteDepByName(String name){
        Department department = iDepartment.getDepartmentByName(name);
        if(department == null){
            log.error("Department by name"+name +" not found");
            return null ;
        }

        List<Employee> empList = iEmployee.getEmployeesByDepartment(department);

        for(Employee emp : empList){
            iEmployee.delete(emp);
        }

        log.info("Department deletion successfull");

        iDepartment.delete(department);
        return department ;


    }

}
