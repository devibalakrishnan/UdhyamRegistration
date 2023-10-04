package com.ippopay.msmeregistration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class RegCatGenderAbled_5 extends AadhaarVerification_1 {


	@Test
	public String mobAndEmail(String socCat, String gender, String abledOrNot) {



		try {
			WebElement socialCateg= driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rdbcategory']"));
			List<WebElement> categoryOptions = socialCateg.findElements(By.tagName("label"));
			for (WebElement option : categoryOptions) {
				log(option.getText());
				if (option.getText().toLowerCase().contains(socCat.toLowerCase())) {
					option.click();
					break;
				}
			}



			WebElement genderValue= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rbtGender']")));
			List<WebElement> genderOptions = genderValue.findElements(By.tagName("label"));
			for (WebElement option2 : genderOptions) {
				if (option2.getText().toLowerCase().contains(gender.toLowerCase())) {
					option2.click();
					break;
				}
			}


			WebElement abled = driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rbtPh']"));
			List<WebElement> radioOptions = abled.findElements(By.tagName("label"));
			for (WebElement option3 : radioOptions) {
				if (option3.getText().toLowerCase().contains(abledOrNot.toLowerCase())) {
					option3.click();
					break;
				}
			}
			
			return "Social category, gender, and ability status added successfully.";
		}
		catch (Exception e) {
			log("Error: Failed to add social category, gender, and ability status");
			return "Error: Failed to add social category, gender, and ability status.";
		}
	}}