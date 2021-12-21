package com.projekt.projekt.databases;



import com.projekt.projekt.tables.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDB extends JpaRepository<Employee, Integer> {
    
}
