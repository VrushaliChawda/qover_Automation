package step_Definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import pageObjects.QuotePage;
import resources.Base;
import java.io.IOException;
import java.util.List;

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
    public void userAddsBikeDetails(DataTable bikeDetails) {
        List<List<String>> bikeDetailsTable = bikeDetails.asLists();
        bikeInsuranceQuotePage.selectBikeType(bikeDetailsTable.get(1).get(0));
        bikeInsuranceQuotePage.getBikePriceInputBox().sendKeys(bikeDetailsTable.get(1).get(1));
        bikeInsuranceQuotePage.selectGPSTracker(bikeDetailsTable.get(1).get(2));
    }

    @Then("Quote is generated with correct price")
    public void quoteGenerated(DataTable price) {
        List<List<String>> priceObj = price.asLists();
        bikeInsuranceQuotePage.verifyTheiftAssistanceQuotePriceYearly(priceObj.get(1).get(0));
        bikeInsuranceQuotePage.verifyTheiftAssistanceQuotePriceMonthly(priceObj.get(2).get(0));
        bikeInsuranceQuotePage.verifyOmniumQuotePriceYearly(priceObj.get(1).get(1));
        bikeInsuranceQuotePage.verifyOmniumQuotePriceMonthly(priceObj.get(2).get(1));
    }
    @When("User clicked on see price")
    public void userClickedOnSeePrice() {
        bikeInsuranceQuotePage.clickSeePrice();
    }

    @After
    public void teardown() {
        driver.close();
        driver.quit();
    }

}
