package anushaankam.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import anushaankam.TestComponents.BaseTest;
import anushaankam.pageobjects.CartPage;
import anushaankam.pageobjects.CheckoutPage;
import anushaankam.pageobjects.ConfirmationPage;
import anushaankam.pageobjects.LandingPage;
import anushaankam.pageobjects.OrderPage;
import anushaankam.pageobjects.ProductCatalouge;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	String productname = "ZARA COAT 3";
	@Test(dataProvider="getData",groups="Purchase")
	public void TestApp(HashMap<String,String> input) throws InterruptedException    //thrws intrpexcptn due to thread added in checkoutpage
	{
		
		ProductCatalouge productcatalouge = landingpage.loginApplication(input.get("email"),input.get("pwd"));
		List<WebElement> products = productcatalouge.getProductsList();
		productcatalouge.addProductToCart(input.get("productname"));
		CartPage cartpage = productcatalouge.ClickonCart();
		Boolean match = cartpage.VerifyProductDisplay(input.get("productname"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.clickCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.placetheOrder();
		String message = confirmationpage.confirmationmessage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"TestApp"})
	public void OrderHistoryTest()
	{
		//first login again to check the order history 
		ProductCatalouge productcatalouge = landingpage.loginApplication("ankam7063@gmail.com","Seleniumudemy@7");
		//then click on orders on header = AbstractComponents
		OrderPage orderpage =  productcatalouge.goToOrdersPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productname));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//anushaankam//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	/*
	 * @DataProvider public Object[][] getData() { return new Object[][]
	 * {{"ankam7063@gmail.com","Seleniumudemy@7","ZARA COAT 3"},{
	 * "alekhyaankam3@gmail.com","Data@123","ADIDAS ORIGINAL"}}; }
	 */
	
	/*
	 * HashMap<String,String> map = new HashMap<String,String>();
	 * map.put("email","ankam7063@gmail.com"); map.put("pwd", "Seleniumudemy@7");
	 * map.put("productname", "ZARA COAT 3");
	 * 
	 * HashMap<Object,Object> map1 = new HashMap<Object,Object>();
	 * map1.put("email","alekhyaankam3@gmail.com"); map1.put("pwd", "Data@123");
	 * map1.put("productname", "ADIDAS ORIGINAL");
	 */
}