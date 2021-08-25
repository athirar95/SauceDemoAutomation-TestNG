package com.sauce.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sauce.qa.base.TestBase;
import com.sauce.qa.pages.LoginPage;
import com.sauce.qa.pages.ProductDesciptionPage;
import com.sauce.qa.pages.ProductListPage;

public class ProductDescriptionPageTest extends TestBase {

	ProductListPage productList;
	LoginPage loginPage;
	ProductDesciptionPage productDesc;

	public ProductDescriptionPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new LoginPage();
		productList = new ProductListPage();
		productDesc = new ProductDesciptionPage();

		productList = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		productDesc = productList.selectProduct(prop.getProperty("productName"));
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1)
	public void validateImageDisplayedTest() {
		Boolean f = productDesc.validateImageDisplayed(prop.getProperty("productName"));
		Assert.assertTrue(f);
		System.out.println("Product image is displayed");
	}

	@Test(priority = 3)
	public void validateAddToCartTest() {
		Boolean f;
		f = productDesc.validateAddToCart();
		Assert.assertTrue(f);
	}

	@Test(priority = 4)
	public void removeFromCartTest() {
		Boolean f = productDesc.validateRemoveFromCart();
		Assert.assertTrue(f);
	}

	@Test(priority = 5)
	public void validateBackToProductsTest() {
		String actualTitle = "Swag Labs";
		productList = productDesc.validateBackToProducts();
		String title = driver.getTitle();
		System.out.println("title displayed is " + title);
		if (actualTitle.equalsIgnoreCase(title)) {
			System.out.println("Page title is as expected");
		} else {
			System.out.println("Page title is not as expected");
		}
		driver.navigate().back();
	}

	@Test(priority = 6)
	public void validateYourCartTest() {
		boolean f = productDesc.validateYourCartPage();
		Assert.assertTrue(f);
		driver.navigate().back();
	}

	@Test(priority = 2)
	public void validateProductTitleTest() {
		boolean f = productDesc.validateProductTitle(prop.getProperty("productName"));
		Assert.assertTrue(f);
	}

}
