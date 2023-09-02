package com.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codejava.model.Employees;

public interface EmployeesRepository extends JpaRepository <Employees, Long >{

}
