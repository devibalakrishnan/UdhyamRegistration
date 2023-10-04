package com.ippopay.msmeregistration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class RegNoOfEmployees_12 extends AadhaarVerification_1{

	@Test
	public String noOfEmployees(String maleCount, String femaleCount, String othersCount) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder1_txtNoofpersonMale"))).sendKeys(maleCount != null ? maleCount : "0");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder1_txtNoofpersonFemale"))).sendKeys(femaleCount != null ? femaleCount : "0");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder1_txtNoofpersonOthers"))).sendKeys(othersCount != null ? othersCount : "0");

			return "Employees count added";
		} catch (Exception e) {
			return "Error in updating employees count: " + e.getMessage();
		}
	}
}
