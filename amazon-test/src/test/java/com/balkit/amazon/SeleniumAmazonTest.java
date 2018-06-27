package com.balkit.amazon;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.TestCase;

@RunWith(Parameterized.class)
public class SeleniumAmazonTest extends TestCase {
	
	
	public static void main(String[] args) throws Exception {                    
	       JUnitCore.main(
	         "com.balkit.amazon");            
	}
	
	private WebDriver 	driver;
	private String 		url;
	private String 		title;
	
	
	public SeleniumAmazonTest(String testUrl, String expectedTitle) {
		this.url 	= testUrl;
		this.title 	= expectedTitle;
	}
	
	@Parameters
	public static Collection<String[]> testScenarios() {
		return Arrays.asList(new String[][] { 
						{ "http://www.amazon.com", "800mm" },
						{ "http://www.amazon.com", "Nikon D3X" },
						{ "http://www.amazon.de", "Nikon D3X" },
		});
	}


	
	@Test
    public void testSeleniumAmazon()
    {
		//NOTE: Change this value accordingly. The Firefox driver is app. 13MBs 
		// I have attached a version in the 'Extras' folder
    	System.setProperty("webdriver.gecko.driver", "C:\\KBER\\07_Private\\04_AmazonTask\\Extras\\geckodriver.exe");
    	
    	// Create a new instance of the Firefox driver
        driver 	= new FirefoxDriver();
        WebDriverWait 	wait 	= new WebDriverWait(driver, 10);
        
        
        // a) Go to Url
        driver.get(url);

        // b) - Search Nikon 
        WebElement elSearchbox = driver.findElement(By.id("twotabsearchtextbox"));
        elSearchbox.click();
        elSearchbox.sendKeys("Nikon");
        elSearchbox.submit();
        

        // c) - and sort results from highest price to slowest.
        // <option value="price-desc-rank" selected="selected">Price: High to Low</option>
		wait.until(ExpectedConditions.elementToBeClickable(By.id("sort")));
        Select elDropdown = new Select(driver.findElement(By.id("sort")));
        elDropdown.selectByValue("price-desc-rank");

        // d) - Select second product
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='result_1']/div/div/div/div[2]/div/div/a/h2")));
        WebElement elSecondProduct = driver.findElement(By.xpath("//li[@id='result_1']/div/div/div/div[2]/div/div/a/h2"));
        
        // e) - and click it for details.
        elSecondProduct.click();
        
        // f) - From details check (verify with assert) that product topic contains text “Nikon D3X”
        //    - Annoyingly it does not from any of my PCs
        WebElement elProductTitle = driver.findElement(By.id("productTitle"));
        String productTitleString = elProductTitle.getText();
        assertTrue(productTitleString.contains(title));
        
    }
	
	
	@After
    public void tearDown()
    {
		//Close the browser
        driver.quit();
    }

}
