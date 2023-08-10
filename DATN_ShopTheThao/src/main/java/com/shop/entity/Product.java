package com.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.el.parser.AstFalse;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(name = "ProductName", columnDefinition = "nvarchar(50)", nullable = true)
	private String productName;
	
	@Column(name = "CostPrice", nullable = true)
	private Double costPrice;
	
	@Column(name = "SellingPrice", nullable = true)
	private Double sellingPrice;
	
	@Column(name = "QuantityLeft", nullable = true)
	private Integer quantityLeft;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ArrivalDate", nullable = true)
	private Date arrivalDate;
	
	@Column(name = "Material", columnDefinition = "nvarchar(100)", nullable = true)
	private String material;
	
	@Column(name = "Description", columnDefinition = "nvarchar(MAX)", nullable = true)
	private String description;
	
	@Column(name = "Avaiable", nullable = true)
	private Boolean available;
	
	@Column(name = "TotalQuantity", nullable = true)
	private Integer totalQuantity;
	
	@Column(name = "IsDeleted", nullable = true)
	private Boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name = "ColorId", referencedColumnName = "ColorId", nullable = true)
	private Color color;
	
	@ManyToOne
	@JoinColumn(name = "ImageId", referencedColumnName = "ImageId", nullable = true)
	private Image image;
	
	@ManyToOne
	@JoinColumn(name = "BrandId", referencedColumnName = "BrandId", nullable = true)
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "SizeId", referencedColumnName = "SizeId", nullable = true)
	private Size size;
	
	@ManyToOne
	@JoinColumn(name = "SubcategoryId", referencedColumnName = "SubcategoryId", nullable = true)
	private Subcategory subcategory;
	
	@ManyToOne
	@JoinColumn(name = "DiscountId", referencedColumnName = "DiscountId", nullable = true)
	private Discount discount;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Favorite> favorites;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Feedback> feedbacks;
	 
}