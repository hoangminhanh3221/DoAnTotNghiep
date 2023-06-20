package com.shop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;
import com.shop.entity.Product;

public interface AccountRepository  extends JpaRepository<Account, String>{
	@Query(value = "SELECT c.CustomerId, c.Birthday, c.CustomerImage, c.CustomerName, c.Gender, c.PhoneNumber, ac.Username, c.AddressId FROM CUSTOMER c INNER JOIN ACCOUNT ac ON ac.Username = c.Username WHERE ac.Username = :username", nativeQuery = true)
	Object[] findAccountDetailsByUsername(String username);

}
