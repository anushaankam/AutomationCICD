package anushaankam.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import anushaankam.pageobjects.CartPage;
import anushaankam.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) 
	{
	
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement Cart;

	@FindBy(css="[routerlink*='myorders']")
	WebElement OrderHeader;
	
	public void waitForElementToAppear(By FindBy)
	{
		
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitForWebElementToAppear(WebElement FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.invisibilityOf(ele));
		 
	}
	
	public CartPage ClickonCart()
	{
		Cart.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	 public OrderPage goToOrdersPage() 
	 { 
		 OrderHeader.click(); 
		 OrderPage orderpage = new OrderPage(driver); 
		 return orderpage;
	 }
	 
}
