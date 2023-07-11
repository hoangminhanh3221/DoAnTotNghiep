package com.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.repository.AccountRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {
	
	@Id
	@Column(name = "Username", columnDefinition = "varchar(20)")
	private String username;
	
	@Column(name = "Password", columnDefinition = "varchar(20)", nullable = false)
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateDate", nullable = false)
	private Date createDate;
	
	@Column(name = "Email", columnDefinition = "varchar(50)", nullable = false, unique = true)
	private String email;
	
	@Column(name = "Role", columnDefinition = "varchar(20)", nullable = false)
	private String role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Customer> customers;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Employee> employees;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Order> orders;
}
public interface AccountService {
    void registerAccount(String username, String password);
    boolean login(String username, String password);
    void updateAccountInfo(String username, String newEmail, String newPassword);
    void deleteAccount(String username);
}

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerAccount(String username, String password) {
        // Thực hiện logic đăng ký tài khoản
        Account newAccount = new Account(username, password);
        accountRepository.save(newAccount);
    }

    @Override
    public boolean login(String username, String password) {
        // Thực hiện logic đăng nhập
        Account account = accountRepository.findByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public void updateAccountInfo(String username, String newEmail, String newPassword) {
        // Thực hiện logic cập nhật thông tin tài khoản
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            account.setEmail(newEmail);
            account.setPassword(newPassword);
            accountRepository.update(account);
        }
    }

    @Override
    public void deleteAccount(String username) {
        // Thực hiện logic xóa tài khoản
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            accountRepository.delete(account);
        }
    }
}
