package methods;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import driver.DriverScript;

public class AppDependentMethods extends DriverScript{
	/**********************************
	 * Method Name		: loginToApp
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean loginToApp(WebDriver oDriver, String strLogicalName)
	{
		String strStatus = null;
		Map<String, String> objData = null;
		try {
			objData = datatable.getDataFromExcel("loginToApp", strLogicalName);
			
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.id("username"), objData.get("UserName")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("pwd"), objData.get("Password")));
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.linkText("Login")));
			Thread.sleep(2000);
			
			strStatus+=String.valueOf(appInd.verifyText(oDriver, By.xpath("//td[@class='pagetitle']"), "Text", "Enter Time-Track"));
			
			//HAndle the shortcut
			if(oDriver.findElements(
					By.xpath("//div[@style='display: block;']")).size()>0)
			{
				oDriver.findElement(By.id("gettingStartedShortcutsMenuCloseId")).click();
			}
				
			if(strStatus.contains("false"))
			{
				appInd.writeResult("Fail", "The login to actiTime was failed.");
				return false;
			}else {
				appInd.writeResult("Pass", "The login to actiTime was successful.");
				return true;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'loginToApp()' method. "+e.getMessage());
			return false;
		}
		finally
		{
			objData = null;
		}
	}
	
	
	
	/**********************************
	 * Method Name		: logoutFromApp
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean logoutFromApp(WebDriver oDriver)
	{
		String strStatus = null;
		try {
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.linkText("Logout")));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.verifyText(oDriver, By.id("headerContainer"), "Text", "Please identify yourself"));
			if(strStatus.contains("false"))
			{
				appInd.writeResult("Fail", "Failed to logout from the application");
				return false;
			}else {
				appInd.writeResult("Pass", "Logout was successful");
				return true;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'logoutFromApp()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	/**********************************
	 * Method Name		: navigateURL
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean navigateURL(WebDriver oDriver)
	{
		try {
			oDriver.navigate().to("http://localhost/login.do");
			Thread.sleep(2000);
			
			if(oDriver.getTitle().equals("actiTIME - Login"))
			{
				appInd.writeResult("Pass", "The URL was navigated successful");
				return true;
			}else {
				appInd.writeResult("Fail", "Failed to navigated the URL");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'navigateURL()' method. "+e.getMessage());
			return false;
		}
	}
	
}
