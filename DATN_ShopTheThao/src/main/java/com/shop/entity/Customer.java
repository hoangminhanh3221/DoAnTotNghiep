package com.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CUSTOMER")
public class Customer implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CustomerId")
	private Integer customerId;
	
	@Column(name = "CustomerName", columnDefinition = "nvarchar(50)", nullable = false)
	private String customerName;
	
	@Column(name = "Gender", nullable = false)
	private Boolean gender;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Birthday", nullable = false)
	private Date birthday;
	
	@Column(name = "PhoneNumber", columnDefinition = "varchar(15)", nullable = false)
	private String phoneNumber;
	
	@Column(name = "Address", columnDefinition = "nvarchar(20)", nullable = false)
	private String address;
	
	@Column(name = "Street", columnDefinition = "nvarchar(20)", nullable = false)
	private String street;
	
	@Column(name = "Ward", columnDefinition = "nvarchar(20)", nullable = false)
	private String ward;
	
	@Column(name = "District", columnDefinition = "nvarchar(20)", nullable = false)
	private String district;
	
	@Column(name = "City", columnDefinition = "nvarchar(20)", nullable = false)
	private String city;
	
	@ManyToOne
	@JoinColumn(name = "Username", referencedColumnName = "Username", nullable = false)
	private Account account;
}
