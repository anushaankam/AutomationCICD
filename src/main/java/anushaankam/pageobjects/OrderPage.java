package anushaankam.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import anushaankam.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	//constructor
	public OrderPage(WebDriver driver)
	{
		//initializing
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> OrderPageProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutbtn;
	
	By Checkout = By.cssSelector(".totalRow button");
	
	public Boolean VerifyOrderDisplay(String productname)
	{
		Boolean match = OrderPageProducts.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		return match;
	}
}
