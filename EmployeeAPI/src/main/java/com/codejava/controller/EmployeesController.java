package com.codejava.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codejava.model.Employees;
import com.codejava.repository.EmployeesRepository;

@RestController
@RequestMapping("/api")
public class EmployeesController {

	@Autowired
	EmployeesRepository employeesRepository ;
	
	@PostMapping("/employees")
	public String createNewEmployees(@RequestBody Employees employees) {
		employeesRepository.save(employees);
		return "Employyes created in database";
		
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employees>> getAllEmployees(){
		
		List<Employees> empList = new ArrayList<>();
		employeesRepository.findAll().forEach(empList::add);
		return new ResponseEntity<List<Employees>>(empList,HttpStatus.OK);
		
		
	}
	
	
}
