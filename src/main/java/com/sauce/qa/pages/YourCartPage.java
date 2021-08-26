package com.sauce.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sauce.qa.base.TestBase;

public class YourCartPage extends TestBase {

	@FindBy(xpath = "//div[@class='inventory_item_name']")
	List<WebElement> prodItems;

	@FindBy(xpath = "//span[contains(text(), 'Your')]")
	List<WebElement> header;

	@FindBy(id = "continue-shopping")
	WebElement continueShopping;

	@FindBy(id = "checkout")
	WebElement checkout;

	@FindBy(xpath = "//span[@class='shopping_cart_badge']")
	List<WebElement> cartSize;

	public YourCartPage() {
		PageFactory.initElements(driver, this);
	}

	public Boolean validateTitleDisplayed() {
		Boolean flag;
		if (header.size() != 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public int validateCartSize() {
		int count = 0;

		ArrayList<String> sizeSt = new ArrayList<String>();

		if (cartSize.size() != 0) {
			for (WebElement e : cartSize) {
				sizeSt.add(e.getText());
			}

			count = Integer.parseInt(sizeSt.get(0));

		} else {
			count = 0;
		}
		return count;
	}

	public Boolean validateRemoveProduct() {

		ArrayList<String> rCart = new ArrayList<String>();
		ArrayList<String> remProd = new ArrayList<String>();
		Integer sbefore = validateCartSize();
		Boolean flag;

		rCart.add(prop.getProperty("removefromYourCart"));
		String sn;
		String sb;
		for (String s : rCart) {
			sn = s.toLowerCase();
			sb = sn.replace(" ", "-");
			String res[] = sb.split(",");

			for (int p = 0; p < res.length; p++) {
				remProd.add(res[p]);
			}
		}
		for (int i = 0; i < remProd.size(); i++) {
			String sp = remProd.get(i).trim();

			driver.findElement(By.xpath("//button[contains(@name,'remove-" + sp + "')]")).click();
		}

		Integer safter = validateCartSize();

		System.out.println("The size of cart after removing products " + safter);

		if (safter == (sbefore - remProd.size())) {

			flag = true;
			System.out.println("Item removed");

		} else {
			flag = false;
			System.out.println("Item not removed");

		}
		return flag;

	}

	public Boolean validateSizeAndItemsInCart() {
		Boolean flag;
		int size = prodItems.size();
		int cSize = validateCartSize();
		if (size == cSize) {
			flag = true;

		} else {
			flag = false;
		}
		return flag;
	}
	
	public ProductListPage validateContinueShopping() {
		continueShopping.click();
		return new ProductListPage();
	}
	
	public CheckoutPage validateCheckOut() {
		checkout.click();
		return new CheckoutPage();
	}

}
