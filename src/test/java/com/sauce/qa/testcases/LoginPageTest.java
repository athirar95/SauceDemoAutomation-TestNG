package com.sauce.qa.testcases;

//import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sauce.qa.base.TestBase;
import com.sauce.qa.pages.LoginPage;
import com.sauce.qa.pages.ProductListPage;

public class LoginPageTest extends TestBase {

	public LoginPageTest() {
		super();
	}

	LoginPage loginPage;
	ProductListPage productListPage;

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void loginPageTitleTest() {
		String actualTitle = "Swag Labs";
		String title = loginPage.validateLoginPageTitle();
		System.out.println("title displayed is " + title);
		if (actualTitle.equalsIgnoreCase(title)) {
			System.out.println("Page title is as expected");
		} else {
			System.out.println("Page title is not as expected");
		}
	}

	@Test
	public void imageTest() {
		Assert.assertTrue(loginPage.validateImageDisplayed());
		System.out.println("Image displayed");
	}

	@Test
	public void loginTest() throws InterruptedException {
		Thread.sleep(3000);
		productListPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}
}
