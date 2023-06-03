package com.shop.entity;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "FEEDBACK")
public class Feedback implements Serializable{
	private String feedbackId;
	private String reviewContent;
	private Date reviewDate;
	private Integer rating;
	private Product product;
	private Account account;
}
