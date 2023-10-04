package com.ippopay.msmeregistration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class AadhaarOtpValidation_2 extends AadhaarVerification_1{


	@Test
	public String otpValidation(String otp) {

		if (!otp.matches("\\d+")) {
			throw new IllegalArgumentException("OTP can only contain numeric characters.");
		}
		else {

			// Enter OTP
			WebElement otpField = this.driver.findElement(By.xpath("//input[@placeholder='OTP code'][@type='text']"));
			otpField.sendKeys(otp);
			log("Otp entered");


			// Click on Validate button
			WebElement validateButton = this.driver.findElement(By.xpath("//input[@type='submit'][@value='Validate']"));
			validateButton.click();
			log("Validate button is clicked");

			// Waiting for loading
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				log("Error: Interrupted while waiting for page to load.");
				return "Page load interrupted";

			}

			// Verify Aadhaar validation success message
			WebElement aadhaarSuccessMsg = this.driver.findElement(By.xpath("//span[contains(text(),'Your Aadhaar has been successfully verified. You can continue Udyam Registration process.')]"));
			if (aadhaarSuccessMsg.isDisplayed()) {
				return "Aadhaar validation is successful";
			} else {
				return "Aadhaar validation failed";
			}


		}



	}}
