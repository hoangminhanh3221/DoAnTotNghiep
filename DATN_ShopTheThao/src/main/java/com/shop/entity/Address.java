package com.shop.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AddressId")
	private Integer addressId;
	
	@Column(name = "NumberHome", columnDefinition = "nvarchar(100)", nullable = true)
	private String numberHome;
	
	@Column(name = "Ward", columnDefinition = "nvarchar(100)", nullable = true)
	private String ward;
	
	@Column(name = "District", columnDefinition = "nvarchar(100)", nullable = true)
	private String district;
	
	@Column(name = "City", columnDefinition = "nvarchar(100)", nullable = true)
	private String city;
	
	@JsonIgnore
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
	private List<Customer> customers;
	
	@JsonIgnore
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
	private List<Employee> employees;
	
	@JsonIgnore
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
	private List<OrderInfo> orderInfos;
	
}
