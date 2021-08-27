package com.sauce.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sauce.qa.base.TestBase;

public class CheckOutOverviewPage extends TestBase {

	@FindBy(xpath = "//div[@class='inventory_item_price']")
	List<WebElement> cost;

	@FindBy(xpath = "//div[@class='inventory_item_name']")
	List<WebElement> finItems;

	@FindBy(xpath = "//div[@class='summary_subtotal_label']")
	WebElement itemTotal;

	@FindBy(xpath = "//div[@class='summary_tax_label']")
	WebElement tax;

	@FindBy(xpath = "//div[@class='summary_total_label']")
	WebElement totalCost;

	@FindBy(id = "cancel")
	WebElement cancel;

	@FindBy(id = "finish")
	WebElement finish;

	public CheckOutOverviewPage() {
		PageFactory.initElements(driver, this);
	}

	public Double validateTotalItems() {
		List<Double> realPrice = new ArrayList<Double>();
		List<String> convPrice = new ArrayList<String>();
		List<String> pricelst = new ArrayList<String>();
		Double sum = 0.0;
		for (WebElement e : cost) {
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
		for (int k = 0; k < realPrice.size(); k++) {
			sum += realPrice.get(k);

		}

		return sum;
	}

	public Boolean validateTotalCostProducts() {

		Boolean flag;

		String st = itemTotal.getText();

		// item Total
		String sparts[] = st.split(":");
		String s = sparts[1];
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '$') {
				StringBuilder sb = new StringBuilder(s);
				sb.deleteCharAt(i);
				s = sb.toString().trim();

			}
		}

		Double itemCost = Double.parseDouble(s);

		System.out.println("Item cost displayed " + itemCost);

		Double calCost = validateTotalItems();
		System.out.println("item cost calculated is " + calCost);

		if (Double.compare(itemCost, calCost) == 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;

	}

	public Boolean validateTotalCost() {
		Boolean flag;
		String tt = tax.getText();
		String tc = totalCost.getText();
		// tax amt
		String ttparts[] = tt.split(":");
		String tp = ttparts[1];
		for (int i = 0; i < tp.length(); i++) {
			if (tp.charAt(i) == '$') {
				StringBuilder sb = new StringBuilder(tp);
				sb.deleteCharAt(i);
				tp = sb.toString();

			}
		}

		Double tax = Double.parseDouble(tp);
		System.out.println("Tax amount is " + tax);
		// total cost
		String tcparts[] = tc.split(":");
		String tcc = tcparts[1];
		for (int i = 0; i < tcc.length(); i++) {
			if (tcc.charAt(i) == '$') {
				StringBuilder sb = new StringBuilder(tcc);
				sb.deleteCharAt(i);
				tcc = sb.toString();

			}
		}

		Double totalCost = Double.parseDouble(tcc);
		System.out.println("Total cost displyed is " + totalCost);
		Double itemCost = validateTotalItems();
		System.out.println("Total price calculated is " + (itemCost + tax));

		if ((itemCost + tax) == totalCost) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;

	}
	
	public CheckOutCompletePage validateFinish() {
		finish.click();
		return new CheckOutCompletePage();
		
	}
	
	public ProductListPage validateCancel() {
		cancel.click();
		return new ProductListPage();
	}
}
