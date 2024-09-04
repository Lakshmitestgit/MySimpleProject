package methods;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import driver.DriverScript;

public class UserModuleMethods extends DriverScript{
	/**********************************
	 * Method Name		: createUser
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean createUser(WebDriver oDriver, String strLogicalName)
	{
		String strStatus = null;
		Map<String, String> objData = null;
		try {
			objData = datatable.getDataFromExcel("createUser", strLogicalName);
			
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[text()='USERS']")));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[text()='Add User']")));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("firstName"), objData.get("FirstName")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("lastName"), objData.get("LastName")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("email"), objData.get("Email")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("username"), objData.get("U_UserName")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("password"), objData.get("U_Password")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("passwordCopy"), objData.get("U_RetypePWd")));
			
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//span[text()='Create User']")));
			Thread.sleep(2000);
			
			strStatus+=String.valueOf(appInd.verifyText(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+objData.get("LastName")+", "+objData.get("FirstName")+"'"+"]"), "Text", objData.get("LastName")+", "+objData.get("FirstName")));
			if(oDriver.findElements(By.xpath("//div[@class='name']/span[text()="+"'"+objData.get("LastName")+", "+objData.get("FirstName")+"'"+"]")).size()>0)
			{
				System.setProperty("RT_User", objData.get("LastName")+", "+objData.get("FirstName"));
				appInd.writeResult("Pass", "User created successful");
				return true;
			}else {
				appInd.writeResult("Fail", "Failed to create the user");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'createUser()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	
	/**********************************
	 * Method Name		: deleteUser()
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean deleteUser(WebDriver oDriver, String strUserName)
	{
		String strStatus = null;
		try {
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+strUserName+"'"+"]")));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//button[contains(text(),'Delete User')]")));
			Thread.sleep(2000);
			oDriver.switchTo().alert().accept();
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.verifyObjectNotExist(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+strUserName+"'"+"]")));
			
			if(strStatus.contains("false"))
			{
				appInd.writeResult("Fail", "Failed to delete the user");
				return false;
			}else {
				appInd.writeResult("Pass", "The user was deleted successful");
				return true;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'deleteUser()' method. "+e.getMessage());
			return false;
		}
	}
}
