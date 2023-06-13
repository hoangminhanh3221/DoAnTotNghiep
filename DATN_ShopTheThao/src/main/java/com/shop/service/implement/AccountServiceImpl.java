package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Account;
import com.shop.repository.AccountRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
	
	@Override
	public Page<Account> findAllAccount(Pageable pageable) {
		return accountRepository.findAll(pageable);
	}

	@Override
	public List<Account> findAllAccount() {
		return accountRepository.findAll();
	}

	@Override
	public Optional<Account> findAccountById(String id) {
		return accountRepository.findById(id);
	}

	@Override
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account updateAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public void deleteAccount(String id) {
		accountRepository.deleteById(id);

	}

}
