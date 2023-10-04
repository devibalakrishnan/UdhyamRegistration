package com.ippopay.msmeregistration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class RegDeclarations_14 extends AadhaarVerification_1 {

    @Test
    public String declarations(String dicNo) {
        try {
            WebElement gem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_rblGeM_0']")));
            gem.click();

            WebElement treds = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_rblTReDS_0']")));
            treds.click();

            WebElement ncs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_rblNCS_0']")));
            ncs.click();

            WebElement nsic = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_rblnsic_0']")));
            nsic.click();

            WebElement dic = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlDIC']")));
            dic.click();

            Select dicDropdown = new Select(dic);
            dicDropdown.selectByVisibleText(dicNo);

            WebElement submitOtp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$btnsubmit']")));
            submitOtp.click();

            return "Declaration updated";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Error in updating declarations: " + e.getMessage();
        }
    }
}
