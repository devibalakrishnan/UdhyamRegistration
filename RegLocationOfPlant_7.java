package com.ippopay.msmeregistration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class RegLocationOfPlant_7 extends AadhaarVerification_1 {



	@Test
	public String locationOfPlant(int doorNo, String name, String town, String block, String road, String city, int pin, String state, String district ) {

		try {


			WebElement unitNameDropdown = driver.findElement(By.id("ctl00_ContentPlaceHolder1_ddlUnitName"));
			Select selectUnitName = new Select(unitNameDropdown);
			selectUnitName.selectByValue("1");
			log("Plant selected");


			WebElement doorNumber = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPFlat'][@placeholder='Flat/Door/Block No.']"));
			wait.until(ExpectedConditions.visibilityOf(doorNumber)).sendKeys(String.valueOf(doorNo));
			log("Door no passed");




			WebElement nameOfBuilding = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPBuilding'][@placeholder='Name of Premises/ Building']"));
			wait.until(ExpectedConditions.visibilityOf(nameOfBuilding)).sendKeys(String.valueOf(name));
			log("Building name passed");



			WebElement townField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPVillageTown'][@placeholder='Village/Town']"));
			wait.until(ExpectedConditions.visibilityOf(townField)).sendKeys(String.valueOf(town));
			log("Town name passed");



			WebElement blockField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPBlock'][@placeholder='Block']"));
			wait.until(ExpectedConditions.visibilityOf(blockField)).sendKeys(String.valueOf(block));
			log("Block name passed");



			WebElement roadField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPRoadStreetLane'][@placeholder='Road/ Street/ Lane']"));
			wait.until(ExpectedConditions.visibilityOf(roadField)).sendKeys(String.valueOf(road));
			log("Road name passed");



			WebElement cityField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPCity'][@placeholder='City']"));
			wait.until(ExpectedConditions.visibilityOf(cityField)).sendKeys(String.valueOf(city));
			log("City passed");


			WebElement pincode = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPpin'][@placeholder='Pin']"));
			wait.until(ExpectedConditions.visibilityOf(pincode)).sendKeys(String.valueOf(pin));
			log("Pin passed");



			WebElement stateField = driver.findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddlPState'][@title='State_Namee']"));
			wait.until(ExpectedConditions.visibilityOf(stateField)).click();


			WebElement stateDropdown = driver.findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddlPState'][@title='State_Namee']"));
			Select selectState = new Select(stateDropdown);
			selectState.selectByVisibleText(state);


			Thread.sleep(5000);


			WebElement districtDropdown = driver.findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddlPDistrict'][@id='ctl00_ContentPlaceHolder1_ddlPDistrict']"));
			Select selectDistrict = new Select(districtDropdown);
			selectDistrict.selectByVisibleText(district);

			try {
				Thread.sleep(6000);//waiting for loading to add plant
			} catch (Exception e) {
				//return "Failed to load to get updated";
				// TODO: handle exception
			}


			WebElement addPlantBtn = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$BtnPAdd'][@value='Add Plant']"));
			wait.until(ExpectedConditions.visibilityOf(addPlantBtn)).click();



			try {
				Thread.sleep(6000);//waiting for loading to add plant
			} catch (Exception e) {
				return "Failed to load to get updated";

			}

			return "Location of the plant updated successfully";

		}	
		catch (Exception e) {
			e.printStackTrace();
			return "Failed to update plant location"; // failure
		}

		finally {
		}

	}
}