package anushaankam.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;


import anushaankam.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	//constructor
	public CheckoutPage(WebDriver driver)
	{
		//initializing
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countrybox;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement clickcountry;
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	By countries = By.cssSelector(".ta-results");
	
	public void selectCountry(String country)
	{
		Actions a = new Actions(driver);
		a.sendKeys(countrybox,country).build().perform();
		waitForElementToAppear(countries);
		clickcountry.click();
	}
	
	public ConfirmationPage placetheOrder() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;      //Added on 29Aug
		js.executeScript("window.scrollBy(0,700)");              //Added
		Thread.sleep(2000);                                      //Add hence throws InterptdExcptin
		placeOrder.click();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
	}
}
