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
	
	@ManyToOne
	@JoinColumn(name = "ImageId", referencedColumnName = "ImageId", nullable = false)
	private Image image;
	
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	 
	
	
}
