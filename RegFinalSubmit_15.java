package com.ippopay.msmeregistration;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

public class RegFinalSubmit_15 extends AadhaarVerification_1{

	@Test
	public String finalSubmit() {
		
		// Switch to the alert
				Alert alert = driver.switchTo().alert();

				// Get the alert text and check if it contains the message about the mobile number
				String alertText = alert.getText();
				if (alertText.contains("Please check your mobile number is correct")) {
				    // If the message is found, accept the alert
				    alert.accept();
				} else {
				    // If the message is not found, dismiss the alert
				    alert.dismiss();
				}

				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return alertText;
		
		
		
		
	}
}


