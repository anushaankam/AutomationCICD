package anushaankam.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import anushaankam.TestComponents.BaseTest;
import anushaankam.TestComponents.Retry;
import anushaankam.pageobjects.CartPage;
import anushaankam.pageobjects.CheckoutPage;
import anushaankam.pageobjects.ConfirmationPage;
import anushaankam.pageobjects.ProductCatalouge;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups="ErrorHandling", retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException
	{
		String productname = "ZARA COAT 3";		
		landingpage.loginApplication("ankam7063@gmail.com","Seleniuudemy@7");
		Assert.assertEquals(landingpage.getErrorMessage(),"Incorrect email or password.");
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		String productname = "ZARA COAT 3";
		ProductCatalouge productcatalouge = landingpage.loginApplication("ankam7063@gmail.com","Seleniumudemy@7");
		List<WebElement> products = productcatalouge.getProductsList();
		productcatalouge.addProductToCart(productname);
		CartPage cartpage = productcatalouge.ClickonCart();
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);	 
	}
	
}