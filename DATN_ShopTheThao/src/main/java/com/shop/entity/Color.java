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
@Table(name = "COLOR")
public class Color implements Serializable{
	
	@Id
	@Column(name = "ColorId", columnDefinition = "varchar(10)")
	private String colorId;
	
	@Column(name = "ColorName", columnDefinition = "nvarchar(50)", nullable = true)
	private String colorName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "color", cascade = CascadeType.ALL)
	private List<Product> products;

	public Color(String colorId, String colorName) {
		super();
		this.colorId = colorId;
		this.colorName = colorName;
	}   
	
	
}
