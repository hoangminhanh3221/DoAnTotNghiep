package com.shop.service.implement;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Account;
import com.shop.repository.AccountRepository;
import com.shop.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private final AccountRepository aRep;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.aRep = accountRepository;
    }
	
	@Override
	public Page<Account> findAllAccount(Pageable pageable) {
		return aRep.findAll(pageable);
	}

	@Override
	public List<Account> findAllAccount() {
		return aRep.findAll();
	}

	@Override
	public Optional<Account> findAccountById(String id) {
		return aRep.findById(id);
	}

	@Override
	public Account createAccount(Account account) {
		return aRep.save(account);
	}

	@Override
	public Account updateAccount(Account account) {
		return aRep.save(account);
	}

	@Override	
	public void deleteAccount(String id) {
		aRep.deleteById(id);

	}

}
