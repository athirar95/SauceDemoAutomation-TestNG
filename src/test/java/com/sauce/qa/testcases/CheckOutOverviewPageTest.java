package com.sauce.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sauce.qa.base.TestBase;
import com.sauce.qa.pages.CheckOutCompletePage;
import com.sauce.qa.pages.CheckOutOverviewPage;
import com.sauce.qa.pages.CheckoutPage;
import com.sauce.qa.pages.LoginPage;
import com.sauce.qa.pages.ProductDesciptionPage;
import com.sauce.qa.pages.ProductListPage;
import com.sauce.qa.pages.YourCartPage;

public class CheckOutOverviewPageTest extends TestBase {

	LoginPage loginPage;
	ProductListPage productList;
	ProductDesciptionPage productDesc;
	YourCartPage yourCart;
	CheckoutPage checkOut;
	CheckOutOverviewPage checkOutOverview;
	CheckOutCompletePage checkOutComplete;

	public CheckOutOverviewPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		productList = new ProductListPage();
		productDesc = new ProductDesciptionPage();
		yourCart = new YourCartPage();
		checkOut = new CheckoutPage();
		checkOutOverview = new CheckOutOverviewPage();
		checkOutComplete = new CheckOutCompletePage();

		productList = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

		yourCart = productList.validateCartPage();
		checkOut = yourCart.validateCheckOut();
		checkOutOverview = checkOut.validateOverView(prop.getProperty("firstname"), prop.getProperty("lastname"),
				prop.getProperty("postalcode"));

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1)
	public void validateTotalCostTest() {
		Boolean f = checkOutOverview.validateTotalCostProducts();
		Assert.assertTrue(f);
	}

	@Test(priority=2)
	public void validateFinalCostTest() {
		Boolean f = checkOutOverview.validateTotalCost();
		Assert.assertTrue(f);
	}

	@Test(priority=3)
	public void validateCompletePaymentTest() {
		String actualTitle = "Swag Labs";
		checkOutComplete = checkOutOverview.validateFinish();
		String title = driver.getTitle();
		System.out.println("title displayed is " + title);
		if (actualTitle.equalsIgnoreCase(title)) {
			System.out.println("Page title is as expected");
		} else {
			System.out.println("Page title is not as expected");
		}
		driver.navigate().back();
	}

	@Test(priority=4)
	public void validateCancelTest() {
		String actualTitle = "Swag Labs";
		productList = checkOutOverview.validateCancel();
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
