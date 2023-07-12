package com.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "DISCOUNT")
public class Discount implements Serializable{
	
	@Id
	@Column(name = "DiscountId", columnDefinition = "varchar(10)")
	public String discountId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "StartDate", nullable = false)
	public Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EndDate", nullable = false)
	public Date endDate;
	
	@Column(name = "DiscountRate", nullable = false)
	public Integer discountRate;
	
	@Column(name = "Description", columnDefinition = "nvarchar(MAX)", nullable = false)
	public String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "discount", cascade = CascadeType.ALL)
	public List<Product> products;

	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	
	
}
