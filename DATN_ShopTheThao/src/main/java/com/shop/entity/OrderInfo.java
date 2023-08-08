package com.shop.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ORDERINFO")
public class OrderInfo implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderInfoId")
	private Integer orderInfoId;
	
	@Column(name = "FullName", columnDefinition = "nvarchar(50)", nullable = true)
	private String fullName;
	
	@Column(name = "PhoneNumber", columnDefinition = "varchar(15)", nullable = true)
	private String phoneNumber;
	
	@Column(name = "Email", columnDefinition = "varchar(50)", nullable = true)
	private String email;
	
	@Column(name = "Description", columnDefinition = "nvarchar(MAX)", nullable = true)
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "orderInfo", cascade = CascadeType.ALL)
	private List<Order> orders;
	
	@ManyToOne
	@JoinColumn(name = "PaymentId", referencedColumnName = "PaymentId", nullable = true)
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "TransportId", referencedColumnName = "TransportId", nullable = true)
	private Transport transport;
	
	@ManyToOne
	@JoinColumn(name = "AddressId", referencedColumnName = "AddressId", nullable = true)
	private Address address;
	
}
