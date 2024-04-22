package com.example.empolyee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.empolyee.entity.Employee;
import com.example.empolyee.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Long register(@RequestBody Employee newEmployee){
        return employeeService.registerEmployee(newEmployee);
    }

    @GetMapping("/all")
    public List<Employee> findAll(){
        return employeeService.callAllEmployee();
    }

    @GetMapping("{id}")
    public Employee findById(@PathVariable Long id){
        return employeeService.callEmployeeById(id);
    }

    @PutMapping("{id}")
    public void modify(@RequestBody Employee employee, @PathVariable Long id ){
        employeeService.modify(employee, id);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id){
        employeeService.remove(id);
    }
}
