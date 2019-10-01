package stepDefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ServiceNSWPage;
import utils.BrowserFactory;
import utils.ConfigurationProperties;
import utils.PropertyReader;

/*
@author : Raaghav
 */
public class ServiceNSWStepDefinition {

    Scenario scenario;
    WebDriver driver = null;
    String suburb = "";
    ServiceNSWPage serviceNSWPage;


    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("^User navigates to Service NSW homepage$")
    public void url_is_loaded() throws Throwable {
        driver = BrowserFactory.getDriver(PropertyReader.readConfig(ConfigurationProperties.BROWSER));
        String url = PropertyReader.readConfig(ConfigurationProperties.SERVICE_NSW_BASE_URL);
        driver.get(url);
        serviceNSWPage = new ServiceNSWPage(driver);
    }

    @When("^Search \"([^\"]*)\"$")
    public void search_apply_number_plate(String numberPlate) throws Throwable {
        serviceNSWPage.numberPlateSearch(numberPlate);
        serviceNSWPage.searchButton();

    }

    @Then("^Validate the navigation to Apply for a number plate$")
    public void validate_the_search_results_page() throws Throwable {
        serviceNSWPage.selectFromList();
        Assert.assertEquals(driver.getTitle(),
                "Apply for a number plate | Service NSW", "Verify Navigation to Apply for a number plate page");
    }

    @And("^Verify Find locations$")
    public void findLocations() {
        serviceNSWPage.findLocations();
    }

    @And("^Enter suburb \"([^\"]*)\"$")
    public void enter_suburb_name(String SuburbName) throws Throwable {
        suburb = serviceNSWPage.searchSuburb(SuburbName);
        scenario.write("Suburb details : : " + SuburbName);

    }

    @Then("^\"([^\"]*)\" is displayed in search results Items$")
    public void service_centre_in_search_items(String ServiceCentreName) throws Throwable {
        serviceNSWPage.verifyServiceCentreInSearchItems(ServiceCentreName);
        scenario.write("ServiceCentre Name  : : " + ServiceCentreName);
    }

    @Then("^\"([^\"]*)\" displayed in search results Items$")
    public void service_centre_search_items(String ServiceCentre) throws Throwable {
        serviceNSWPage.verifyServiceCentreInSearchItems(ServiceCentre);
        scenario.write("ServiceCentre Name  : : " + ServiceCentre);
    }


    @After
    public void after() {
    }
}
