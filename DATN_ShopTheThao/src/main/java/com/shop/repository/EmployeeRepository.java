package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Employee;
import com.shop.entity.Product;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

}
