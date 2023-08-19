package com.shop.entity;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "ORDERS")
public class Order implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderId")
	private Integer orderId;
	
	@Column(name = "OrderAmount", nullable = true)
	private Double OrderAmount;
	
	@Column(name = "Quantity", nullable = true)
	private Integer quantity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateDate", nullable = true)
	private Date createDate;
	
	@Column(name = "OrderStatus", columnDefinition = "nvarchar(50)", nullable = false)
	private String orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "Username", referencedColumnName = "Username", nullable = true)
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "OrderInfoId", referencedColumnName = "OrderInfoId", nullable = false)
	private OrderInfo orderInfo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;
	
}
