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
	private String discountId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "StartDate", nullable = true)
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EndDate", nullable = true)
	private Date endDate;
	
	@Column(name = "DiscountRate", nullable = true)
	private Integer discountRate;
	
	@Column(name = "Description", columnDefinition = "nvarchar(MAX)", nullable = true)
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "discount", cascade = CascadeType.ALL)
	private List<Product> products;
	
}
