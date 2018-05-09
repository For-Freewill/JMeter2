package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AotuTest {
	public static Boolean runTest(String url, String su) throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		driver.get(url);
		Login loginObj = new Login(driver);
		loginObj.login("admin", "admin*1", su, "LOCAL");
		return true;
	}

}
