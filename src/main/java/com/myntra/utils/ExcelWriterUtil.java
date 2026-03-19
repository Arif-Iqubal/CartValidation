package com.myntra.utils;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriterUtil {

	    private static final String FILE_PATH = "reports/ExecutionReport.xlsx";
	    private static final Object lock = new Object(); // thread-safe

	    public static void writeResult(String testId,
	                                   String description,
	                                   String expected,
	                                   String actual,
	                                   String status,
	                                   String screenshotPath) {

	        synchronized (lock) {

	            Workbook workbook = null;
	            Sheet sheet = null;

	            try {
	                File file = new File(FILE_PATH);

	                // Create folder if not exists
	                file.getParentFile().mkdirs();

	                // Open or create workbook
	                if (file.exists()) {
	                    workbook = WorkbookFactory.create(file);
	                    sheet = workbook.getSheet("Results");

	                    if (sheet == null) {
	                        sheet = workbook.createSheet("Results");
	                        createHeader(sheet);
	                    }

	                } else {
	                    workbook = new XSSFWorkbook();
	                    sheet = workbook.createSheet("Results");
	                    createHeader(sheet);
	                }

	                // Create new row
	                int rowCount = sheet.getLastRowNum() + 1;
	                Row row = sheet.createRow(rowCount);

	                row.createCell(0).setCellValue(testId);
	                row.createCell(1).setCellValue(description);
	                row.createCell(2).setCellValue(expected);
	                row.createCell(3).setCellValue(actual);
	                row.createCell(4).setCellValue(status);

	                // 📸 Screenshot Hyperlink
	                if (screenshotPath != null && !screenshotPath.isEmpty()) {

	                    CreationHelper helper = workbook.getCreationHelper();

	                    Cell cell = row.createCell(5);
	                    cell.setCellValue("View Screenshot");

	                    Hyperlink link = helper.createHyperlink(HyperlinkType.FILE);
	                    File filee = new File(screenshotPath);
	                    link.setAddress(filee.toURI().toString());

	                    cell.setHyperlink(link);
	                } else {
	                    row.createCell(5).setCellValue("N/A");
	                }

	                // Auto-size columns
	                for (int i = 0; i <= 5; i++) {
	                    sheet.autoSizeColumn(i);
	                }

	                // Write file
	                FileOutputStream fos = new FileOutputStream(FILE_PATH);
	                workbook.write(fos);
	                fos.close();
	                workbook.close();

	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    // 🔹 Create Header Row
	    private static void createHeader(Sheet sheet) {

	        Row header = sheet.createRow(0);

	        header.createCell(0).setCellValue("Test ID");
	        header.createCell(1).setCellValue("Description");
	        header.createCell(2).setCellValue("Expected Result");
	        header.createCell(3).setCellValue("Actual Result");
	        header.createCell(4).setCellValue("Status");
	        header.createCell(5).setCellValue("Screenshot");
	    }
	}
