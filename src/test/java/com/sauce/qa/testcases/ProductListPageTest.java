package com.sauce.qa.testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sauce.qa.base.TestBase;
import com.sauce.qa.pages.LoginPage;
import com.sauce.qa.pages.ProductDesciptionPage;
import com.sauce.qa.pages.ProductListPage;

public class ProductListPageTest extends TestBase {

	LoginPage loginPage;
	ProductListPage productList;
	ProductDesciptionPage productDesc;

	public ProductListPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		productList = new ProductListPage();

		productList = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1)
	public void verifyProductListTitleTest() {
		String title = productList.validateTitleProductList();
		Assert.assertEquals(title, "Swag Labs");
		System.out.println("Page title is " + title);
	}

	@Test(priority = 2)
	public void verifyFilterOptionsTest() {
		List<String> filter = new ArrayList<String>();
		List<String> toCompareFilList = new ArrayList<String>();
		filter.add("Name (A to Z)");
		filter.add("Name (Z to A)");
		filter.add("Price (low to high)");
		filter.add("Price (high to low)");
		toCompareFilList = productList.validateFilterOptions();
		System.out.println(toCompareFilList);
		Assert.assertEquals(filter, toCompareFilList);
		System.out.println("Filter options are displayed as expected");

	}

	@Test(priority = 3)
	public void validateSortingTest() {

		productList.validateFilter(prop.getProperty("filtervalue"));

		if (prop.getProperty("filtervalue").equalsIgnoreCase("Price (low to high)")) {
			Boolean flag = productList.validatePriceAscOrder();
			Assert.assertTrue(flag);
			System.out.println("The price is in correct order");

		} else if (prop.getProperty("filtervalue").equalsIgnoreCase("Price (high to low)")) {
			Boolean flag = productList.validatePriceDescOrder();
			Assert.assertTrue(flag);
			System.out.println("The price is in correct order");

		} else if (prop.getProperty("filtervalue").equalsIgnoreCase("Name (A to Z)")) {
			Boolean flag = productList.validateProductAscOrder();
			Assert.assertTrue(flag);
			System.out.println("The product name is in correct order");

		} else if (prop.getProperty("filtervalue").equalsIgnoreCase("Name (Z to A)")) {
			Boolean flag = productList.validateProductDescOrder();
			Assert.assertTrue(flag);
			System.out.println("The product name is in correct order");

		}

	}

	@Test(priority = 7)
	public void selectAProduct() throws InterruptedException {
		Thread.sleep(3000);
		productDesc = productList.selectProduct(prop.getProperty("productName"));
		Thread.sleep(2000);
		driver.navigate().back();

	}

	@Test(priority = 6)
	public void validateMenuOptionsTest() {
		productList.validateMenuOptions(prop.getProperty("menuoption"));
		driver.navigate().back();
	}

	@Test(priority = 4)
	public void addToCartTest() {
		if (prop.getProperty("addtocartproducts") != null && !prop.getProperty("addtocartproducts").isEmpty()) {
			Boolean f = productList.validateCartSize();
			Assert.assertTrue(f);
		} else {
			System.out.println("No tems to add to cart");
		}
	}

	@Test(priority = 5)
	public void removeFromCart() throws InterruptedException {
		Thread.sleep(2000);
		if (prop.getProperty("removefromcart") != null && !prop.getProperty("removefromcart").isEmpty()
				&& prop.getProperty("addtocartproducts") != null && !prop.getProperty("addtocartproducts").isEmpty()) {

			Boolean f = productList.validateRemoveFromCart();
			Assert.assertTrue(f);

		} else {
			System.out.println("No items to remove from cart");
		}
	}

}
