package com.shop.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BRAND")
public class Brand implements Serializable{
	private String brandId;
	private String brandName;
	private String origin;
	private List<Product> products;
}
 