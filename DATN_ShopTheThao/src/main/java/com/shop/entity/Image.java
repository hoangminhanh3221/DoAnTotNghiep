package com.shop.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
<<<<<<< HEAD
=======
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
>>>>>>> update-entity
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
<<<<<<< HEAD
	@Column(name = "ImageId", columnDefinition = "varchar(10)")
	private String imageId;
=======
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ImageId")
	private Integer imageId;
>>>>>>> update-entity
	
	@Column(name = "ImageName1", columnDefinition = "varchar(100)", nullable = true)
	private String imageName1;
	
	@Column(name = "ImageName2", columnDefinition = "varchar(100)", nullable = true)
	private String imageName2;
	
	@Column(name = "ImageName3", columnDefinition = "varchar(100)", nullable = true)
	private String imageName3;
	
	@Column(name = "ImageName4", columnDefinition = "varchar(100)", nullable = true)
	private String imageName4;
	
	@JsonIgnore
	@OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
	private List<Product> products;
	
}
