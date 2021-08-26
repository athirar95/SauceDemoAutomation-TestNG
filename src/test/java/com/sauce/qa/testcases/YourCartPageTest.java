package com.sauce.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sauce.qa.base.TestBase;
import com.sauce.qa.pages.CheckoutPage;
import com.sauce.qa.pages.LoginPage;
import com.sauce.qa.pages.ProductDesciptionPage;
import com.sauce.qa.pages.ProductListPage;
import com.sauce.qa.pages.YourCartPage;

public class YourCartPageTest extends TestBase {

	LoginPage loginPage;
	ProductListPage productList;
	ProductDesciptionPage productDesc;
	YourCartPage yourCart;
	CheckoutPage checkOut;

	public YourCartPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		productList = new ProductListPage();
		productDesc = new ProductDesciptionPage();
		yourCart = new YourCartPage();
		checkOut= new CheckoutPage();

		productList = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

		yourCart = productList.validateCartPage();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void validateTitleDisplayedTest() {
		Boolean f = yourCart.validateTitleDisplayed();
		Assert.assertTrue(f);
	}

	@Test(priority = 2)
	public void validateSizeAndItemsInCartTest() {
		Boolean f = yourCart.validateSizeAndItemsInCart();
		Assert.assertTrue(f);

	}

	@Test(priority = 3)
	public void validateRemoveProductTest() {
		if (prop.getProperty("removefromYourCart") != null && !prop.getProperty("removefromYourCart").isEmpty()) {
			Boolean f = yourCart.validateRemoveProduct();
			Assert.assertTrue(f);
		} else {
			System.out.println("No items to remove");
		}
	}
	
	@Test(priority=4)
	public void validateReturnShoppingTest() {
		String actualTitle = "Swag Labs";
		productList=yourCart.validateContinueShopping();
		String title = driver.getTitle();
		System.out.println("title displayed is " + title);
		if (actualTitle.equalsIgnoreCase(title)) {
			System.out.println("Page title is as expected");
		} else {
			System.out.println("Page title is not as expected");
		}
		driver.navigate().back();
		
	}
	
	@Test(priority=5)
	public void validateCheckOutTest() throws InterruptedException {
		Thread.sleep(3000);
		String actualTitle = "Swag Labs";
		checkOut=yourCart.validateCheckOut();
		String title = driver.getTitle();
		System.out.println("title displayed is " + title);
		if (actualTitle.equalsIgnoreCase(title)) {
			System.out.println("Page title is as expected");
		} else {
			System.out.println("Page title is not as expected");
		}
		driver.navigate().back();
		
		
		
	}

}
