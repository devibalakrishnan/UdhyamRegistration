package com.ippopay.msmeregistration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;


public class RegBankDetails_10 extends AadhaarVerification_1{
	@Test
	public String bankDetails(String name, String ifsc, String accNo, String category) {
		try {
			WebElement bankName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtBankName'][@placeholder='Enter Bank Name']")));
            bankName.sendKeys(name);


			log("Bank name passed");
		} catch (Exception e) {
			return "Failed to enter bank name";
			// handle the exception
		}


		try {
			WebElement ifscNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtifsccode'][@placeholder='Example:- SBIN0001624']")));
            ifscNumber.sendKeys(ifsc);

			log("Ifsc passed");
		} catch (Exception e) {
			return "Failed to enter ifsc";
			// handle the exception
		}


		try {
			WebElement accountNo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtaccountno'][@maxlength='18']")));
            accountNo.sendKeys(accNo);

			log("Account number passed");
		} catch (Exception e) {
			return "Failed to enter account number";
			// handle the exception
		}

		WebElement manufacturingRadio = driver.findElement(By.xpath("//label[contains(text(),'Manufacturing')]"));
		WebElement servicesRadio = driver.findElement(By.xpath("//label[contains(text(),'Services')]"));

		if (category.equalsIgnoreCase("Manufacturing")) {
			manufacturingRadio.click();
		} else if (category.equalsIgnoreCase("Services")) {
			servicesRadio.click();
		}
		
		return "Bank details and category updated";





	}	
}
