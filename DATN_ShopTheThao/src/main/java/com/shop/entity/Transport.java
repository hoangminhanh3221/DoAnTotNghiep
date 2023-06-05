package com.shop.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSPORT")
public class Transport implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TransportId")
	private Integer transportId;
	
	@Column(name = "TransportMethod", columnDefinition = "nvarchar(50)", nullable = false)
	private String transportMethod;
	
	@Column(name = "TransportFee", nullable = false)
	private Double transportFee;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TransportDate")
	private Date transportDate;
	 
	@Column(name = "TransportStatus", nullable = false)
	private Boolean transportStatus;
	
	@ManyToOne
	@JoinColumn(name = "OrderId", referencedColumnName = "OrderId", nullable = false)
	private Order order;
}
