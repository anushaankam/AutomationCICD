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

public class ProductCatalouge extends AbstractComponent {
	
	WebDriver driver;
	//constructor
	public ProductCatalouge(WebDriver driver)
	{
		//initializing
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart  = By.cssSelector(".card-body button:last-of-type");
	By toastmessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductsList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductName(String productname)
	{
		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		return prod;
	}
	public void addProductToCart(String productname) throws InterruptedException
	{
		WebElement prod = getProductName(productname);
		JavascriptExecutor js = (JavascriptExecutor)driver;      //Added on 29Aug
		js.executeScript("window.scrollBy(0,25)");              //Added
		Thread.sleep(2000); 
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastmessage);
		waitForElementToDisappear(spinner);
		
	}
	
}















