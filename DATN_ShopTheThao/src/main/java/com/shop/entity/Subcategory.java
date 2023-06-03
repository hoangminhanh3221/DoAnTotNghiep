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
@Table(name = "SUBCATEGORY")
public class Subcategory implements Serializable{
	private String subcategoryId;
	private String subcategoryName;
	private Category category;
}
