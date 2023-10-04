package com.ippopay.msmeregistration;

import java.time.Duration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AadhaarVerification_1 {

	// Declare the WebDriver object	
	WebDriver driver ;
	WebDriverWait wait;
	

	// Create a logger object using Log4j
	private static final Logger log =LogManager.getLogger("UdyamRegistration");

	// Define a log method to print messages to the console and log file
	public void log(String string) {		
		System.out.println(string);	
		log.info(string);
	}
	

	// Test method to perform Aadhaar verification
	@Test
	public String aadhaar(String number,String name) {

		try {
			// Initialize ChromeDriver and set the implicit wait
			

		    ChromeOptions options = new ChromeOptions();
		    options.addArguments("--remote-allow-origins=*");
		    options.addArguments("--headless"); // Add this line to enable headless mode
		    WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver(options);
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			// Navigate to the Udyam registration site
			driver.get("http://udyamregistration.gov.in/UdyamRegistration.aspx");
			driver.manage().window().maximize();

			// Set up explicit wait
			wait = new WebDriverWait(driver, Duration.ofMillis(30000));
			log("Navigated udyam site");
			
			// Verify that the homepage has been reached
			String title = driver.getTitle();
			Assert.assertTrue(title.contains("UDYAM REGISTRATION FORM - For New Enterprise who are not Registered yet as MSME")); 
			log("Reached homepage");

			// Locate and enter the Aadhaar number
			WebElement aadhaarNo = driver.findElement(By.xpath("//input[@type='text'][@placeholder='Your Aadhaar No']"));
			wait.until(ExpectedConditions.visibilityOf(aadhaarNo)).sendKeys(number);
			log("Aadhaar no passed");

			// Locate and enter the name as per Aadhaar
			WebElement nameAsAadhaar = driver.findElement(By.xpath("//input[@placeholder='Name as per Aadhaar']"));
			wait.until(ExpectedConditions.visibilityOf(nameAsAadhaar)).sendKeys(name);
			log("Name as per aadhaar is passed");

			// Locate and click the Validate and Generate OTP button
			WebElement ValidateOtp = driver.findElement(By.xpath("//input[@type='submit'][@value='Validate & Generate OTP']"));
			wait.until(ExpectedConditions.visibilityOf(ValidateOtp)).click();
			log("Validate and generate otp button is clicked");

			// Waiting for loading
			
			WebElement otpField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtOtp1'][@placeholder='OTP code']"));
	        if (otpField.isDisplayed()) {
	            Assert.assertTrue(true);
	        } else {
	            log("Aadhaar verification failed");
	            Assert.assertTrue(false);
	        }
	        
	    } 
		catch (Exception e) {
	        log("Error: Aadhaar verification failed.");
	        return "Aadhaar verification failed"; // Fail the test in case of any exception
	    }
		return "Aadhaar verification successful";
	


	}
}
