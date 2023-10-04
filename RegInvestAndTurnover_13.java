package com.ippopay.msmeregistration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class RegInvestAndTurnover_13 extends AadhaarVerification_1{

	@Test
	public String updateInvestmentAndTurnover(int wdv, int pollution, int turnover) {
		
		try {
			WebElement wdvField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder1_txtDepCost")));
			wdvField.sendKeys(String.valueOf(wdv));
			
			WebElement pollutionExclusionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtExCost'][@id='ctl00_ContentPlaceHolder1_txtExCost']")));
			pollutionExclusionField.sendKeys(String.valueOf(pollution));
		
			WebElement turnoverField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtTotalTurnoverA'][@id='ctl00_ContentPlaceHolder1_txtTotalTurnoverA']")));
			turnoverField.sendKeys(String.valueOf(turnover));
		
			return "Investment and Turnover updated";
		}
		catch (Exception e) {
			
			return "Error in updating Investment and Turnover";
		}
	}
}
