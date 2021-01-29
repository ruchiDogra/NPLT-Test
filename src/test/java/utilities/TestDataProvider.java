package utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	/*	@DataProvider(name = "dp")
	public Object[][] getData()
	{
		Object[][] data = new Object[3][2];
		
		data[0][0] = "ruchi.dogra.cdac@gmail.com";
		data[0][1] = "Lotus@123";

		data[1][0] = "ruchid@cdac.in";
		data[1][1] = "Lotus@123";
		
		data[2][0] = "sanjayt@cdac.in";
		data[2][1] = "Lotus@123";

		return data;
	}
*/	

	//read data from xls 
		@DataProvider(name = "dp")
		public Object[][] getData(Method m) // Method is function of Reflection class, which gives the name of the fuction which is calling getData
		{
			
			//reading data from xls file. xls reading code is in utilities/ExcelReader.java
			//testdata.xls is kept in the resources folder
			ExcelReader excel = new ExcelReader("/home/ruchi/eclipse-workspace/NPLT-Test/src/test/resources/excel/testdata.xls"); // ExcelReader class constructor needs string excel path as parameter which you wants to read
			String sheetName = " ";
			
			if( m.getName().equals("RegisterAsIndividual"))
			{
				sheetName = "RegisterAsIndividual";
				System.out.println("test data sheet name is: " + m.getName());
			}
			else if (m.getName().equals("RegisterAsIndianAcademic"))
			{
				System.out.println("test data sheet name is " + m.getName());
			}
			else if (m.getName().equals("RegisterAsStartup"))
			{
				System.out.println("test data sheet name is " + m.getName());
			}
			else if(m.getName().contentEquals("RegisterAsMSME"))
			{
				System.out.println("test data sheet name is " + m.getName());
			}
			
			int rowCnt = excel.getRowCount(sheetName);
			int colCnt = excel.getColumnCount(sheetName);
			Object [][] data = new Object[rowCnt-1][colCnt];
			
			for(int i=0; i < rowCnt-1; i++ )
			{
				for(int j=0; j< colCnt; j++)
				{
					data[i][j] = excel.getCellData(sheetName, j, i+2);
					System.out.println("row number is " + i + "col number is" + j + data[i][j]);
				}
			}
			
			return data;
		}
		
		@DataProvider(name = "dp1") // Method is function of Reflection class, which gives the name of the fuction which is calling getData
		public Object[][] getDataUsingHashTable(Method m)
		{
			ExcelReader excel = new ExcelReader("./src/test/resources/excel/testdata.xls");
			String sheetName = "";
			sheetName = m.getName();
			int colCnt = excel.getColumnCount(sheetName);  // m.getName() will return name of the calling method, which should be same as sheet name.
			int rowCnt = excel.getRowCount(sheetName);
			Object[][] data = new Object[rowCnt-1][1]; // as first row is heading of columns
			
			//to read more about Hashtable and other collections
			Hashtable<String, String> tableData = null;
			
			for(int rowNum=0; rowNum< rowCnt-1; rowNum++ )
			{
				tableData = new Hashtable<String, String>();
				for(int colNum=0; colNum< colCnt; colNum++)
				{
//					data[rowNum][colNum] = excel.getCellData(sheetName, colNum, rowNum+2); //rowNum =2 is the value row 
					System.out.println("value of row " + rowNum + " and colNum " + colNum + "is " + data[rowNum+2][colNum]); 
					tableData.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum+2));
					data[rowNum][0]= tableData; 
				}
			}
			return data;
		}
}
