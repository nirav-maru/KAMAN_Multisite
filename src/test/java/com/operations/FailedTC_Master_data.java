package com.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.operations.Common.Constants;
import com.operations.Common.FireClass;
import com.operations.Common.ReadStats;
import com.operations.Common.ReadUserconfig;
import com.operations.Common.Xls_Reader;
import com.testcases.driverscripts.Execute_FailedScript;
import com.testcases.driverscripts.Execute_MainScript;

public class FailedTC_Master_data {

	static Xls_Reader Readexcel = null;
	static XSSFWorkbook Master_Workbook = null;
	static ReadUserconfig uc =new ReadUserconfig();
	static ReadStats rs =new ReadStats();


	@DataProvider(name = "Fetch_FailedTC_data")
	public static Object [][] passdata() throws IOException  {
		//public static void main(String[] args) throws IOException {
		uc.getUserConfig();
		rs.getRepositoryValues();
		Object[][]Sheet_data = null;
		Object[][]Total_data=null;
		int Final_rows=0;
		int Final_columns=0;
		int Total_Master_sheets = 0;
		XSSFSheet sh1;
		int rows_temp = 0;	
		int cols_temp;
		int Total_rows;
		Total_rows=0;
		cols_temp=0;


		ReadFailexcel();


		if (!(Readexcel==null && Master_Workbook==null)) {

			Total_Master_sheets=Readexcel.getNumberOfSheets(Master_Workbook);

			for (int k = 0; k <Total_Master_sheets; k++) {

				sh1 = Master_Workbook.getSheetAt(k);
				int rows_currentsheet =Readexcel.getRowCountBySheetnumber(k);
				rows_temp=rows_currentsheet;

			}
			Total_rows=Total_rows+rows_temp;

			cols_temp=Readexcel.getColumnCountBySheetnumber(0);
			Total_data=new Object[Total_rows-1][cols_temp];

			for (int k = 0; k <Total_Master_sheets; k++) {

				sh1 = Master_Workbook.getSheetAt(k);
				rows_temp = Readexcel.getRowCountBySheetnumber(k);

				int i;
				int j = 0;

				Sheet_data = new Object[rows_temp][cols_temp];

				for (i = 1; i<= rows_temp-1; i++) {


					for (j = 0; j < cols_temp; j++) {

						Sheet_data[i][j]=sh1.getRow(i).getCell(j).getStringCellValue();

						Total_data[Final_rows][Final_columns]=Sheet_data[i][j];
						Final_columns++;
					}

					Final_rows++;
					if(Final_columns==cols_temp){
						Final_columns=0;
					}

				}

			}

			int totalfailtc = Total_data.length;
			System.out.println("Total Failed testcases to execute again :" + totalfailtc);
		}

		else {

			System.out.println("There are NO Failed Testcases Found......");
		}
		return Total_data;
	}

	private static void ReadFailexcel() {

		File Master_file = null;
		FireClass FC = new FireClass();
		Execute_MainScript EM = new Execute_MainScript();

		String FailedFilePath=EM.FailedsheetPath;
		System.out.println("");
		//String FailedFilePath="D:\\ROhit\\Rohit\\Automation\\Demo\\KAMAN_ReExecuteFailedTcs\\KAMAN_ECTEST_IE_SANITY\\Failed_Reports\\2020-05-06_19-53-29\\Kaman\\IE";

		if (!(FailedFilePath==null)) {

			if(uc.OS.equalsIgnoreCase("Windows")) {

				Master_file =new File(FailedFilePath);
				//Master_file =	new File("D:\\ROhit\\Rohit\\Automation\\Demo\\KAMAN_ReExecuteFailedTcs\\KAMAN_ECTEST_IE_SANITY\\Failed_Reports\\2020-05-06_19-53-29\\Kaman\\IE");
			}

			else if (uc.OS.equalsIgnoreCase("Linux")) {


				// Master_file =	new File("/Input_files/Master_executors/MasterExecutor"+"_"+str+".xlsx");
				// (System.getProperty("user.dir")+"/Resources/Config.properties")
			}

			else {

				System.out.println("Please Specify OS correctly i.e. either Windows or Linux...!!!!");
			}

			FileInputStream Master_inputStream = null;

			Readexcel = new Xls_Reader(Master_file+"/"+"FailedTC's"+EM.ExecutionRound+".xlsx");

			try {

				Master_inputStream = new FileInputStream(Master_file+"/"+"FailedTC's"+EM.ExecutionRound+".xlsx");
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				Master_Workbook = new XSSFWorkbook(Master_inputStream);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}


}
