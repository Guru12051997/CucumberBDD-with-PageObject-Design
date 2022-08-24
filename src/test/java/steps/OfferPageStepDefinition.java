package steps;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;
import pageObjects.OffersPage;
import pageObjects.PageObjectManager;
import utils.TestContextSetup;

public class OfferPageStepDefinition {
	public WebDriver driver;
	public String LandingPageProductname;
	public String OfferPageProductname;
	TestContextSetup testContextSetup;
	PageObjectManager pageObjectManager;

	public OfferPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
	}

	@Then("^user searched for(.+) shortname in offers page$")
	public void user_searched_for_shortname_in_offers_page(String Shortname) throws InterruptedException {
		
		switchToOffersPage();
		
		OffersPage offesPage = new OffersPage(testContextSetup.pageObjectManager.driver);
		offesPage.searchItem(Shortname);
		Thread.sleep(2000);
		offesPage.getProductName();
		System.out.println(OfferPageProductname + " is extracted from homepage");

	 }

	public void switchToOffersPage() {
		//if(testContextSetup.driver.getCurrentUrl().equalsIgnoreCase("https://rahulshettyacademy.com/seleniumPractise/#/offers"))
	//	pageObjectManager =new PageObjectManager(testContextSetup.driver);
		
		LandingPage  landingPage=testContextSetup.pageObjectManager.getLandingPage();
		//LandingPage  landingPage =new LandingPage(testContextSetup.driver);
		
		landingPage.selectTopDealsPage();
		//switch to child windows
		testContextSetup.genericUtils.SwitchwindowToChild();
	}

	@Then("validate product name in offers page matches with Landing Page")
	public void validate_product_name_in_offers_page_matches_with_landing_page() {

	Assert.assertEquals(OfferPageProductname, testContextSetup.LandingPageProductname);

	}
}