package driver;

import methods.AppDependentMethods;
import methods.AppIndependentMethods;
import methods.Datatable;
import methods.TaskModuleMethods;
import methods.UserModuleMethods;
import testScripts.TaskModuleTestScripts;
import testScripts.UserModuleTestScripts;

public class DriverScript {
	public static AppIndependentMethods appInd = null;
	public static AppDependentMethods appDep = null;
	public static UserModuleMethods userMethods = null;
	public static TaskModuleMethods taskMethods = null;
	public static UserModuleTestScripts userScritps = null;
	public static TaskModuleTestScripts taskScripts = null;
	public static Datatable datatable = null;
	public static boolean blnRes = false;
	
	public static void main(String[] args) {
		appInd = new AppIndependentMethods();
		appDep = new AppDependentMethods();
		userMethods = new UserModuleMethods();
		userScritps = new UserModuleTestScripts();
		taskMethods = new TaskModuleMethods();
		taskScripts = new TaskModuleTestScripts();
		datatable = new Datatable();
		
		
		blnRes = userScritps.TS_LoginLogout();
		if(blnRes) System.out.println("PASSED");
		else System.out.println("FAILED");
		appInd.writeResult("#","************************************************");
		
		blnRes = userScritps.TS_CreateDeleteUser();
		if(blnRes) System.out.println("PASSED");
		else System.out.println("FAILED");
	}
}
