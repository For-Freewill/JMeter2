package com.test;

import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wulei
 * @date 2018-4-26
 * 
 */
public class Login {
	private WebDriver driver;

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);//初始化元素时等待时间
	}
	
	@FindBy(id = "loginHtml:username")
	@CacheLookup 
	private WebElement userName;
	
	@FindBy(id = "loginHtml:password")
	@CacheLookup 
	private WebElement password;
	
	@FindBy(id = "loginHtml:domain")
	@CacheLookup 
	private WebElement domain;
	
	@FindBy(id = "loginHtml:userDomain")
	@CacheLookup 
	private WebElement userDomain;
	
	@FindBy(id = "loginHtml:loginBtn")
	@CacheLookup 
	private WebElement loginBtn;
	
	@FindBy(css = "ul.nav.navbar-nav.navbar-right.header-menu li:nth-child(3)")
	@CacheLookup
	private WebElement userIcon;
	
	@FindBy(css = "ul.nav.navbar-nav.navbar-right.header-menu li:nth-child(5)")
	@CacheLookup
	private WebElement logoutIcon;
	
	@FindBy(partialLinkText = "Logout")
	private WebElement logout;
	
	@FindBy(css = "div.errors ul li")
	@CacheLookup 
	private WebElement errorMsg;
	
	@FindBy(id = "si_searchAttrInput")
	@CacheLookup 
	private WebElement searchAttrInput;
	
	@FindBy(xpath = "//*[@id=\"userProfilePopup\"]/div/div/div[3]/button[@id=\"cancelBtn\"]")
	@CacheLookup 
	private WebElement closeBt;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mainHeader\"]/div/nav/div[2]/ul[2]/li[3]/a")
	private WebElement profile;

	
	public void login(String userName, String password, String custId, String domain)
			throws Exception {
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		this.domain.clear();
		this.domain.sendKeys(custId);
		Select select = null;
		for (int i = 0; i < 3; i++) {
			try {
				select = new Select(userDomain);
				select.selectByVisibleText(domain);
				break;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
			}
		}

		loginBtn.click();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}

	public boolean isLoggedIn() {
		boolean result = true;
		// check if input search box can be located
		try {
			new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(searchAttrInput));
		} catch (org.openqa.selenium.TimeoutException e) {
			result = false;
		}
		return result;
	}

	public String getErrorMsg() {
		return errorMsg.getText();
	}

	public Login logout() throws InterruptedException {
		closeBt.click();
		Thread.sleep(2000);
		logoutIcon.click();
		logout.click();
		return this;
	}
}
