package com.planon.onlineshop;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OnlineShopping {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		ProductHelper prod = new ProductHelper();

		File file = new File("C:\\Users\\dasant\\eclipse\\ECommerce\\src\\main\\resources\\ProductDetails1.txt"); // File where ProductDetails are stored
		List<Product> productList = prod.createListOfProducts(file); // create list of Products
		List<PurchasedProducts> cartList = new ArrayList<>(); // Used to store purchased Products
		Cart cart = new Cart(); // create Cart object
		int shopcount = 1; // Start shop
		try {

			while (shopcount > 0) {
				if (!productList.isEmpty()) // If ProductList created is not empty
				{
					prod.printProductDetails(productList); // Print the updatedList of Products Available

					System.out.println("Do You Want to Continue Shopping");
					System.out.println(" 1.Yes" + "   " + " 2. No");
					System.out.println("Enter ur choice(1/2) **1 to continue **2 to print Bill:");
					int ch = sc.nextInt();

					switch (ch) {
					case 1:
						System.out.print("Enter the ProductCode You want to shop:"); // Enter unique product code they
																						// are
																						// case Sensitive
						String productcode = sc.next();
						System.out.print("Enter the no of items of  model" + "  " + productcode + " :"); // Enter No of
																											// Products
																											// you
																											// want to
																											// buy
						int productqty = sc.nextInt();

						// get object of product purchased from ProductHelper according to availability

						PurchasedProducts productpurchased = prod.getProduct(productList, productcode, productqty);
						if (productpurchased == null) // if product purchased is not available
						{
							System.out.println("Please check your Productcode or Availability of Products");
						} else { // if product purchased is available add to cart
									// "cartObj.setPurshasedProduct(P1);"
							cartList = cart.setPurchasedProduct(productpurchased);
						}

						break;

					case 2:

						shopcount = -1; // Stop Shopping
						double totbill = cart.calculateTotalBill(cartList, productList);// Print Bill
						System.out.println("Total Bill:" + totbill);
						break;

					default:
						System.out.println("Please Mention 1/2:");

					}
				}

				else if (productList.isEmpty() & prod.filenotfound) {
					throw new NoProductsException("No Products as File not found..."); // If file not found and
																						// productList is empty

				} else if (productList.isEmpty() & !prod.filenotfound) {
					System.err.println("No Products as File is Empty..."); // If file found and productList is empty as
																			// file is empty
					break;
				}

			}
		} catch (NoProductsException e) {
			e.printStackTrace();
		} catch (InputMismatchException e)

		{
			System.out.println("Please check  input");
		}

	}

}