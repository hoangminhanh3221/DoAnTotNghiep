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
