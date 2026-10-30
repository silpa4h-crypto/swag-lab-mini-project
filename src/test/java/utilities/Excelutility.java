
	package utilities;

	import java.io.FileInputStream;
	import java.io.IOException;

	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class Excelutility {

		public static Object[][] getTestData(String filePath, String sheetName) throws IOException {
				 FileInputStream fis = new FileInputStream(filePath);

			     XSSFWorkbook workbook = new XSSFWorkbook(fis);

			     XSSFSheet sheet = workbook.getSheet(sheetName);

			     int rows = sheet.getLastRowNum();
			     int cols = sheet.getRow(0).getLastCellNum();

			     Object[][] data = new Object[rows][cols];

			     DataFormatter formatter = new DataFormatter();

			     for (int i = 1; i <= rows; i++) {

			         for (int j = 0; j < cols; j++) {

			             data[i - 1][j] =
			                     formatter.formatCellValue(
			                             sheet.getRow(i).getCell(j)
			                     );
			         }
			     }

			     workbook.close();
			     fis.close();

			     return data;
			 }
			}

