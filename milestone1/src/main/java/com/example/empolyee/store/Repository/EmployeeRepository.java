package com.example.empolyee.store.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.empolyee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
