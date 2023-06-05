package com.shop.entity;

import java.io.Serializable;
import java.util.Date;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderId")
	private Integer orderId;
	
	@Column(name = "OrderAmount", nullable = false)
	private Double OrderAmount;
	
	@Column(name = "Quantity", nullable = false)
	private Integer quantity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateDate", nullable = false)
	private Date createDate;
	
	@Column(name = "OrderStatus", columnDefinition = "nvarchar(50)", nullable = false)
	private String orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "Username", referencedColumnName = "Username", nullable = false)
	private Account account;
}
