package com.example.empolyee.store;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.empolyee.EmployeeNotFoundException;
import com.example.empolyee.entity.Employee;
import com.example.empolyee.store.Repository.EmployeeRepository;

@Repository
public class EmployeeStore {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Long create(Employee newEmployee){
        employeeRepository.save(newEmployee);
        return newEmployee.getId();
    }

    public List<Employee> callAll(){
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Employee callById(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()){
            throw new EmployeeNotFoundException(id);
        }
        return employee.get();
    }

    public void update(Employee newEmployee){
        employeeRepository.save(newEmployee);
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }
}
