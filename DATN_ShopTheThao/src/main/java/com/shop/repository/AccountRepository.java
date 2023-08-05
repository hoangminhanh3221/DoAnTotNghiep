package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entity.Account;

public interface AccountRepository  extends JpaRepository<Account, String>{

	
	@Query("Select o from Account o where o.email = ?1")
	Account findByEmail(String email);
	
	Account findByUsername(String username);
}
