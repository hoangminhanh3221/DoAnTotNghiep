package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Employee;
import com.shop.repository.EmployeeRepository;
import com.shop.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository EmployeeRepository;

    public EmployeeServiceImpl(EmployeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }

	@Override
	public Page<Employee> findAllEmployee(Pageable pageable) {
		return EmployeeRepository.findAll(pageable);
	}

	@Override
	public List<Employee> findAllEmployee() {
		return EmployeeRepository.findAll();
	}

	@Override
	public Optional<Employee> findEmployeeById(Integer id) {
		return EmployeeRepository.findById(id);
	}

	@Override
	public Employee createEmployee(Employee Employee) {
		return EmployeeRepository.save(Employee);
	}

	@Override
	public Employee updateEmployee(Employee Employee) {
		return EmployeeRepository.save(Employee);
	}

	@Override
	public void deleteEmployee(Integer id) {
		EmployeeRepository.deleteById(id);
		
	}
    
}
