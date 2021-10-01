package com.planon.onlineshop;
public class PurchasedProducts {

	String purcode;
	int purqty;

	public PurchasedProducts(String purcode, int purqty) {
		this.purcode = purcode;
		this.purqty = purqty;

	}

	public String getPurchasedCode() {
		return purcode;
	}

	public	int getPurchasedQty() {
		return purqty;
	}

}