package com.src.google.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.src.google.base.TestBase;

public class GoogleLandingPage extends TestBase{
	
	
	
	@FindBy(xpath="//img[@title='Google']")
	WebElement googleImage;
	
	@FindBy(xpath="//a[contains(text(),'Gmail')]")
	WebElement gmailLink;
	
	@FindBy(xpath="//div[@id='fkbx-spch']")
	WebElement micSymbol;
	
	@FindBy(xpath="//a[contains(@title,'Google Account: praveen')]")
	WebElement gmailTitleLink;
	
	@FindBy(xpath="//input[@id='lst-ib']")
	public WebElement googleSearchText;
	
	@FindBy(xpath="//input[@value='Google Search']")
	public
	WebElement googlesearchBtn;
	
	
	public GoogleLandingPage() {
		PageFactory.initElements(driver, this);	
	}

	
	
	

}
