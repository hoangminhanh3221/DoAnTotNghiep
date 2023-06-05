package com.shop.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "BRAND")
public class Brand implements Serializable{
	
	@Id
	@Column(name = "BrandId", columnDefinition = "varchar(10)")
	private String brandId;
	
	@Column(name = "BrandName", columnDefinition = "nvarchar(50)", nullable = false)
	private String brandName;
	
	@Column(name = "Origin", columnDefinition = "nvarchar(50)", nullable = false)
	private String origin;
	
	@JsonIgnore
	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
	private List<Product> products;
}
 