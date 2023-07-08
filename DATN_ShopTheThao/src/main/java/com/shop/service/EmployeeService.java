package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Employee;

public interface EmployeeService {
	
	Page<Employee> findAllEmployee(Pageable pageable);
	
	List<Employee> findAllEmployee();

	Optional<Employee> findEmployeeById(Integer id);
	
	Employee createEmployee(Employee employee);

	Employee updateEmployee(Employee employee);

	void deleteEmployee(Integer id);
	
}
