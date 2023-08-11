package com.shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERDETAIL")
public class OrderDetail implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderDetailId")
	private Integer x;
	
	@Column(name = "ProductPrice", nullable = true)
	private Double productPrice;
	
	@Column(name = "ProductQuantity", nullable = true)
	private Integer productQuantity;
	
	@ManyToOne
	@JoinColumn(name = "orderId", referencedColumnName = "orderId", nullable = true)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "productId", referencedColumnName = "productId", nullable = true)
	private Product product;
}
