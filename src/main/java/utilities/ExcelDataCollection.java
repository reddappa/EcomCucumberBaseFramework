package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataCollection {
	
	public static Map<String,Map<String,Map<String,String>>> globalExcelData;
	public static Map<String,Map<String,String>> scenarioSheetData;
	public static Map<String,Map<String,String>> accountsSheetData;
	public static Map<String,Map<String,String>> usersSheetData;
	
	
	public static Workbook getWorkBook(String filepath) throws IOException
	{
		Workbook workbook=null;
		
		FileInputStream fis = new FileInputStream(filepath);
		ZipSecureFile.setMinInflateRatio(0);
		workbook=new XSSFWorkbook(fis);
		return workbook;
		
	}
	
	public static Map<String,Map<String,String>> sheetData(Workbook workbook , String sheetName)
	{
		
		Map<String,Map<String,String>> sheetDataByName = new HashMap();
		
		Sheet sheetData = workbook.getSheet(sheetName);
		
		Row firstRow = sheetData.getRow(0);
		
		for(int i =0;i<=sheetData.getLastRowNum();i++)
		{
			Map<String,String> valuePairs = new HashMap();
			
			try
			{
				Row row = sheetData.getRow(i);
				String rowFirstValue = row.getCell(0).getStringCellValue().trim();
				
				for(int j=0;j<=row.getLastCellNum()-1;j++)
				{
					try {
						String headerData = firstRow.getCell(j).getStringCellValue().trim();
						String rowValueData =row.getCell(j).getStringCellValue().trim();
						
						valuePairs.put(headerData, rowValueData);
					}catch(Exception E)
					{
						
					}
					sheetDataByName.put(rowFirstValue, valuePairs);
				}
				
				
			}catch(Exception e)
			{
				
			}
			
		}
		return sheetDataByName;
		
	}
	
	public static Map<String,Map<String,Map<String,String>>> sheetData(String filePath) throws IOException
	{
		Map<String,Map<String,Map<String,String>>> sheetDataCollection = new HashMap();
		
		Workbook workbook = getWorkBook(filePath);
		
		for(int i=0;i<=workbook.getNumberOfSheets()-1;i++)
		{
			String currentSheetName=workbook.getSheetName(i);
			Map<String,Map<String,String>> shhetDataMap = sheetData(workbook,currentSheetName);
			sheetDataCollection.put(currentSheetName, shhetDataMap);
		}
		return sheetDataCollection;
		
	}
	
	public static Map<String,Map<String,String>> getSheettoData(String sheetName)
	{
		Map<String,Map<String,String>> sheetData = globalExcelData.get(sheetName);
		return sheetData;
		
	}
	
	
	public static String getSpecificKeyValue(Map<String,Map<String,String>> dataCollection , String rowName,String key)
	{
		String value = null;
		Map<String,String> rawData = dataCollection.get(rowName);
		if(rawData!=null)
		{
			value=rawData.get(key);
		}
		return value;
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
