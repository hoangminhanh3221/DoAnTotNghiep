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
@Table(name = "ORDERINFO")
public class OrderInfo implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderInfoId")
	private Integer orderInfoId;
	
	private String fullName;
	private String phoneNumber;
	private String email;
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "orderInfo", cascade = CascadeType.ALL)
	private List<Payment> payments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "orderInfo", cascade = CascadeType.ALL)
	private List<Transport> transports;
	
}
