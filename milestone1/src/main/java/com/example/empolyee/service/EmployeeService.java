package com.example.empolyee.service;

import java.util.List;

import com.example.empolyee.entity.Employee;

public interface EmployeeService {
    Long registerEmployee(Employee newEmployee);
    List<Employee> callAllEmployee();
    Employee callEmployeeById(Long id);
    void modify(Employee newEmployee, Long id);
    void remove(Long id);
}
