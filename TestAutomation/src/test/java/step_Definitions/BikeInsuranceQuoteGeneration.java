package step_Definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.QuotePage;
import resources.Base;

import java.io.IOException;

public class BikeInsuranceQuoteGeneration extends Base {
    private QuotePage bikeInsuranceQuotePage;

    @Before
    public void setDriver() throws IOException {
        driver = initialiseDriver();
    }

    @Given("User navigates to the {string} page")
    public void userNavigatesToThePage(String url) {
        driver.get(url);
        bikeInsuranceQuotePage = new QuotePage(driver);
    }

    @When("User enter bike price {string}")
    public void userEnterBikePrice(String bikePrice) {
        bikeInsuranceQuotePage.getBikePriceInputBox().sendKeys(bikePrice);
        bikeInsuranceQuotePage.getBikePriceInputBox().sendKeys(Keys.TAB);
    }

    @Then("Error message is displayed for {string}")
    public void errorMessageIsDisplayedForValue(String error) {
        bikeInsuranceQuotePage.verifyErrorMessage(error);
    }

    @Given("User adds bike details")
    public void userAddsBikeDetails() {
        bikeInsuranceQuotePage.selectBikeType("Racing bike");
        bikeInsuranceQuotePage.getBikePriceInputBox().sendKeys("2390");
        bikeInsuranceQuotePage.selectGPSTracker("Equipped with GPS tracker");
    }

    @Then("Quote is generated with correct price")
    public void quoteGenerated() {
        bikeInsuranceQuotePage.verifyTheiftAssistanceQuotePrice("€123.17/year");
        bikeInsuranceQuotePage.verifyOmniumQuotePrice("€151.44/year");
    }

    @After
    public void teardown() {
        driver.close();
        driver.quit();
    }


    @When("User clicked on see price")
    public void userClickedOnSeePrice() {
        bikeInsuranceQuotePage.clickSeePrice();
    }

    @Then("Error message displayed for {string}")
    public void errorMessageIsDisplayedIsError(String Error) {
        bikeInsuranceQuotePage.verifyErrorMessage(Error);
    }

    @And("User can choose plan")
    public void userCanChoosePlan() {
        bikeInsuranceQuotePage.clickChoosePlan("Omium");
        bikeInsuranceQuotePage.verifyPolicyPageLoaded();
    }
}
