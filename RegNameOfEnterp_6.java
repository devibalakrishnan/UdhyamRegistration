package com.ippopay.msmeregistration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class RegNameOfEnterp_6 extends AadhaarVerification_1 {

    @Test
    public String nameOfEnterp(String entName, String unitName) {
        try {
        	WebElement enterName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name of Enterprise']")));
            enterName.sendKeys(entName);

            WebElement mailId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Unit Name']")));
            mailId.sendKeys(unitName);


            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                log("Error: Interrupted while waiting for page to load.");
                return "Failed to add unit"; // Return statement with a message
            }

            WebElement unitNameTable = driver.findElement(By.xpath("//th[contains(text(),'Unit Name')]"));
            if (wait.until(ExpectedConditions.visibilityOf(unitNameTable)).isDisplayed()) {
                log("Unit added successfully.");
                return "Unit added successfully"; // Return statement with a message
            } else {
                log("Failed to add unit.");
                return "Failed to add unit"; // Return statement with a message
            }
        } catch (Exception e) {
            log("Failed to update the enterprise & unit name.");
            return "Failed to update the enterprise & unit name"; // Return statement with a message
        }
    }
}
