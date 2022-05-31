package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import resources.Base;

public class QuotePage extends Base{

    @FindBy(xpath = "//select[@data-test='language.selector']")
    private  WebElement languageSelector;

    @FindBy(css = "input[data-test='bike.quote.originalValue']")
    private WebElement bikePriceInputBox;

    @FindBy(xpath = "//div[@data-test='error.minValue']/span")
    private WebElement minValueError;

    @FindBy(xpath ="//input[@data-test='bike.quote.originalValue']/../../../div[@data-test='error.required']/span")
    private WebElement bikePriceRequiredError;

    @FindBy(xpath = "//div[@data-test='error.maxValue']/span")
    private WebElement maxValueError;

    @FindBy(xpath ="//select[@data-test='bike.quote.type']/../../div[@data-test='error.required']/span")
    private WebElement bikeTypeRequiredError;

    @FindBy(xpath = "//select[@data-test='bike.quote.type']")
    private WebElement bikeTypeDropdown;

    @FindBy(xpath = "//select[@data-test='bike.quote.antiTheftMeasure']")
    private WebElement getGPSTrackerDropdown;

    @FindBy(xpath = "//button[@data-test='bike.quote.priceInfoButton']")
    private WebElement seePriceButton;

    @FindBy(xpath = "//*[@data-test='bike.quote.card.VARIANT_THEFT_ASSISTANCE']/../preceding-sibling::div/div[@class='sc-dx5tdu-0 hJZLib']")
    private WebElement theiftAssistanceQuotePrice;

    @FindBy(xpath = "//*[@data-test='bike.quote.card.VARIANT_THEFT_DAMAGE_ASSISTANCE']/../preceding-sibling::div/div[@class='sc-dx5tdu-0 hJZLib']")
    private WebElement omniumQuotePrice;

    @FindBy(xpath = "//button[@data-test='bike.quote.card.VARIANT_THEFT_DAMAGE_ASSISTANCE']")
    private WebElement chooseOmiumPlan;


    public QuotePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyPolicyPageLoaded()
    {
        driver.findElement(By.xpath("//div[@data-tid='general.policyholder']/span")).getText().equalsIgnoreCase("Policyholder");
    }
    public void clickChoosePlan(String plan)
    {
        switch (plan) {
            case "Theift":

                break;
            case"Omium":
                chooseOmiumPlan.click();
                break;
        }

    }
    public void languageSelectorDropDown(String lang)
    {
        Select dropdown = new Select(languageSelector);
        dropdown.selectByVisibleText(lang);
    }
    public WebElement getBikePriceInputBox(){
        return bikePriceInputBox;
    }

    public void verifyErrorMessage(String Error){
        switch (Error){
            case "MinValue":
                Assert.assertTrue(minValueError.getText().trim().contains("Min 250"),"Minimum price error message is not displayed as expected");
                break;
            case "MaxValue":
                Assert.assertTrue(maxValueError.getText().contains("Max. €10.000"),"Maximum price error message is not displayed as expected");
                break;
            case "BikeType":
                Assert.assertTrue(bikeTypeRequiredError.getText().trim().contains("Required"),"Bike type rquired error message is not displayed as expected");
                break;
            case "BikePrice":
                Assert.assertTrue(bikePriceRequiredError.getText().trim().contains("Required"),"Bike price rquired error message is not displayed as expected");
                break;
        }
    }

    public void selectBikeType(String bikeType){
        Select dropdown = new Select(bikeTypeDropdown);
        dropdown.selectByVisibleText(bikeType);
    }
    public void selectGPSTracker(String gpsTracker){
        Select gpsDropdown = new Select(getGPSTrackerDropdown);
        gpsDropdown.selectByVisibleText(gpsTracker);
    }

    public void clickSeePrice(){
        seePriceButton.click();
    }

    public void verifyTheiftAssistanceQuotePrice(String expPrice){
        String actualPrice=theiftAssistanceQuotePrice.getText();
        Assert.assertEquals(actualPrice,expPrice, "Theift Assistance Price displayed incorrectly");

    }

    public void verifyOmniumQuotePrice(String expPrice){
        String actualPrice=omniumQuotePrice.getText();
        Assert.assertEquals(actualPrice,expPrice, "Omnium Price displayed incorrectly");

    }
}
