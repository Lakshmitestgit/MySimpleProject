package methods;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import driver.DriverScript;

public class AppIndependentMethods extends DriverScript{
	/**********************************
	 * Method Name		: writeResult()
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public void writeResult(String strStatus, String strMessage)
	{
		Logger log = null;
		try {
			log = Logger.getLogger("Results");
			
			switch(strStatus.toLowerCase())
			{
				case "pass":
					log.info(strMessage);
					break;
				case "fail":
					log.error(strMessage);
					break;
				case "exception":
					log.fatal(strMessage);
					break;
				case "#":
					log.info(strMessage);
					break;
				default:
					System.out.println("Invalid result status '"+strStatus+"'.");
			}
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'writeResult()' method. "+e.getMessage());
		}
		finally
		{
			log = null;
		}
	}
	
	
	/**********************************
	 * Method Name		: clickObject()
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean clickObject(WebDriver oDriver, By objBy)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed())
			{
				appInd.writeResult("Pass", "The element '"+String.valueOf(objBy)+"' was clicked successful");
				oEle.click();
				return true;
			}else {
				appInd.writeResult("Fail", "The element '"+String.valueOf(objBy)+"' was not found");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'clickObject()' method. "+e.getMessage());
			return false;
		}
		finally
		{
			oEle = null;
		}
	}
	
	
	/**********************************
	 * Method Name		: setObject()
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean setObject(WebDriver oDriver, By objBy, String strData)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed())
			{
				appInd.writeResult("Pass", "The data '"+strData+"' was entered in the element '"+String.valueOf(objBy)+"' successful");
				oEle.sendKeys(strData);
				return true;
			}else {
				appInd.writeResult("Fail", "The element '"+String.valueOf(objBy)+"' was not found");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'setObject()' method. "+e.getMessage());
			return false;
		}
		finally
		{
			oEle = null;
		}
	}
	
	
	/**********************************
	 * Method Name		: clearAndSetObject()
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean clearAndSetObject(WebDriver oDriver, By objBy, String strData)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed())
			{
				appInd.writeResult("Pass", "The data '"+strData+"' was entered in the element '"+String.valueOf(objBy)+"' successful");
				oEle.clear();
				oEle.sendKeys(strData);
				return true;
			}else {
				appInd.writeResult("Fail", "The element '"+String.valueOf(objBy)+"' was not found");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'clearAndSetObject()' method. "+e.getMessage());
			return false;
		}
		finally
		{
			oEle = null;
		}
	}
	
	
	
	/**********************************
	 * Method Name		: compareValues()
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean compareValues(String strActual, String strExpected)
	{
		try {
			if(strActual.equals(strExpected))
			{
				appInd.writeResult("Pass", "The actual '"+strActual+"' & expected '"+strExpected+"' are matching");
				return true;
			}else {
				appInd.writeResult("Fail", "Mis-match in both actual '"+strActual+"' & expected '"+strExpected+"' values");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'compareValues()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	/**********************************
	 * Method Name		: verifyText()
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean verifyText(WebDriver oDriver, By objBy, String strObjType, String strExpected)
	{
		WebElement oEle = null;
		String strActual = null;
		Select oSel = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed())
			{
				switch(strObjType.toLowerCase())
				{
					case "text":
						strActual = oEle.getText();
						break;
					case "value":
						strActual = oEle.getAttribute("value");
						break;
					case "list":
						oSel = new Select(oEle);
						strActual = oSel.getFirstSelectedOption().getText();
						break;
					default:
						appInd.writeResult("Fail", "Invalid object Type '"+strObjType+"' was mentioned.");
				}
				
				if(appInd.compareValues(strActual, strExpected))
				{
					return true;
				}else {
					return false;
				}
			}else {
				appInd.writeResult("Fail", "The element '"+String.valueOf(objBy)+"' was not found");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'verifyText()' method. "+e.getMessage());
			return false;
		}
		finally
		{
			oEle = null;
			oSel = null;
		}
	}
	
	
	/**********************************
	 * Method Name		: verifyObjectExist()
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean verifyObjectExist(WebDriver oDriver, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(objBy);
			if(oEles.size()>0) {
				appInd.writeResult("Pass", "The element '"+String.valueOf(objBy)+"' was exist successful");
				return true;
			}else {
				appInd.writeResult("Fail", "The element '"+String.valueOf(objBy)+"' DOESN'T exist in the DOM");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'verifyObjectExist()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	/**********************************
	 * Method Name		: verifyObjectNotExist()
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean verifyObjectNotExist(WebDriver oDriver, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(objBy);
			if(oEles.size()>0) {
				appInd.writeResult("Fail", "The element '"+String.valueOf(objBy)+"' exist in the DOM");
				return false;
			}else {
				appInd.writeResult("Pass", "The element '"+String.valueOf(objBy)+"' was removed successful");
				return true;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'verifyObjectNotExist()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	/**********************************
	 * Method Name		:
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public WebDriver launchBrowser(String browserName)
	{
		WebDriver oDriver = null;
		try {
			switch(browserName.toLowerCase())
			{
				case "chrome":
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Library\\drivers\\chromedriver.exe");
					oDriver = new ChromeDriver();
					break;
				case "firefox":
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Library\\drivers\\geckodriver.exe");
					oDriver = new FirefoxDriver();
					break;
				case "iexplore":
					System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Library\\drivers\\IEDriverServer.exe");
					oDriver = new InternetExplorerDriver();
					break;
				default:
					writeResult("Fail","Invalid browser '"+browserName+"' name was mentioned");
			}
			
			if(oDriver!=null)
			{
				oDriver.manage().window().maximize();
				writeResult("Pass", "The '"+browserName+"' browser was launched successful");
				return oDriver;
			}else {
				writeResult("Fail", "Failed to launch the '"+browserName+"' browser");
				return null;
			}
		}catch(Exception e)
		{
			writeResult("Exception", "Exception while executing 'launchBrowser()' method. "+e.getMessage());
			return null;
		}
	}
	
	
	/**********************************
	 * Method Name		: closeBrowser()
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public boolean closeBrowser(WebDriver oDriver)
	{
		try {
			oDriver.close();
			return true;
		}catch(Exception e)
		{
			writeResult("Exception", "Exception while executing 'closeBrowser()' method. "+e.getMessage());
			return false;
		}
	}
}
