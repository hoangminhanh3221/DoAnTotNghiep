package com.shop.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FAVORITE")
public class Favorite implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FavoriteId")
	private Integer favoriteId;
	
	@ManyToOne
	@JoinColumn(name = "Username", referencedColumnName = "Username", nullable = false)
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "ProductId", referencedColumnName = "ProductId", nullable = false)
	private Product product;
	
}
