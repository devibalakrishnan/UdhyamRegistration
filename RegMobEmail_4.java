package com.ippopay.msmeregistration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegMobEmail_4 extends AadhaarVerification_1{


	@Test
	public String mobAndEmail(float mobileNo, String mail) {

		WebElement mobNo= driver.findElement(By.xpath("//input[@placeholder='Example:- 9999999999'][@type='text']"));
		wait.until(ExpectedConditions.visibilityOf(mobNo)).sendKeys(String.valueOf(mobileNo));

		WebElement mailId= driver.findElement(By.xpath("//input[@placeholder='Example:- info@gmail.com'][@type='text']"));
		wait.until(ExpectedConditions.visibilityOf(mailId)).sendKeys(mail);

		if (wait.until(ExpectedConditions.attributeContains(mobNo, "value", String.valueOf(mobileNo)))
				&& wait.until(ExpectedConditions.attributeContains(mailId, "value", mail))) {
			
			log("Mobile number and email added successfully.");
			return "Mobile number and email added successfully.";
		}
		else {
			log("Failed to add mobile number and email.");
			Assert.fail("Failed to add mobile number and email.");
			return "Failed to add mobile number and email.";
		}


	}


}
