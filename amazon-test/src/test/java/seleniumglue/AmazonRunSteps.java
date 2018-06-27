package seleniumglue;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AmazonRunSteps {

	public static WebDriver 	driver;
	public static WebDriverWait wait;
	private WebElement 			elSecondProduct;
	
	@Before
	public void beforeScenario() 
	{
		//NOTE: Change this value accordingly. The Firefox driver is app. 13MBs 
		// I have attached a version in the 'Extras' folder
    	System.setProperty("webdriver.gecko.driver", "C:\\KBER\\07_Private\\04_AmazonTask\\Extras\\geckodriver.exe");
        driver = new FirefoxDriver();
    	wait 	= new WebDriverWait(driver, 10);
	}
	
    
    // a)
	@Given("^user goes to \"([^\"]*)\"$")
    public void user_is_on_homepage(String url) throws Throwable {     
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://" + url);
    }
    
	
    // b) - Search Nikon 
    @When("^user searches for Nikon product$")
    public void user_searches_for_Nikon_product() throws Throwable {
        WebElement elSearchbox = driver.findElement(By.id("twotabsearchtextbox"));
        elSearchbox.click();
        elSearchbox.sendKeys("Nikon");
        elSearchbox.submit();
    }
    
    
    // c) - and sort results from highest price to slowest.
    @When("^user sorts results from highest price to lowest$")
    public void user_sorts_results_from_highest_price_to_lowest() throws Throwable {
        // <option value="price-desc-rank" selected="selected">Price: High to Low</option>
		wait.until(ExpectedConditions.elementToBeClickable(By.id("sort")));
        Select elDropdown = new Select(driver.findElement(By.id("sort")));
        elDropdown.selectByValue("price-desc-rank");
    }
    
    
    // d) - user selects second product from top.
    @When("^user selects second product from top$")
    public void user_selects_second_product_from_top() throws Throwable {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='result_1']/div/div/div/div[2]/div/div/a/h2")));
        elSecondProduct = driver.findElement(By.xpath("//li[@id='result_1']/div/div/div/div[2]/div/div/a/h2"));
    }
    
    
    // e) - user clicks it for details.
    @When("^user clicks it for details$")
    public void user_clicks_it_for_details() throws Throwable {
    	elSecondProduct.click();
    }
    
    
    // f) - product containing Nikon D3X is found
    @Then("^product containing Nikon D3X is found$")
    public void product_containing_Nikon_D3X_is_found() throws Throwable {
        // Annoyingly it does not from any of my PCs
    	// However I CAN find "800mm"
//    	String exp_message = "800mm";
        String exp_message = "Nikon D3X";
        WebElement elProductTitle = driver.findElement(By.id("productTitle"));
        String productTitleString = elProductTitle.getText();
        Assert.assertTrue(productTitleString.contains(exp_message));
    }      
 
	@After
    public void afterScenario()
    {
		//Close the browser
        driver.quit();
    }
}