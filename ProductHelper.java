package com.planon.onlineshop;

import java.io.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductHelper {

	public List<Product> productList = new ArrayList<>(); // List of Products and their Details Read from File

	boolean filenotfound = false;
	Cart cart = new Cart();

	public PurchasedProducts getProduct(List<Product> productList, String productcode, int productqty) {
		// return object of product which is available according to users quantity and
		// product code
		List<Product> res = productList.stream()
				.filter(prod -> prod.getprodCode().equals(productcode) && prod.getQty() >= productqty)
				.collect(Collectors.toList());
		for (Product prod : res) {

			int changedQty = prod.getQty() - productqty;
			prod.setQty(changedQty);
			PurchasedProducts p1 = new PurchasedProducts(productcode, productqty);
			System.out.println("Purchased" + " " + productqty + " Items with Product Name " + prod.getName()
					+ "  And Product code" + prod.getprodCode());

			return p1;
		}

		return null;

	}

	public List<Product> createListOfProducts(File file) { // Creating productList

		try {

			FileReader fr = new FileReader(file);
			BufferedReader in = new BufferedReader(fr);

			String line, arr[];

			while ((line = in.readLine()) != null) {
				arr = line.split(",");
				String name = arr[0];
				int qty = Integer.parseInt(arr[1]);
				String model = arr[2];
				Double price = Double.valueOf(arr[3]);

				Product product = new Product(name, qty, model, price);
				productList.add(product);
			}
			in.close();
		}

		catch (FileNotFoundException e) {
			filenotfound = true;

		} catch (IOException e) {
			System.out.println(e);
		} catch (NumberFormatException e) {

			System.out.println("Please Check the File Format");
		}
		return productList;
	}

	public void printProductDetails(List<Product> productList) throws NoProductsException { // Print Products and their
																							// Details
		if (productList.isEmpty()) {

			throw new NoProductsException("No Products");
		} else {
			System.out.println("******************PRODUCTS AVAILABLE****************************");
			System.out.println("ProductName" + " " + " Price " + " ProductCode " + "ProductQty");
			System.out.println("-------------------------------");

			productList.forEach(product -> System.out.println(product.getName() + "--> " + product.getPrice() + "--> "
					+ product.getprodCode() + " -->" + product.getQty()));
		}
	}

}
