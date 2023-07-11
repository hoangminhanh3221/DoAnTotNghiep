package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;

public interface AccountService {
	
	Page<Account> findAllAccount(Pageable pageable);
	
	List<Account> findAllAccount();

	Optional<Account> findAccountById(String id);
	
	Account createAccount(Account account);

	Account updateAccount(Account account);

	void deleteAccount(String id);
	
}
