package steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import utils.TestContextSetup;

public class LandingPageStepDefinition {
	public WebDriver driver;
	public String LandingPageProductname;
	public String OfferPageProductname;
	TestContextSetup testContextSetup;
	LandingPage landingPage;

	public LandingPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
		this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
	}

	@Given("User is on GreenCart Landing page")
	public void user_is_on_green_cart_landing_page() {
		Assert.assertTrue(landingPage.getTitleLandingpage().contains("GreenKart"));
	}

	@When("^user searched with Shortname (.+) and extracted actual name of product$")
	public void user_searched_with_shortname_and_extracted_actual_name_of_product(String Shortname) {
		// LandingPage landingPage =new LandingPage(testContextSetup.driver);

		// LandingPage landingPage =
		// testContextSetup.pageObjectManager.getLandingPage();
		landingPage.searchItem(Shortname);
		landingPage.getProductName().split("-")[0].trim();
		testContextSetup.LandingPageProductname = landingPage.getProductName().split("-")[0].trim();
		System.out.println(LandingPageProductname +" is extracted from Home page");

		// testContextSetup.driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Shortname);
		// LandingPageProductname =
		// testContextSetup.driver.findElement(By.cssSelector("h4.product-name")).getText().split("-")[0].trim();
		// System.out.println(LandingPageProductname + " is extracted from homepage");

	}
	@When("Added {string} items of the selected product to cart")
	public void Added_items_product(String quantity) throws InterruptedException
	{
		Thread.sleep(4000);
		landingPage.incrementQuantity(Integer.parseInt(quantity));
		landingPage.addToCart();
		
	}

}