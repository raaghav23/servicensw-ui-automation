package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import org.testng.Assert;
import utils.WebDriverWrapper;

/*
@author : Raaghav
 */
public class ServiceNSWPage extends LoadableComponent<ServiceNSWPage> {

    @FindBy(css = "#homeAutosuggest > input")
    WebElement searchInputBox;

    @FindBy(css = "#block-content > div > header > div.container > div > form > div.form__actions > button")
    WebElement searchButton;

	@FindBy(css = "#locator > div > div > form > div > div.form__actions > button")
	WebElement submitButton;


	@FindBy(css = "#block-global-header-menu > div > ul > li:nth-child(3) > a")
	WebElement locateUsButton;

	@FindBy(css = "#search > div > div > div:nth-child(2) > h3 > a")
	WebElement searchResult;

	@FindBy(css = "#locatorTextSearch")
	WebElement suburbID;


    private WebDriverWrapper wrapper;
    WebDriver driver = null;
    private int timeOutInSeconds = 20;
	String searchResults;


	public ServiceNSWPage(WebDriver driver) {
    	this.driver=driver;
        PageFactory.initElements(driver, this);
        wrapper = new WebDriverWrapper(driver);
    }

    @Override
    protected void load() {
        wrapper.waitForElementToVisible(searchInputBox, timeOutInSeconds);
        searchInputBox.isDisplayed();
    }

    @Override
    protected void isLoaded() throws Error {
        wrapper.waitForElementToVisible(searchInputBox, timeOutInSeconds);
        searchInputBox.isDisplayed();
    }

    public String searchSuburb(String suburbName) {
		if (suburbID.isDisplayed()) {
			System.out.println("The searchbox is found");
			suburbID.click();
			wrapper.waitForElementToVisible(suburbID, timeOutInSeconds);
			suburbID.sendKeys(suburbName);
			wrapper.waitForElementToVisible(suburbID, timeOutInSeconds);
			searchSubmitButton();

		} else {
			System.out.println("The search Box is not found");
		}
        return suburbName;
    }

	public void numberPlateSearch(String numberPlate) {
		wrapper.waitForElementToVisible(searchInputBox, timeOutInSeconds);
		if (searchInputBox.isDisplayed()) {
			searchInputBox.click();
			searchInputBox.sendKeys(numberPlate);

		} else {
			System.out.println("The search Input box not found ");
		}
	}

	public void searchSubmitButton() {
		if (submitButton.isDisplayed()) {
			submitButton.click();
			wrapper.waitForElementToVisible(submitButton, timeOutInSeconds);

		} else {
			System.out.println(" Search Submit Button not found" );
		}
	}

	public void searchButton() {
		if (searchButton.isDisplayed()) {
			searchButton.click();

		} else {
			System.out.println(" Search Button not found" );
		}
	}
	public void selectFromList() {
		wrapper.waitForElementToVisible(searchResult, timeOutInSeconds);
		searchResults = searchResult.getText();
		System.out.println(" Search Result  : :" + searchResults);
		searchResult.click();

	}

	public void findLocations() {
		if (locateUsButton.isDisplayed()) {
			locateUsButton.click();
		} else {
			System.out.println(" LocateUS button not found" );
		}
	}

	/*
	Data Driven to search differrent centres
	 */
	public void verifyServiceCentreInSearchItems(String centreName) {
		boolean searchItem = driver.findElement(By.xpath("//a[contains(text(),'" + centreName + "')]"))
				.isDisplayed();

		Assert.assertTrue(searchItem);
	}

}
