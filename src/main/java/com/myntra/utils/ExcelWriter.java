package com.myntra.utils;

import org.apache.poi.xssf.usermodel.*;
import java.io.FileOutputStream;

public class ExcelWriter {

	static XSSFWorkbook wb = new XSSFWorkbook();
	static XSSFSheet sheet = wb.createSheet("Results");
	static int rowNum = 0;

	public static void write(String id, String desc, String exp, String act, String status) {

		XSSFRow row = sheet.createRow(rowNum++);

		row.createCell(0).setCellValue(id);
		row.createCell(1).setCellValue(desc);
		row.createCell(2).setCellValue(exp);
		row.createCell(3).setCellValue(act);
		row.createCell(4).setCellValue(status);

		try (FileOutputStream fos = new FileOutputStream("TestResults.xlsx")) {
			wb.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}