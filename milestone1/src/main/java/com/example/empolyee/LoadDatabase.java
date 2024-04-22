package com.example.empolyee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.empolyee.entity.Employee;
import com.example.empolyee.store.Repository.EmployeeRepository;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Employee("Pang","teacher")));
      log.info("Preloading " + repository.save(new Employee("Taejun", "student")));
    };
  }
}