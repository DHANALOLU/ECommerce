package com.planon.onlineshop;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	public List<PurchasedProducts> cartList = new ArrayList<>();

	public double calculateTotalBill(List<PurchasedProducts> cartList,List<Product> prolist) {
		Double totalbill = 0.0;
		System.out.println("Products Purchased");   // calculate bill

		System.out.println("------------------------------------------");

		for (PurchasedProducts purprod : cartList) {
			for (Product prod : prolist) {
				if (purprod.getPurchasedCode().equals(prod.getprodCode())) {
					totalbill = totalbill + (prod.getPrice() * purprod.getPurchasedQty());
					System.out.println(
							prod.getName() + "   " + purprod.getPurchasedCode() + " " + purprod.getPurchasedQty());
				}
			}
		}

		
		return totalbill;

	}  
		
	public List<PurchasedProducts> setPurchasedProduct(PurchasedProducts p1) {
		cartList.add(p1);  // add purchased products into cartList
		return cartList;
	}

}
	