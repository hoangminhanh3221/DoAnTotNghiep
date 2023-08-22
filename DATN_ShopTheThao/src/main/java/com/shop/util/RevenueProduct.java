package com.shop.util;

public class RevenueProduct {
	String name;
	Double revenue;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRevenue() {
		return revenue;
	}
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	public RevenueProduct(String name, Double revenue) {
		super();
		this.name = name;
		this.revenue = revenue;
	}
	public RevenueProduct() {
		super();
	}
	
	
}
