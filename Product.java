package com.planon.onlineshop;


public class Product {

	private String name;
	private int qty;
	private String model;
	private Double price;

	public Product(String name, int qty, String model, Double price) {
		this.name = name;
		this.qty = qty;
		this.model = model;
		this.price = price;
	}
	public void setQty(int changeqty)
	{
		qty=changeqty;
	}

	public String getName() {
		return name;
	}

	public int getQty() {
		return qty;
	}

	public String getprodCode() {
		return model;
	}

	public Double getPrice() {
		return price;
	}

}