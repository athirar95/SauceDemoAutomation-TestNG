package com.sauce.qa.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.sauce.qa.base.TestBase;

public class ProductListPage extends TestBase {

	@FindBy(id = "react-burger-menu-btn")
	WebElement menuBtn;

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	WebElement cart;

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	WebElement addToCart;

	@FindBy(xpath = "//div[@class=\"inventory_item_price\"]")
	List<WebElement> price;

	@FindBy(xpath = "//select[@class=\"product_sort_container\"]")
	WebElement filterBtn;

	@FindBy(xpath = "//div[@class='inventory_item_name']")
	List<WebElement> productName;

	@FindBy(id = "inventory_sidebar_link")
	WebElement allItems;

	@FindBy(id = "about_sidebar_link")
	WebElement about;

	@FindBy(id = "logout_sidebar_link")
	WebElement logout;

	@FindBy(id = "reset_sidebar_link")
	WebElement reset;

	@FindBy(xpath = "//span[@class='shopping_cart_badge']")
	WebElement cartSize;
	
	@FindBy(xpath="//button[contains(text(),'Remove')]")
	List<WebElement> removeLst;
	
	@FindBy(xpath="//span[contains(text(),'Your Cart')]")
	List<WebElement> yourCartTitle;
	
	public ProductListPage() {
		PageFactory.initElements(driver, this);
	}

	public String validateTitleProductList() {
		return driver.getTitle();
	}

	public List<Double> validatePrice() {
		List<Double> realPrice = new ArrayList<Double>();
		List<String> convPrice = new ArrayList<String>();
		List<String> pricelst = new ArrayList<String>();
		for (WebElement e : price) {
			pricelst.add(e.getText());

		}
		for (String s : pricelst)

			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '$') {
					StringBuilder sb = new StringBuilder(s);
					sb.deleteCharAt(i);
					s = sb.toString();
					convPrice.add(s);
				}
			}

		for (int j = 0; j < convPrice.size(); j++) {
			Double d = Double.parseDouble(convPrice.get(j));
			realPrice.add(d);
		}

		return realPrice;

	}

	public List<String> validateFilterOptions() {

		Select filterOptns = new Select(filterBtn);
		List<WebElement> optns = filterOptns.getOptions();
		List<String> fil = new ArrayList<String>();
		for (WebElement e : optns) {
			fil.add(e.getText());

		}
		return fil;

	}

	public void validateFilter(String value) {
		Select filterOptns = new Select(filterBtn);
		filterOptns.selectByVisibleText(value);
	}

	public ProductDesciptionPage selectProduct(String productName) {
		driver.findElement(By.xpath("//div[contains(text(),'" + productName + "')]")).click();
		return new ProductDesciptionPage();
	}

	public YourCartPage validateCartPage() {
		addToCart.click();
		return new YourCartPage();
	}

	public void validateMenuOptions(String menuOption) {
		menuBtn.click();

		if (menuOption.equalsIgnoreCase("About")) {
			about.click();
		}

		else if (menuOption.equalsIgnoreCase("Logout")) {
			logout.click();

		} else if (menuOption.equalsIgnoreCase("Reset App state")) {
			reset.click();
		}

		else if (menuOption.equalsIgnoreCase("All items")) {
			allItems.click();
		}
	}

	public Boolean validatePriceAscOrder() {
		Boolean flag;

		List<Double> ascPrice = validatePrice();
		System.out.println("Price displayed in screen is" + ascPrice);

		Collections.sort(ascPrice);
		System.out.println("Price in ascending order is " + ascPrice);
		if (ascPrice.equals(validatePrice())) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;

	}

	public Boolean validatePriceDescOrder() {
		Boolean flag;
		List<Double> descPrice = validatePrice();
		System.out.println("Price displayed in screen is" + descPrice);
		Collections.sort(descPrice, Collections.reverseOrder());
		System.out.println("Price in descending order is " + descPrice);
		if (descPrice.equals(validatePrice())) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;

	}

	public List<String> validateProductNameList() {
		List<String> productLst = new ArrayList<String>();
		for (WebElement e : productName) {
			productLst.add(e.getText());

		}

		return productLst;
	}

	public Boolean validateProductAscOrder() {
		Boolean flag;

		List<String> ascPrdct = validateProductNameList();
		System.out.println("Product names displayed in screen is" + ascPrdct);

		Collections.sort(ascPrdct);
		System.out.println("Product names in ascending order is " + ascPrdct);
		if (ascPrdct.equals(validateProductNameList())) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public Boolean validateProductDescOrder() {
		Boolean flag;

		List<String> descPrdct = validateProductNameList();
		System.out.println("Product names displayed in screen is" + descPrdct);

		Collections.sort(descPrdct, Collections.reverseOrder());
		System.out.println("Product names descending order is " + descPrdct);
		if (descPrdct.equals(validateProductNameList())) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public ArrayList<String> validateAddToCart() {
		ArrayList<String> prod = new ArrayList<String>();
		ArrayList<String> newProd = new ArrayList<String>();
		String sb;
		String sn;
		prod.add(prop.getProperty("addtocartproducts"));

		for (String s : prod) {
			sn = s.toLowerCase();
			sb = sn.replace(" ", "-");
			String[] res = sb.split(",");

			for (int p = 0; p < res.length; p++) {
				newProd.add(res[p]);
			}
		}
		return newProd;
	}

	public Boolean validateCartSize() {

		ArrayList<String> finProd = new ArrayList<String>();
		Boolean flag;

		finProd = validateAddToCart();
		for (int i = 0; i < finProd.size(); i++) {
			String sp = finProd.get(i);

			driver.findElement(By.xpath("//button[contains(@name,'" + sp + "')]")).click();
		}

		Integer s = Integer.parseInt((cartSize).getText());

		System.out.println("The size of cart is " + s);

		if (finProd.size() == s) {

			flag = true;
			System.out.println("Cart no is valid");

		} else if ((finProd.size() - s) != 0) {
			int sr = removeLst.size();
			if(sr==s) {
				flag=true;
				System.out.println("Cart no is valid");
			}
			else {
				flag=false;
				System.out.println("Cart no is not valid");
			}
			
			

		} else {
			flag = false;
			System.out.println("Cart no is not valid");

		}
		return flag;

	}

	public Boolean validateRemoveFromCart() {

		ArrayList<String> rCart = new ArrayList<String>();
		ArrayList<String> remProd = new ArrayList<String>();
		Integer sbefore = Integer.parseInt((cartSize).getText());
		Boolean flag;
		rCart.add(prop.getProperty("removefromcart"));
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
			String sp = remProd.get(i);

			driver.findElement(By.xpath("//button[contains(@name,'" + sp + "')]")).click();
		}

		Integer safter = Integer.parseInt((cartSize).getText());

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
	
	public YourCartPage validateYourCartPage() {
		cart.click();
		return new YourCartPage();
	}

}
