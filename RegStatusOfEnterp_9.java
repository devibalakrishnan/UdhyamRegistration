package com.ippopay.msmeregistration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class RegStatusOfEnterp_9 extends AadhaarVerification_1{


	@Test
	public String updateRegistrationStatus(String dateOfIncorporation, String busCommenced, String busComDate) throws Exception {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(dateOfIncorporation, formatter);

		try {
			
			WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtdateIncorporation'][@id='ctl00_ContentPlaceHolder1_txtdateIncorporation']")));
			dateField.sendKeys(date.format(formatter));
			log("Date passed: " + date.format(formatter));
		}


		catch(Exception e) {
			throw new Exception("Failed to update date", e);
		}

		WebElement yesRadioButton = driver.findElement(By.id("ctl00_ContentPlaceHolder1_rblcommenced_0"));
		WebElement noRadioButton = driver.findElement(By.id("ctl00_ContentPlaceHolder1_rblcommenced_1"));

		if (busCommenced.equalsIgnoreCase("yes")) {
			yesRadioButton.click();
			Thread.sleep(2000);
			WebElement dateBus= driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtcommencedate'][@id='ctl00_ContentPlaceHolder1_txtcommencedate']"));
			dateBus.sendKeys(busComDate != null ? busComDate : "");
		} else {
			noRadioButton.click();
		}

		return "Registration Status updated";



	}



}
