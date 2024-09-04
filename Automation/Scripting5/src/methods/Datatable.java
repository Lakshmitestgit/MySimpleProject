package methods;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import driver.DriverScript;

public class Datatable extends DriverScript{
	/**********************************
	 * Method Name		: getDataFromExcel()
	 * Purpose			:
	 * Author			:
	 * Date creation	:
	 * Date modified	:
	 * Reviewer			:
	 ***********************************/
	public Map<String, String> getDataFromExcel(String strSheetName, String strLogicalName)
	{
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row1 = null;
		Row row2 = null;
		Cell cell1 = null;
		Cell cell2 = null;
		int intRowNum = 0;
		String strKey = null;
		String strValue = null;
		String sDay = null;
		String sMonth = null;
		String sYear = null;
		Map<String, String> objData = null;
		try {
			objData = new HashMap<String, String>();
			fin = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\TestData.xlsx");
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(strSheetName);
			if(sh==null || wb.getSheetIndex(strSheetName)<0)
			{
				appInd.writeResult("Fail", "The sheet '"+strSheetName+"' doesnot exist.");
				return null;
			}
			
			int rowNum = sh.getPhysicalNumberOfRows();
			for(int rows=0; rows<rowNum; rows++)
			{
				row1 = sh.getRow(rows);
				cell1 = row1.getCell(0);
				if(cell1.getStringCellValue().equals(strLogicalName))
				{
					intRowNum = rows;
					break;
				}
			}
			
			if(intRowNum>0)
			{
				row1 = sh.getRow(0);
				row2 = sh.getRow(intRowNum);
				for(int rows=0; rows<row1.getLastCellNum(); rows++)
				{
					cell1 = row1.getCell(rows);
					strKey = cell1.getStringCellValue();
					cell2 = row2.getCell(rows);
					
					//format and read the data
					if(cell2==null||cell2.getCellType()==CellType.BLANK)
					{
						strValue = "";
					}else if(cell2.getCellType()==CellType.BOOLEAN)
					{
						strValue = String.valueOf(cell2.getBooleanCellValue());
					}
					else if(cell2.getCellType()==CellType.STRING)
					{
						strValue = cell2.getStringCellValue();
					}
					else if(cell2.getCellType()==CellType.NUMERIC)
					{
						if(HSSFDateUtil.isCellDateFormatted(cell2))
						{
							double dt = cell2.getNumericCellValue();
							Calendar cal = Calendar.getInstance();
							cal.setTime(HSSFDateUtil.getJavaDate(dt));
							
							//Day should be prefixed with Zero if <10
							if(cal.get(Calendar.DAY_OF_MONTH)<10)
							{
								sDay = "0"+cal.get(Calendar.DAY_OF_MONTH);
							}else {
								sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
							}
							
							//Month should be prefixed with Zero if <10
							if((cal.get(Calendar.MONTH)+1)<10)
							{
								sMonth = "0"+(cal.get(Calendar.MONTH)+1);
							}else {
								sMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
							}
							
							sYear = String.valueOf(cal.get(Calendar.YEAR));
							
							strValue = sDay+"/"+sMonth+"/"+sYear;
						}else {
							strValue = String.valueOf(cell2.getNumericCellValue());
						}
					}
					objData.put(strKey, strValue);
				}
			}else {
				appInd.writeResult("Fail", "The logicalName '"+strLogicalName+"' doesnot exist");
				return null;
			}
			return objData;
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'getDataFromExcel()' method. "+e.getMessage());
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				cell1 = null;
				cell2 = null;
				row1 = null;
				row2 = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				appInd.writeResult("Exception", "Exception while executing 'getDataFromExcel()' method. "+e.getMessage());
				return null;
			}
		}
	}
}
