package anushaankam.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import anushaankam.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	//constructor
	public CartPage(WebDriver driver)
	{
		//initializing
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutbtn;
	
	By Checkout = By.cssSelector(".totalRow button");
	
	public Boolean VerifyProductDisplay(String productname)
	{
		Boolean match = cartproducts.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		return match;
	}

	public CheckoutPage clickCheckout() throws InterruptedException
	{
		waitForElementToAppear(Checkout);
		JavascriptExecutor js = (JavascriptExecutor)driver;      //Added on 3Sep
		js.executeScript("window.scrollBy(0,900)");              //Added
		Thread.sleep(2000); 
		checkoutbtn.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}
}
