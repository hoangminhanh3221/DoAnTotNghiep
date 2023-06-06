package com.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FEEDBACK")
public class Feedback implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FeedbackId")
	private Integer feedbackId;
	
	@Column(name = "ReviewContent", columnDefinition = "nvarchar(100)", nullable = false)
	private String reviewContent;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ReviewDate", nullable = false)
	private Date reviewDate;
	
	@ManyToOne
	@JoinColumn(name = "ProductId", referencedColumnName = "ProductId", nullable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "Username", referencedColumnName = "Username", nullable = false)
	private Account account;
}
