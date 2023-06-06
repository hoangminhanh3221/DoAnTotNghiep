package com.shop.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "IMAGE")
public class Image implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ImageId")
	private String imageId;
	
	@Column(name="ImageImage1", columnDefinition = "varchar(20)", nullable = false)
	private String imageName1;
	
	@Column(name="ImageImage2", columnDefinition = "varchar(20)", nullable = false)
	private String imageName2;
	
	@Column(name="ImageImage3", columnDefinition = "varchar(20)", nullable = false)
	private String imageName3;
	
	@Column(name="ImageImage4", columnDefinition = "varchar(20)", nullable = false)
	private String imageName4;
	
	@JsonIgnore
	@OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
	private List<Product> products;
}
