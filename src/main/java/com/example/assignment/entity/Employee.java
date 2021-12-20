package com.example.assignment.entity;

import javax.persistence.*;

@Entity

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String name ;

    private String address ;

    private int salary ;

    private String email ;

    private String phone ;

    @Transient
    private String departmentName ;

    @ManyToOne
    @JoinColumn(name = "dId" , referencedColumnName = "id")
    private Department department ;


    public Employee(int id, String name, String address, int salary, String email, String phone, Department department) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.email = email;
        this.phone = phone;
        this.department = department;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public String getDepartmentName(){
        return departmentName;
    }
    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", department=" + department +
                '}';
    }
}
