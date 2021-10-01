package com.planon.onlinejunit;

import org.junit.*;

import com.planon.onlineshop.*;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;



import java.io.File;

import java.util.*;

public class ShopJUnit {

	static ProductHelper prodhelp;
	static Cart cart;
	List<Product> productList = new ArrayList<>();
	
 
	@BeforeClass
	public static void classBeforetest() {
		prodhelp = new ProductHelper();
		cart = new Cart();
	}

	@Before
	public void Beforetest() {
		
	Product p = new Product("Dell", 10, "P001", 25000.00);
	Product p1 = new Product("Hp", 20, "P002", 30000.00);
	Product p2 = new Product("Asus", 25, "P003", 35000.00);
	productList.add(p);
	productList.add(p1);
	productList.add(p2);
	}

	@Test
	public void testgetProduct()

	{  
		PurchasedProducts prod = prodhelp.getProduct(productList, "P001", 10);
		String prodcode = prod.getPurchasedCode();
		assertEquals("No Product With Productcode or Quantity", "P001", prodcode);
	}

	@Test
	public void testcreateListOfProductPositive() {
		File file = new File("ProductDetails.txt");
		List<Product> pl = prodhelp.createListOfProducts(file);
		List<String> ProductCodeList = new ArrayList<>();
		for (Product x : pl) {
			ProductCodeList.add(x.getprodCode());
		}
	   
				assertThat(ProductCodeList, hasItems("P001", "P002", "P003", "P004", "P005", "P006", "P007"));
	}
	@Test
	public void testcreateListOfProductNegative() {
		File file1 = new File("Produc65ghg.txt");
		List<Product> pl = prodhelp.createListOfProducts(file1);
		
		assertTrue(pl.isEmpty());
	}
	
	@Test
	public void testsetPurchasedProducts() {
   
		List<PurchasedProducts> cartlist = new ArrayList<>();
		
		PurchasedProducts purprod = new PurchasedProducts("P001", 1);
		 cartlist = cart.setPurchasedProduct(purprod);
		List<String> cartListprodcodeList = new ArrayList<>();
		for (PurchasedProducts x : cartlist) {
			cartListprodcodeList.add(x.getPurchasedCode());
		}
		assertThat(cartListprodcodeList, hasItems("P001"));
	}
	
	@Test
	public void testcalculateTotalBill()
	{   
		List<PurchasedProducts> cartList1 = new ArrayList<>();
		
		PurchasedProducts purprod1 = new PurchasedProducts("P001",1);
		cartList1.add(purprod1);
		
		
		double totbill= cart.calculateTotalBill(cartList1, productList);
		assertTrue("Expected and Actual Bill is Not Matching",totbill-25000==0);
	}
	
	@Test(expected=NoProductsException.class)
	public void testprintProductDetailsNegative() throws NoProductsException
	
	  { 
		 List<Product> prol = new ArrayList<>();
		 
		  prodhelp.printProductDetails(prol);
	 }

     @Test
	public void testprintProductDetailsPositivetive() throws NoProductsException 
	
	  { 
		 
		  prodhelp.printProductDetails(productList);
	 }
	

}
