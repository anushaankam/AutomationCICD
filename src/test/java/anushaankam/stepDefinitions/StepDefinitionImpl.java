package anushaankam.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import anushaankam.TestComponents.BaseTest;
import anushaankam.pageobjects.CartPage;
import anushaankam.pageobjects.CheckoutPage;
import anushaankam.pageobjects.ConfirmationPage;
import anushaankam.pageobjects.LandingPage;
import anushaankam.pageobjects.ProductCatalouge;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingpage;
	ProductCatalouge productcatalouge;
	public ConfirmationPage confirmationpage;

	@Given("Landed on Ecommerce Page")
	public void Landed_on_Ecommerce_page() throws IOException {
		landingpage = launchApplication();
	}

	@Given("^Logged in with Username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productcatalouge = landingpage.loginApplication(username, password);
	}

	@When("^I add the product (.+) to Cart$")
	public void i_add_the_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productcatalouge.getProductsList();
		productcatalouge.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the Order$")
	public void checkout_submit_the_order(String productName) throws InterruptedException {
		CartPage cartpage = productcatalouge.ClickonCart();
		Boolean match = cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.clickCheckout();
		checkoutpage.selectCountry("india");
		confirmationpage = checkoutpage.placetheOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationpage(String string) {
		String message = confirmationpage.confirmationmessage();
		Assert.assertTrue(message.equalsIgnoreCase(string));
		driver.close();
	}
	
	
    @Then("{string} message is displayed")
    public void message_displayed(String string)
    {
		Assert.assertEquals(landingpage.getErrorMessage(),string);
		driver.close();
    }

}
