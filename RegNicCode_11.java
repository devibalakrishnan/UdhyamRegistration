package com.ippopay.msmeregistration;


import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class RegNicCode_11 extends AadhaarVerification_1{

	@Test
	public String nicCode(String category, String twoDig, String fourDig, String fiveDig) {
		
		Select select;
		
		Map<String, WebElement> categoryRadios = new HashMap<String, WebElement>();
		categoryRadios.put("Manufacturing", driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rdbCatggMultiple']//label[contains(text(),'Manufacturing')]")));
		categoryRadios.put("Services", driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rdbCatggMultiple']//label[contains(text(),'Services')]")));
		categoryRadios.put("Trading", driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rdbCatggMultiple']//label[contains(text(),'Trading')]")));

		categoryRadios.get(category).click();
		
	
		try {
			Thread.sleep(3000);

		} catch (Exception e) {
			return "Failed to load";
			// TODO: handle exception
		}

		WebElement twoDigit = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddl2NicCode']")));
		select = new Select(twoDigit);
		select.selectByVisibleText(twoDig);


		WebElement fourDigit = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddl4NicCode']")));
		select = new Select(fourDigit);
		select.selectByVisibleText(fourDig);

		WebElement fiveDigit = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddl5NicCode']")));
		select = new Select(fiveDigit);
		select.selectByVisibleText(fiveDig);

		
		WebElement addActivity = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@name='ctl00$ContentPlaceHolder1$btnAddMore'][@value='Add Activity']")));
		addActivity.click();
		
		try {
			Thread.sleep(5000);

		} catch (Exception e) {
			return "Failed to load";
			// TODO: handle exception
		}

		
		
		return "Updated NIC codes";






	}	}






















