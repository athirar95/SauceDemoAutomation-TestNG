package com.sauce.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sauce.qa.base.TestBase;

public class ProductDesciptionPage extends TestBase {

	// String productName;

	@FindBy(name = "back-to-products")
	WebElement backBtn;

	@FindBy(xpath = "//button[contains(text(),'Add to cart')]")
	WebElement addToCart;

	@FindBy(xpath = "//span[@class='shopping_cart_badge']")
	List<WebElement> cartIcon;
	
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	WebElement yourCart;
	
	@FindBy(xpath="//button[contains(text(),'Remove')]")
	WebElement removeCart;
	
	@FindBy(xpath="//span[contains(text(),'Your Cart')]")
	List<WebElement> yourCartTitle;
	
	
	public ProductDesciptionPage() {
		PageFactory.initElements(driver, this);
	}

	public Boolean validateImageDisplayed(String prodName) {
		Boolean f = driver.findElement(By.xpath("//img[@alt='" + prodName + "']")).isDisplayed();
		return f;

	}

	public Boolean validateProductTitle(String prodName) {
		Boolean f = driver.findElement(By.xpath("//div[contains(text(),'" + prodName + "')]")).isDisplayed();
		return f;
	}

	public int validateCartCount() {
		int count = 0;

		ArrayList<String> sizeSt = new ArrayList<String>();

		if (cartIcon.size() != 0) {
			for (WebElement e : cartIcon) {
				sizeSt.add(e.getText());
			}

			 count = Integer.parseInt(sizeSt.get(0));
			

		} else {
			count = 0;
		}
		return count;
	}

	public Boolean validateAddToCart() {
		int countB = 0;
		int countA = 0;
		Boolean flag;
		   countB=validateCartCount();

			addToCart.click();
			countA=validateCartCount();

			if (countA == (countB + 1)) {

				flag = true;
				System.out.println("Cart no is valid");

			} else {
				flag = false;
				System.out.println("cart no is not valid");
			}
			return flag;

		}
	public Boolean validateYourCartPage() {
		Boolean flag;
		yourCart.click();
		if(yourCartTitle.size()!=0) {
			flag=true;
		}else {
			flag=false;
		}
		return flag;
	}
	
	public Boolean validateRemoveFromCart() {
		Boolean flag;
		int countB=validateCartCount();
		removeCart.click();
		int countA=validateCartCount();
		
		if(countA==(countB-1)) {
			flag=true;
			System.out.println("Cart no is valid");
			
		}
		else {
			flag=false;
			System.out.println("Cart no is not valid");
		}
		return flag;
	}
	
	public ProductListPage validateBackToProducts() {
		backBtn.click();
		return new ProductListPage();
		
	}

	

}
