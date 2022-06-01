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

    @FindBy(css = "select[data-test='language.selector']")
    private  WebElement languageSelector;

    @FindBy(css = "input[data-test='bike.quote.originalValue']")
    private WebElement bikePriceInputBox;

    @FindBy(css = "div[data-test='error.minValue'] > span")
    private WebElement minValueError;

    @FindBy(xpath ="//input[@data-test='bike.quote.originalValue']/../../../div[@data-test='error.required']/span")
    private WebElement bikePriceRequiredError;

    @FindBy(css = "div[data-test='error.maxValue'] > span")
    private WebElement maxValueError;

    @FindBy(xpath ="//select[@data-test='bike.quote.type']/../../div[@data-test='error.required']/span")
    private WebElement bikeTypeRequiredError;

    @FindBy(css = "select[data-test='bike.quote.type']")
    private WebElement bikeTypeDropdown;

    @FindBy(css = "select[data-test='bike.quote.antiTheftMeasure']")
    private WebElement getGPSTrackerDropdown;

    @FindBy(css = "button[data-test='bike.quote.priceInfoButton']")
    private WebElement seePriceButton;

    @FindBy(xpath = "//*[@data-test='bike.quote.card.VARIANT_THEFT_ASSISTANCE']/../preceding-sibling::div/div[@class='sc-dx5tdu-0 hJZLib']")
    private WebElement theiftAssistanceQuotePriceYearly;

    @FindBy(xpath = "//*[@data-test='bike.quote.card.VARIANT_THEFT_ASSISTANCE']/../preceding-sibling::div/div[@class='sc-dx5tdu-0 hJZLib xsmall']")
    private WebElement theiftAssistanceQuotePriceMonthly;

    @FindBy(xpath = "//*[@data-test='bike.quote.card.VARIANT_THEFT_DAMAGE_ASSISTANCE']/../preceding-sibling::div/div[@class='sc-dx5tdu-0 hJZLib']")
    private WebElement omniumQuotePriceYearly;

    @FindBy(xpath = "//*[@data-test='bike.quote.card.VARIANT_THEFT_DAMAGE_ASSISTANCE']/../preceding-sibling::div/div[@class='sc-dx5tdu-0 hJZLib xsmall']")
    private WebElement omniumQuotePriceMonthly;

    @FindBy(css = "button[data-test='bike.quote.card.VARIANT_THEFT_DAMAGE_ASSISTANCE']")
    private WebElement chooseOmiumPlan;


    public QuotePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyPolicyPageLoaded()
    {
        driver.findElement(By.xpath("//div[@data-tid='general.policyholder']/span")).getText().equalsIgnoreCase("Policyholder");
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

    public void verifyTheiftAssistanceQuotePriceYearly(String expPrice){
        String actualPrice=theiftAssistanceQuotePriceYearly.getText();
        Assert.assertEquals(actualPrice,expPrice, "Theift Assistance quote price yearly displayed incorrectly");
    }

    public void verifyTheiftAssistanceQuotePriceMonthly(String expPrice){
        String actualPrice=theiftAssistanceQuotePriceMonthly.getText();
        Assert.assertEquals(actualPrice,expPrice, "Theift Assistance quote price monthly displayed incorrectly");
    }

    public void verifyOmniumQuotePriceYearly(String expPrice){
        String actualPrice=omniumQuotePriceYearly.getText();
        Assert.assertEquals(actualPrice,expPrice, "Omnium Quote Price yearly displayed incorrectly");
    }

    public void verifyOmniumQuotePriceMonthly(String expPrice){
        String actualPrice=omniumQuotePriceMonthly.getText();
        Assert.assertEquals(actualPrice,expPrice, "Omnium Quote Price monthly displayed incorrectly");
    }

}
