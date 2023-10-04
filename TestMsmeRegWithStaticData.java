package com.ippopay.msmeregistration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestMsmeRegWithStaticData {


	// Declare the ChromeDriver object	
	WebDriver driver ;
	WebDriverWait wait;


	// Create a logger object using Log4j
	private static final Logger log =LogManager.getLogger("Baseclass");

	// Define a log method to print messages to the console and log file
	public void log(String string) {		
		System.out.println(string);	
		log.info(string);
	}



	@Test(priority=1)
	public void mainFile() throws InterruptedException {

		try {
			// Initialize ChromeDriver and set the implicit wait
		
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			// Navigate to the Udyam registration site
			driver.get("http://udyamregistration.gov.in/UdyamRegistration.aspx");
			driver.manage().window().maximize();

			// Set up explicit wait
			wait = new WebDriverWait(driver, Duration.ofMillis(30000));
			log("Navigated udyam site");

			// Verify that the homepage has been reached
			String title = driver.getTitle();
			Assert.assertTrue(title.contains("UDYAM REGISTRATION FORM - For New Enterprise who are not Registered yet as MSME")); 
			log("Reached homepage");

			// Locate and enter the Aadhaar number
			WebElement aadhaarNo = driver.findElement(By.xpath("//input[@type='text'][@placeholder='Your Aadhaar No']"));
			wait.until(ExpectedConditions.visibilityOf(aadhaarNo)).sendKeys("702772053374");
			log("Aadhaar no passed");

			// Locate and enter the name as per Aadhaar
			WebElement nameAsAadhaar = driver.findElement(By.xpath("//input[@placeholder='Name as per Aadhaar']"));
			wait.until(ExpectedConditions.visibilityOf(nameAsAadhaar)).sendKeys("Devibala balakrishnan");
			log("Name as per aadhaar is passed");

			// Locate and click the Validate and Generate OTP button
			WebElement ValidateOtp = driver.findElement(By.xpath("//input[@type='submit'][@value='Validate & Generate OTP']"));
			wait.until(ExpectedConditions.visibilityOf(ValidateOtp)).click();
			log("Validate and generate otp button is clicked");

			// Waiting for loading
			try {
				Thread.sleep(5000);

			} 
			catch (InterruptedException e) {
				log("Error: Interrupted while waiting for page to load.");
				//return "Page load interrupted";

			}


			WebElement otpField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtOtp1'][@placeholder='OTP code']"));
			if (otpField.isDisplayed()) {
				log("Aadhaar verification successful");
				Assert.assertTrue(true);
			} else {
				log("Aadhaar verification failed");
				Assert.assertTrue(false);
			}

		} 
		catch (Exception e) {
			log("Error: Aadhaar verification failed.");
			//return "Aadhaar verification failed"; // Fail the test in case of any exception
		}
		//return "";



	}


	@Test(priority=2)
	public void otpValidation() {


		// Enter OTP
		driver.findElement(By.xpath("//input[@placeholder='OTP code'][@type='text']")).click();
		log("Otp field clicked");
		
		// Waiting for loading
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					log("Error: Interrupted while waiting for page to load.");
					//return "Page load interrupted";

				}

		// Click on Validate button
		driver.findElement(By.xpath("//input[@type='submit'][@value='Validate']")).click();
		log("Validate button is clicked");

		// Waiting for loading
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			log("Error: Interrupted while waiting for page to load.");
			//return "Page load interrupted";

		}
		Boolean invalidOtp = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(text(),'Incorrect OTP, Re-enter correct OTP.')]")));
		log("Is it valid otp?"+invalidOtp);
		if(invalidOtp==true) {
		
		// Verify Aadhaar validation success message
		WebElement aadhaarSuccessMsg = driver.findElement(By.xpath("//span[contains(text(),'Your Aadhaar has been successfully verified. You can continue Udyam Registration process.')]"));
		if(aadhaarSuccessMsg.isDisplayed()) {
			log("Aadhaar validation is successful");
			//return "Aadhaar validation is successful";
		}
		else {
			log("Error in Aadhaar validation.");
			//return "Aadhaar validation failed";
		}

		}
		else
			log("Incorrect OTP, Re-enter correct OTP.");
	}


	@Test(priority=3)
	public void pan() {
		String type="1. Proprietary / एकल स्वामित्व";String pan ="FIXPB7031N";String gstinAvlOrnot ="No";
		
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		WebElement typeOfOrg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='col-md-4'][1]//select[@name='ctl00$ContentPlaceHolder1$ddlTypeofOrg'][@class='form-control']")));
		Select select = new Select(typeOfOrg);
		select.selectByVisibleText(type);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		WebElement panNo = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter Pan Number'][@type='text']")));
		panNo.sendKeys(pan);
		log("PAN number passed");

		WebElement validateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@type='submit'][@value='PAN Validate']")));
		validateButton.click();
		log("PAN validate is clicked");




		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			log("Error: Interrupted while waiting for page to load.");
			//return "PAN validation failed due to page loading error.";
		}
		
		WebElement panSuccessMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("#ctl00_ContentPlaceHolder1_lblPanError")));
		

		if (panSuccessMsg.isDisplayed()) {
			log("PAN validation is success");

			WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//input[@type='submit'][@value='Continue']")));
			continueButton.click();
			log("Continue button is clicked");

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				log("Error: Interrupted while waiting for checkboxes after clicking continue to load.");
				//return "GSTIN validation failed due to checkbox loading error.";
			}

			//WebElement gstinLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(
					//By.xpath("//label[contains(text(),('4.3 Do you have GSTIN ?'))]")));

			WebElement radioGroup = driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rblWhetherGstn']"));
			List<WebElement> radioButtons =radioGroup.findElements(By.tagName("label"));
			for (WebElement radioButton : radioButtons) {
				if (radioButton.getText().equalsIgnoreCase(gstinAvlOrnot)) {
					radioButton.click();
					break;
				}
			}


			//return "PAN and GSTIN validation is success";
		} else {
			log("Error in PAN & GSTIN validation.");
			//return "Error in PAN & GSTIN validation.";
		
		}
	
//		//else
//			log("Udyam Registration has already done through this PAN");
	}
	

	@Test(priority=4)
	public void mobAndEmail() {
		
		String mobileNo= "8248647995"; String mail ="devibala246@gmail.com";

		WebElement mobNo= driver.findElement(By.xpath("//input[@placeholder='Example:- 9999999999'][@type='text']"));
		wait.until(ExpectedConditions.visibilityOf(mobNo)).sendKeys(String.valueOf(mobileNo));

		WebElement mailId= driver.findElement(By.xpath("//input[@placeholder='Example:- info@gmail.com'][@type='text']"));
		wait.until(ExpectedConditions.visibilityOf(mailId)).sendKeys(mail);

		if (wait.until(ExpectedConditions.attributeContains(mobNo, "value", String.valueOf(mobileNo)))
	            && wait.until(ExpectedConditions.attributeContains(mailId, "value", mail))) {
	       // //return "Mobile number and email added successfully.";
	    }
		else {
			Assert.fail("Failed to add mobile number and email.");
			//return "Failed to add mobile number and email.";
		}
		
		
	}

	@Test(priority=5)
	public void socCat() {

		String socCat = "General / सामान्य";String gender = "Female";String abledOrNot = "No";
		
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
			
			Thread.sleep(2000);
		
			WebElement genderValue= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rbtGender']")));
			List<WebElement> genderOptions = genderValue.findElements(By.tagName("label"));
			for (WebElement option2 : genderOptions) {
				if (option2.getText().toLowerCase().contains(gender.toLowerCase())) {
					option2.click();
					break;
				}
			}

			Thread.sleep(2000);

			WebElement abled = driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rbtPh']"));
			List<WebElement> radioOptions = abled.findElements(By.tagName("label"));
			for (WebElement option3 : radioOptions) {
				if (option3.getText().toLowerCase().contains(abledOrNot.toLowerCase())) {
					option3.click();
					break;
				}
			}
			//return "Social category, gender, and ability status added successfully.";
		}
		catch (Exception e) {
			log("Error: Failed to add social category, gender, and ability status");
			//return "Error: Failed to add social category, gender, and ability status.";
		}

	}	
	
	@Test(priority=6)
    public void nameOfEnterp() {
        try {
        	
        	String entName = "Testing Enterprise";String unitName = "test unit";
            
        	WebElement enterName = driver.findElement(By.xpath("//input[@placeholder='Name of Enterprise']"));
            wait.until(ExpectedConditions.visibilityOf(enterName)).sendKeys(entName);

            WebElement enter = driver.findElement(By.xpath("//input[@placeholder='Unit Name']"));
            wait.until(ExpectedConditions.visibilityOf(enter)).sendKeys(unitName);

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                log("Error: Interrupted while waiting for page to load.");
               // //return "Failed to add unit"; // Return statement with a message
            }

            WebElement addUnit = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$btnAddUnit'][@value='Add Unit']"));
            wait.until(ExpectedConditions.visibilityOf(addUnit)).click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log("Error: Interrupted while waiting for page to load.");
               // //return "Failed to add unit"; // Return statement with a message
            }

          
            WebElement unitNameTable = driver.findElement(By.xpath("//th[contains(text(),'Unit Name')]"));
            if (wait.until(ExpectedConditions.visibilityOf(unitNameTable)).isDisplayed()) {
                log("Unit added successfully.");
                //return "Unit added successfully"; // Return statement with a message
            } else {
                log("Failed to add unit.");
                //return "Failed to add unit"; // Return statement with a message
            }
        } catch (Exception e) {
            log("Failed to update the enterprise & unit name.");
            //return "Failed to update the enterprise & unit name"; // Return statement with a message
        }
    }

	@Test(priority=7)
	public void locationOfPlant() {

		String doorNo="12/a";
		String name ="Anbaham";
		String town="pattukkottai";
		String block="perumal kovil street";
		String road="Aranthanagi road";
		String city="pattukkottai";
		String pin="614601";
		String state="30. TAMIL NADU / तमिलनाडु";
		String district ="25. THANJAVUR / तंजावुर";
		try {

			try {
				WebElement unitNameDropdown = driver.findElement(By.id("ctl00_ContentPlaceHolder1_ddlUnitName"));
				Select selectUnitName = new Select(unitNameDropdown);
				selectUnitName.selectByValue("1");
				log("Plant selected");
			} catch (Exception e) {
				//return "Failed to select plant";
				// handle the exception
			}

			try {
				WebElement doorNumber = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPFlat'][@placeholder='Flat/Door/Block No.']"));
				wait.until(ExpectedConditions.visibilityOf(doorNumber)).sendKeys(String.valueOf(doorNo));
				log("Door no passed");

			} catch (Exception e) {
				//return "Failed to pass door no";
				// handle the exception
			}

			try {
				WebElement nameOfBuilding = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPBuilding'][@placeholder='Name of Premises/ Building']"));
				wait.until(ExpectedConditions.visibilityOf(nameOfBuilding)).sendKeys(String.valueOf(name));
				log("Building name passed");
			} catch (Exception e) {
				//return "Failed to enter building name";
				// handle the exception
			}

			try {
				WebElement townField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPVillageTown'][@placeholder='Village/Town']"));
				wait.until(ExpectedConditions.visibilityOf(townField)).sendKeys(String.valueOf(town));
				log("Town name passed");
			} catch (Exception e) {
				//return "Failed to enter town name";
				// handle the exception
			}

			try {
				WebElement blockField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPBlock'][@placeholder='Block']"));
				wait.until(ExpectedConditions.visibilityOf(blockField)).sendKeys(String.valueOf(block));
				log("Block name passed");
			} catch (Exception e) {
				//return "Failed to enter block";
				// handle the exception
			}

			try {
				WebElement roadField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPRoadStreetLane'][@placeholder='Road/ Street/ Lane']"));
				wait.until(ExpectedConditions.visibilityOf(roadField)).sendKeys(String.valueOf(road));
				log("Road name passed");
			} catch (Exception e) {
				//return "Failed to pass road name";
				// handle the exception
			}

			try {
				WebElement cityField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPCity'][@placeholder='City']"));
				wait.until(ExpectedConditions.visibilityOf(cityField)).sendKeys(String.valueOf(city));
				log("City passed");
			} catch (Exception e) {
				//return "Failed to enter city name";
				// handle the exception
			}

			try {
				WebElement pincode = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtPpin'][@placeholder='Pin']"));
				wait.until(ExpectedConditions.visibilityOf(pincode)).sendKeys(String.valueOf(pin));
				log("Pin passed");
			} catch (Exception e) {
				//return "Failed to enter pincode";
				// handle the exception
			}

			try {
				WebElement stateField = driver.findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddlPState'][@title='State_Namee']"));
				wait.until(ExpectedConditions.visibilityOf(stateField)).click();


				WebElement stateDropdown = driver.findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddlPState'][@title='State_Namee']"));
				Select selectState = new Select(stateDropdown);
				selectState.selectByVisibleText(state);

			} catch (Exception e) {
				//return "Failed to select state";
				// handle the exception
			}
			Thread.sleep(5000);

			try {
				WebElement districtDropdown = driver.findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddlPDistrict'][@id='ctl00_ContentPlaceHolder1_ddlPDistrict']"));
				Select selectDistrict = new Select(districtDropdown);
				selectDistrict.selectByVisibleText(district);
			}
			catch (Exception e) {
				e.printStackTrace();
				//return "Failed to update district"; // failure
			}
			try {
				Thread.sleep(6000);//waiting for loading to add plant
			} catch (Exception e) {
				//return "Failed to load to get updated";
				// TODO: handle exception
			}

			try {
				WebElement addPlantBtn = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$BtnPAdd'][@value='Add Plant']"));
				wait.until(ExpectedConditions.visibilityOf(addPlantBtn)).click();

			} catch (Exception e) {
				log("Failed to click add plant button");
				//return "Failed to click add plant button";
				// TODO: handle exception
			}

			try {
				Thread.sleep(6000);//waiting for loading to add plant
			} catch (Exception e) {
				//return "Failed to load to get updated";
				// TODO: handle exception
			}
			//return "Location of the plant updated successfully";

		}	
		catch (Exception e) {
			e.printStackTrace();
			//return "Failed to update plant location"; // failure
		}
		finally {
		}

	}
	
	@Test(priority=8)
	public void enterpAddress() {

		try {
			String doorNo="12/a";
			String name ="Anbaham";
			String town="pattukkottai";
			String block="perumal kovil street";
			String road="Aranthanagi road";
			String city="pattukkottai";
			String pin="614601";
			String state="30. TAMIL NADU / तमिलनाडु";
			String district ="25. THANJAVUR / तंजावुर";
			
			try {
				WebElement unitNameDropdown = driver.findElement(By.id("ctl00_ContentPlaceHolder1_ddlUnitName"));
				Select selectUnitName = new Select(unitNameDropdown);
				selectUnitName.selectByValue("1");
				log("Plant selected");
			} catch (Exception e) {
				//return "Failed to select plant";
				// handle the exception
			}

			try {
				WebElement doorNumber = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtOffFlatNo'][@placeholder='Flat/ Door/ Block No.']"));
				wait.until(ExpectedConditions.visibilityOf(doorNumber)).sendKeys(String.valueOf(doorNo));
				log("Door no passed");

			} catch (Exception e) {
				//return "Failed to pass door no";
				// handle the exception
			}

			try {
				WebElement nameOfBuilding = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtOffBuilding'][@placeholder='Name of Premises/ Building']"));
				wait.until(ExpectedConditions.visibilityOf(nameOfBuilding)).sendKeys(String.valueOf(name));
				log("Building name passed");
			} catch (Exception e) {
				//return "Failed to enter building name";
				// handle the exception
			}

			try {
				WebElement townField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtOffVillageTown'][@placeholder='Village/Town']"));
				wait.until(ExpectedConditions.visibilityOf(townField)).sendKeys(String.valueOf(town));
				log("Town name passed");
			} catch (Exception e) {
				//return "Failed to enter town name";
				// handle the exception
			}

			try {
				WebElement blockField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtOffBlock'][@placeholder='Block']"));
				wait.until(ExpectedConditions.visibilityOf(blockField)).sendKeys(String.valueOf(block));
				log("Block name passed");
			} catch (Exception e) {
				//return "Failed to enter block";
				// handle the exception
			}

			try {
				WebElement roadField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtOffRoadStreetLane'][@placeholder='Road/ Street/ Lane']"));
				wait.until(ExpectedConditions.visibilityOf(roadField)).sendKeys(String.valueOf(road));
				log("Road name passed");
			} catch (Exception e) {
				//return "Failed to pass road name";
				// handle the exception
			}

			try {
				WebElement cityField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtOffCity'][@placeholder='City']"));
				wait.until(ExpectedConditions.visibilityOf(cityField)).sendKeys(String.valueOf(city));
				log("City passed");
			} catch (Exception e) {
				//return "Failed to enter city name";
				// handle the exception
			}

			try {
				WebElement pincode = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtOffPin'][@placeholder='Pin']"));
				wait.until(ExpectedConditions.visibilityOf(pincode)).sendKeys(String.valueOf(pin));
				log("City passed");
			} catch (Exception e) {
				//return "Failed to enter pincode";
				// handle the exception
			}

			try {
				WebElement stateField = driver.findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddlstate'][@title='State_Namee']"));
				wait.until(ExpectedConditions.visibilityOf(stateField)).click();


				WebElement stateDropdown = driver.findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddlstate'][@title='State_Namee']"));
				Select selectState = new Select(stateDropdown);
				selectState.selectByVisibleText(state);

			} catch (Exception e) {
				//return "Failed to select state";
				// handle the exception
			}
			try {
				Thread.sleep(6000);//waiting for loading to add plant
			} catch (Exception e) {
				//return "Failed to load to get updated";
				// TODO: handle exception
			}

			try {
				WebElement districtDropdown = driver.findElement(By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddlDistrict'][@id='ctl00_ContentPlaceHolder1_ddlDistrict']"));
				Select selectDistrict = new Select(districtDropdown);
				selectDistrict.selectByVisibleText(district);
			}
			catch (Exception e) {
				e.printStackTrace();
				//return "Failed to update district"; // failure
			}

			try {
				Thread.sleep(6000);//waiting for loading to add plant
			} catch (Exception e) {
				//return "Failed to load to get updated";
				// TODO: handle exception
			}

			//return "Enterprise address updated successfully";

		}	
		catch (Exception e) {
			e.printStackTrace();
			//return "Failed to update Enterprise address"; // failure
		}
		finally {


		}

	}
	
	@Test(priority=9)
	public void updateRegistrationStatus() throws Exception {

		String dateOfIncorporation="12/02/2000"; String busCommenced="No";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(dateOfIncorporation, formatter);
		

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			WebElement dateField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtdateIncorporation'][@id='ctl00_ContentPlaceHolder1_txtdateIncorporation']"));
			wait.until(ExpectedConditions.visibilityOf(dateField)).sendKeys(date.format(formatter));
			log("Date passed : "+date.format(formatter));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		WebElement yesRadioButton = driver.findElement(By.id("ctl00_ContentPlaceHolder1_rblcommenced_0"));
		WebElement noRadioButton = driver.findElement(By.id("ctl00_ContentPlaceHolder1_rblcommenced_1"));

		if (busCommenced.equalsIgnoreCase("yes")) {
			yesRadioButton.click();
			Thread.sleep(2000);
			WebElement dateBus= driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtcommencedate'][@id='ctl00_ContentPlaceHolder1_txtcommencedate']"));
			dateBus.sendKeys("01/04/2006");
		} else {
			noRadioButton.click();
		}
		
		//return "Registration Status updated";

	}
	
	@Test(priority=10)
	public void bankDetails() {
		
		String name="Indian bank";
		String ifsc="IDIB000P031";
		String accNo ="6951522444";
		String category ="Manufacturing";
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			WebElement bankName = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtBankName'][@placeholder='Enter Bank Name']"));
			wait.until(ExpectedConditions.visibilityOf(bankName)).sendKeys(name);
			log("Bank name passed");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

			WebElement ifscNumber = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtifsccode'][@placeholder='Example:- SBIN0001624']"));
			wait.until(ExpectedConditions.visibilityOf(ifscNumber)).sendKeys(ifsc);
			log("Ifsc passed");
		
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			WebElement accountNo = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtaccountno'][@maxlength='18']"));
			wait.until(ExpectedConditions.visibilityOf(accountNo)).sendKeys(accNo);
			log("Account number passed");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		WebElement manufacturingRadio = driver.findElement(By.xpath("//label[contains(text(),'Manufacturing')]"));
		WebElement servicesRadio = driver.findElement(By.xpath("//label[contains(text(),'Services')]"));

		if (category.equalsIgnoreCase("Manufacturing")) {
			manufacturingRadio.click();
		} else if (category.equalsIgnoreCase("Services")) {
			servicesRadio.click();
		}
		//return "Bank details and category updated";

	}	
	@Test(priority=11)
	public void nicCode()  {
		String category="Manufacturing";
		String twoDig="31-Manufacture of furniture";
		String fourDig="3100-Manufacture of furniture";
		String fiveDig="31001-Manufacture of furniture made of wood";
		

//		Map<String, WebElement> categoryRadios = new HashMap<String, WebElement>();
//		categoryRadios.put("Manufacturing", driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rdbCatggMultiple']//label[contains(text(),'Manufacturing')]")));
//		categoryRadios.put("Services", driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rdbCatggMultiple']//label[contains(text(),'Services')]")));
//		categoryRadios.put("Trading", driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rdbCatggMultiple']//label[contains(text(),'Trading')]")));
//
//		categoryRadios.get(category).click();
		
		WebElement radioButton = driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_rdbCatggMultiple']//label[contains(text(),'" + category + "')]"));
		radioButton.click();

		try {
			Thread.sleep(3000);

		} catch (Exception e) {
			//return "Failed to load";
			// TODO: handle exception
		}

		WebElement twoDigit = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddl2NicCode']")));
		Select selectTwo = new Select(twoDigit);
		selectTwo.selectByVisibleText(twoDig);


		try {
			Thread.sleep(3000);// waiting for loading
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log("Error : waiting for loading");
		}
		WebElement fourDigit = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddl4NicCode']")));
		Select selectFour = new Select(fourDigit);
		selectFour.selectByVisibleText(fourDig);

		try {
			Thread.sleep(3000);// waiting for loading
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log("Error : waiting for loading");
		}
		WebElement fiveDigit = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//select[@name='ctl00$ContentPlaceHolder1$ddl5NicCode']")));
		Select selectfive = new Select(fiveDigit);
		selectfive.selectByVisibleText(fiveDig);

		try {
			Thread.sleep(3000);

		} catch (Exception e) {
			//return "Failed to load";
			// TODO: handle exception
		}
		WebElement addActivity = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@name='ctl00$ContentPlaceHolder1$btnAddMore'][@value='Add Activity']")));
		wait.until(ExpectedConditions.visibilityOf(addActivity)).click();
		
		try {
			Thread.sleep(5000);

		} catch (Exception e) {
			//return "Failed to load";
			// TODO: handle exception
		}

		 WebElement unitNameTable = driver.findElement(By.xpath("//th[contains(text(),'NIC 2 Digit')]"));
         if (wait.until(ExpectedConditions.visibilityOf(unitNameTable)).isDisplayed()) {
             log("NIC added successfully.");
             //return "Unit added successfully"; // Return statement with a message
         } else {
             log("Failed to add NIC code.");
             //return "Failed to add unit"; // Return statement with a message
         }
		
		//return "Updated NIC codes";


	}	
	
	@Test(priority=12)
	public void noOfEmployees() {

		String maleCount="10";
		String femaleCount="2";
		String othersCount ="0";
		
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtNoofpersonMale")).sendKeys(maleCount != null ? maleCount : "");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtNoofpersonFemale")).sendKeys(femaleCount != null ? femaleCount : "");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtNoofpersonOthers")).sendKeys(othersCount != null ? othersCount : "");
	
	//return "Employees count added";

}
	@Test(priority=13)
	public void investmentAndTurnover() {
		int wdv= 200000;
		int pollution= 200000;
		int turnover= 200000;
		try {
		WebElement wdvField = driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtDepCost"));
		wait.until(ExpectedConditions.visibilityOf(wdvField)).sendKeys(String.valueOf(wdv));
		
	    WebElement pollutionField = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtExCost'][@id='ctl00_ContentPlaceHolder1_txtExCost']"));
	    wait.until(ExpectedConditions.visibilityOf(pollutionField)).sendKeys(String.valueOf(pollution));
		
	  
	    WebElement turnOver = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$txtTotalTurnoverA'][@id='ctl00_ContentPlaceHolder1_txtTotalTurnoverA']"));
	    wait.until(ExpectedConditions.visibilityOf(turnOver)).sendKeys(String.valueOf(turnover));
		
	
		
		//return "Investment and Turnover updated";	
		
	}
	catch (Exception e) {
		//return "Error in updating investment and turnover";
		// TODO: handle exception
	}
		
		

		
	}
	@Test(priority=14)
	public void declarations() {
		String dicNo="Thanjavur";
		try {
		WebElement gem = driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_rblGeM_0']"));
		wait.until(ExpectedConditions.visibilityOf(gem)).click();
		
		WebElement treds = driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_rblTReDS_0']"));
		wait.until(ExpectedConditions.visibilityOf(treds)).click();		
	  
		WebElement ncs = driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_rblNCS_0']"));
		wait.until(ExpectedConditions.visibilityOf(ncs)).click();
		
		WebElement nsic = driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_rblnsic_0']"));
		wait.until(ExpectedConditions.visibilityOf(nsic)).click();

		
		WebElement dic = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlDIC']"));
		wait.until(ExpectedConditions.visibilityOf(dic)).click();
		
		Select selectTwo = new Select(dic);
		selectTwo.selectByVisibleText(dicNo);
		
		
		WebElement submitOtp = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$btnsubmit']"));
		wait.until(ExpectedConditions.visibilityOf(submitOtp)).click();
		
		//return "Declaration updated";	
		
	}
	catch (Exception e) {
		//return "Error in updating declarations";
		// TODO: handle exception
	}
	}
	
	@Test(priority=15)
	public void otp() {
		
		WebElement submitOtp = driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$btnsubmit']"));
		wait.until(ExpectedConditions.visibilityOf(submitOtp)).click();
		// switch to the alert popup
		Alert alert = driver.switchTo().alert();

		// get the text of the alert message
		String alertText = alert.getText();

		// check if the alert message contains the expected text
		if(alertText.contains("Are you sure that you have entered correct data. Please check your mobile number is correct?")) {
		    // click on the "Cancel" button
		    alert.dismiss();
		    System.out.println("Alert contains the given text");
		} else {
			 alert.dismiss();
		}

//		// Switch to the alert
//		Alert alert = driver.switchTo().alert();
//
//		// Get the alert text and check if it contains the message about the mobile number
//		String alertText = alert.getText();
//		if (alertText.contains("Please check your mobile number is correct")) {
//		    // If the message is found, accept the alert
//		    alert.accept();
//		} else {
//		    // If the message is not found, dismiss the alert
//		    alert.dismiss();
//		}
//
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//return "Registration done!";
	
	}
	
	
	
	

}


