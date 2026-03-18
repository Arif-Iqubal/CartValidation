package com.myntra.utils;

import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import java.util.*;

public class ExcelReader {

	public static List<String> getProductNames(String path) {

		List<String> list = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(path); XSSFWorkbook wb = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = wb.getSheetAt(0);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				list.add(sheet.getRow(i).getCell(0).getStringCellValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}