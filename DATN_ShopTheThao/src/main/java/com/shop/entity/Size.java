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
@Table(name = "SIZE")
public class Size implements Serializable{
	
	@Id
	@Column(name = "SizeId", columnDefinition = "varchar(10)")
	private String sizeId;
	
	@Column(name = "SizeName", columnDefinition = "varchar(10)", nullable = true)
	private String sizeName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
	private List<Product> products;
}
