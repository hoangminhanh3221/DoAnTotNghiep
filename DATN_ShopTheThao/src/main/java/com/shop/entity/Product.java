package com.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.el.parser.AstFalse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable{
	
	@Id
	@Column(name = "ProductId", columnDefinition = "varchar(10)")
	private String productId;
	
	@Column(name = "ProductName", columnDefinition = "nvarchar(50)", nullable = false)
	private String productName;
	
	@Column(name = "CostPrice", nullable = false)
	private Double costPrice;
	
	@Column(name = "SellingPrice", nullable = false)
	private Double sellingPrice;
	
	@Column(name = "Quantity", nullable = false)
	private Integer quantity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ArrivalDate", nullable = false)
	private Date arrivalDate;
	
	@Column(name = "Material", columnDefinition = "nvarchar(100)", nullable = false)
	private String material;
	
	@Column(name = "Description", columnDefinition = "nvarchar(MAX)", nullable = false)
	private String description;
	
	@Column(name = "Avaiable", nullable = false)
	private Boolean available;
	
	@ManyToOne
	@JoinColumn(name = "ColorId", referencedColumnName = "ColorId", nullable = false)
	private Color color; 
	private Brand brand;
	private Image image;
	private Size size;
	private Subcategory subcategory;
	private Discount discount;
	private List<OrderDetail> orderDetails;
	private List<Favorite> favorites;
	private List<Feedback> feedbacks;
	 
}
