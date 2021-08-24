package com.sauce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sauce.qa.base.TestBase;

public class LoginPage extends TestBase {

	@FindBy(id = "user-name")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "login-button")
	WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='bot_column']")
	WebElement loginImg;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public String validateLoginPageTitle() {
		return driver.getTitle();

	}
	
	public boolean validateImageDisplayed() {
		return loginImg.isDisplayed();
	}
	
	public ProductListPage login(String username1, String password1) {
		username.sendKeys(username1);
		password.sendKeys(password1);
		loginBtn.click();
		
		return new ProductListPage();
	}

}
