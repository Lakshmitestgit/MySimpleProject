package testScripts;

import org.openqa.selenium.WebDriver;
import driver.DriverScript;

public class UserModuleTestScripts extends DriverScript{
	/**********************************
	 * Script Name		: TS_LoginLogout
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean TS_LoginLogout()
	{
		String strStatus = null;
		WebDriver oBrowser = null;
		try {
			oBrowser = appInd.launchBrowser("FireFox");
			if(oBrowser!=null)
			{
				strStatus+=String.valueOf(appDep.navigateURL(oBrowser));
				strStatus+=String.valueOf(appDep.loginToApp(oBrowser, "Login_101"));
				strStatus+=String.valueOf(appDep.logoutFromApp(oBrowser));
				strStatus+=String.valueOf(appInd.closeBrowser(oBrowser));
				
				if(strStatus.contains("false"))
				{
					appInd.writeResult("Fail", "The test script 'TS_LoginLogout()' was failed.");
					return false;
				}else {
					appInd.writeResult("Pass", "The test script 'TS_LoginLogout()' was Passed.");
					return true;
				}
			}else {
				appInd.writeResult("Fail", "Failed to launch the browser");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'TS_LoginLogout()' test script. "+e.getMessage());
			return false;
		}
	}
	
	
	/**********************************
	 * Script Name		: TS_CreateDeleteUser
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean TS_CreateDeleteUser()
	{
		String strStatus = null;
		WebDriver oBrowser = null;
		try {
			oBrowser = appInd.launchBrowser("FireFox");
			if(oBrowser!=null)
			{
				strStatus+=String.valueOf(appDep.navigateURL(oBrowser));
				strStatus+=String.valueOf(appDep.loginToApp(oBrowser, "Login_101"));
				strStatus+=String.valueOf(userMethods.createUser(oBrowser, "user_103"));
				strStatus+=String.valueOf(userMethods.deleteUser(oBrowser, System.getProperty("RT_User")));
				strStatus+=String.valueOf(appDep.logoutFromApp(oBrowser));
				strStatus+=String.valueOf(appInd.closeBrowser(oBrowser));
				
				if(strStatus.contains("false"))
				{
					appInd.writeResult("Fail", "The test script 'TS_CreateDeleteUser()' was failed.");
					return false;
				}else {
					appInd.writeResult("Pass", "The test script 'TS_CreateDeleteUser()' was Passed.");
					return true;
				}
			}else {
				appInd.writeResult("Fail", "Failed to launch the browser");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'TS_CreateDeleteUser()' test script. "+e.getMessage());
			return false;
		}
	}
	
}
