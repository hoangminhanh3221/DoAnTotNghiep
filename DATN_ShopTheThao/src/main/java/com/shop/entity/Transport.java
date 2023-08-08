package com.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(name = "TransportMethod", columnDefinition = "nvarchar(50)", nullable = true)
	private String transportMethod;
	
	@Column(name = "TransportFee", nullable = true)
	private Double transportFee;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TransportDate", nullable = true)
	private Date transportDate;
	 
	@Column(name = "TransportStatus", nullable = true)
	private Boolean transportStatus;
	
	@JsonIgnore
	@OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
	private List<OrderInfo> orderInfos;
}
