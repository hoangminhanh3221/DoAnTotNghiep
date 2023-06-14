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
	
	@Column(name = "ProductName", columnDefinition = "nvarchar(50)", nullable = false)
	public String productName;
	
	@Column(name = "CostPrice", nullable = false)
	private Double costPrice;
	
	@Column(name = "SellingPrice", nullable = false)
	public Double sellingPrice;
	
	@Column(name = "Quantity", nullable = false)
	private Integer quantity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ArrivalDate", nullable = false)
	private Date arrivalDate;
	
	@Column(name = "ImageName1", columnDefinition = "varchar(50)", nullable = false)
	private String imageName1;
	
	@Column(name = "ImageName2", columnDefinition = "varchar(50)", nullable = false)
	private String imageName2;
	
	@Column(name = "ImageName3", columnDefinition = "varchar(50)", nullable = false)
	private String imageName3;
	
	@Column(name = "ImageName4", columnDefinition = "varchar(50)", nullable = false)
	private String imageName4;
	
	@Column(name = "Material", columnDefinition = "nvarchar(100)", nullable = false)
	private String material;
	
	@Column(name = "Description", columnDefinition = "nvarchar(MAX)", nullable = false)
	private String description;
	
	@Column(name = "Avaiable", nullable = false)
	private Boolean available;
	
	@ManyToOne
	@JoinColumn(name = "ColorId", referencedColumnName = "ColorId", nullable = false)
	private Color color;
	
	@ManyToOne
	@JoinColumn(name = "BrandId", referencedColumnName = "BrandId", nullable = false)
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "SizeId", referencedColumnName = "SizeId", nullable = false)
	private Size size;
	
	@ManyToOne
	@JoinColumn(name = "SubcategoryId", referencedColumnName = "SubcategoryId", nullable = false)
	private Subcategory subcategory;
	
	@ManyToOne
	@JoinColumn(name = "DiscountId", referencedColumnName = "DiscountId", nullable = false)
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
