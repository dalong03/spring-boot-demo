package com.fb.springbootdemo.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.Cell;

public abstract class ExcelUtils {
	public static Object getCellValue(Cell cell) {
		return getCellValue(cell, null);
	}
	
	public static Object getCellValue(Cell cell, String numericScale) {
		if (cell == null) {
			return null;
		}
		Object value = null;
		switch (cell.getCellType()) {
		case NUMERIC: // 数字
			value = cell.getNumericCellValue();
			break;
		case STRING: // 字符串
			value = cell.getStringCellValue().trim();
			break;
		case BOOLEAN: // 布尔
			value = cell.getBooleanCellValue();
			break;
		case BLANK: // 空值
			break;
		case FORMULA: // 公式
			value = cell.getNumericCellValue();
			DecimalFormat df = new DecimalFormat(numericScale);
			value = new BigDecimal((String) df.format(value));
			break;
		case ERROR: // 故障
			break;
		default:
			break;
		}
		return value;
	}
}
