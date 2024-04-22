package com.example.empolyee.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.empolyee.entity.Employee;
import com.example.empolyee.service.EmployeeService;
import com.example.empolyee.store.EmployeeStore;

@Service
public class EmployServiceLogic implements EmployeeService{
    @Autowired
    private EmployeeStore employeeStore;

    @Override
    public Long registerEmployee(Employee newEmployee) {
        return employeeStore.create(newEmployee);
    }

    @Override
    public List<Employee> callAllEmployee() {
        return employeeStore.callAll();
    }

    @Override
    public Employee callEmployeeById(Long id) {
        return employeeStore.callById(id);
    }

    @Override
    public void modify(Employee newEmployee, Long id) {
        Employee employee = employeeStore.callById(id);
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        employeeStore.update(employee);
    }

    @Override
    public void remove(Long id) {
        employeeStore.delete(id);
    }
}
