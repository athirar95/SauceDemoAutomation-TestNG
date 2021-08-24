package com.sauce.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sauce.qa.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("C:\\Users\\Athira Ramachandran\\eclipse-workspace\\SauceDemo\\src\\main\\java\\com\\sauce\\qa\\config\\config.properties");
			prop.load(fis);
		}catch(IOException e) {
			e.getMessage();
		}
	}
	
	public static void initialization() {
		String browserName=prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Eclipse\\chromedriver_win32\\chromedriver.exe");
			
			driver = new ChromeDriver();
		}
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
	
	/*public static String createXpath(String xpathExp, Object ...args) {
		for(int i=0;i<args.length;i++) {
			xpathExp=xpathExp.replace("{"+i+"}", (CharSequence)args[i]);
		}
		return xpathExp;
	}*/
	

}
