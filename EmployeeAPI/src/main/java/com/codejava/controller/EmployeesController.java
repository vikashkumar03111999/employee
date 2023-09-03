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

	@GetMapping("/employees/{empid}")
	public ResponseEntity<Employees> getEmployeesId(@PathVariable long empid){
		Optional<Employees> emp = employeesRepository.findById(empid);
		if (emp.isPresent()) {
			return new ResponseEntity<Employees>(emp.get(),HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<Employees>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/employees/{empid}")
	public String updateEmployeesById(@PathVariable long empid, @RequestBody Employees employees) {
		Optional<Employees> emp = employeesRepository.findById(empid);
		if(emp.isPresent()) {
			Employees existEmp = emp.get();
			existEmp.setEmpid(employees.getEmpid());
			existEmp.setEmp_name(employees.getEmp_name());
			existEmp.setEmp_salary(employees.getEmp_salary());
			existEmp.setEmp_age(employees.getEmp_age());
			existEmp.setEmp_city(employees.getEmp_age());
			employeesRepository.save(existEmp);
			return "Employees Details against Id " + empid +" Updated ";
			
			
		}
		else {
			return "Employees Details does not exist for stuid  " + empid;
		}
	}
	@DeleteMapping("/employees/{empid}")
	public String deleteEmployeesByEmpId(@PathVariable Long empid) {
		employeesRepository.deleteById(empid);
		return "Employees Deleted Successfully";
	}
	@DeleteMapping ("/employees")
	public String deleteAllEmployees() {
		employeesRepository.deleteAll();
		return "Employees deleted Successfully...";
	}
}
	
	

	
	
}
