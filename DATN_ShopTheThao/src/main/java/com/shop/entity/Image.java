package com.shop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "IMAGE")
public class Image implements Serializable{
	private String imageId;
	private String imageName1;
	private String imageName2;
	private String imageName3;
	private String imageName4;
}
