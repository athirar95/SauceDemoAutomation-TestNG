package com.sauce.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sauce.qa.base.TestBase;

public class CheckoutPage extends TestBase {

	@FindBy(xpath = "//span[contains(text(),'Checkout')]")
	List<WebElement> title;

	@FindBy(id = "first-name")
	WebElement firstName;

	@FindBy(id = "last-name")
	WebElement lastName;

	@FindBy(id = "postal-code")
	WebElement postalCode;

	@FindBy(id = "continue")
	WebElement continueBtn;

	@FindBy(id = "cancel")
	WebElement cancelBtn;

	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}

	public Boolean validateTitle() {
		Boolean flag;
		if (title.size() != 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public CheckOutOverviewPage validateOverView(String firstname, String lastname, String postcode) {
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		postalCode.sendKeys(postcode);
		continueBtn.click();
		return new CheckOutOverviewPage();

	}

	public ProductListPage validateCancel() {
		cancelBtn.click();
		return new ProductListPage();
	}
}
