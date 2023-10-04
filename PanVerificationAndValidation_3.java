package com.ippopay.msmeregistration;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class PanVerificationAndValidation_3 extends AadhaarVerification_1 {

	@Test
	public String pan(String typeOfOrg, String panNo, String gstinAvlOrnot) {

	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


		WebElement typeOfOrgDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='col-md-4'][1]//select[@name='ctl00$ContentPlaceHolder1$ddlTypeofOrg'][@class='form-control']")));
		Select select = new Select(typeOfOrgDropdown);
		select.selectByVisibleText(typeOfOrg);

		WebElement panNoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter Pan Number'][@type='text']")));
		panNoInput.sendKeys(panNo);
		log("PAN number passed");

		WebElement validateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@type='submit'][@value='PAN Validate']")));
		validateButton.click();
		log("PAN validate is clicked");

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			log("PAN validation failed due to page loading error.");
		}

		WebElement panSuccessMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[contains(text(),'Your PAN has been successfully verified.')]")));

		if (panSuccessMsg.isDisplayed()) {
			log("PAN validation is success");

			WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//input[@type='submit'][@value='Continue']")));
			continueButton.click();
			log("Continue button is clicked");

			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				log("GSTIN validation failed due to checkbox loading error.");
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//label[contains(text(),('4.3 Do you have GSTIN ?'))]")));

			WebElement radioGroup = driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rblWhetherGstn']"));
			List<WebElement> radioButtons =radioGroup.findElements(By.tagName("label"));
			for (WebElement radioButton : radioButtons) {
				if (radioButton.getText().equalsIgnoreCase(gstinAvlOrnot)) {
					radioButton.click();
					break;
				}
			}


			log("PAN and GSTIN validation is success");
		} else {
			log("Error in PAN & GSTIN validation.");
		}
		return "PAN and GSTIN validation is success";

	}

}
