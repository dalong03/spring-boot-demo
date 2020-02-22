package com.fb.springbootdemo.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelWriter2 {
	
    private static final Logger logger = LoggerFactory.getLogger(ExcelReader2.class);

    /**
     * 生成Excel并写入数据信息
     * @param dataList 数据列表
     * @return 写入数据后的工作簿对象
     */
    public static Map<String, Object> importData(List<Map<String, Object>> dataList, String fileName, String sheetName){
    	Map<String, Object> returnMap = new HashMap<>();
    	List<Map<String, Object>> successList = new ArrayList<>();
    	List<Map<String, Object>> failureList = new ArrayList<>();
    	Workbook workbook = null;
    	FileInputStream inputStream = null;
    	try {
    		// 获取Excel后缀名
    		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    		// 获取Excel文件
    		File excelFile = new File(fileName);
    		if (!excelFile.exists()) {
    			logger.warn("指定的Excel文件不存在！");
    			return null;
    		}
    		
    		// 获取Excel工作簿
    		inputStream = new FileInputStream(excelFile);
    		workbook = ExcelReader2.getWorkbook(inputStream, fileType);
    		Sheet sheet = null;
    		if(sheetName != null) {
    			sheet = workbook.getSheet(sheetName);
    		} else {
    			sheet = workbook.getSheetAt(0);
    		}
    		
    		 //构建每行的数据内容
            int rowNum = 2;
            for (Iterator<Map<String, Object>> it = dataList.iterator(); it.hasNext(); ) {
            	Map<String, Object> data = it.next();
                if (data == null) {
                    continue;
                }
                //输出行数据
                Row row = sheet.createRow(rowNum++);
                convertDataToRow(data, row);
            }
            return returnMap;
    		
    	} catch (Exception e) {
    		logger.warn("解析Excel失败，文件名：" + fileName + " 错误信息：" + e.getMessage());
    		return null;
    	} finally {
    		try {
    			if (null != workbook) {
    				workbook.close();
    			}
    			if (null != inputStream) {
    				inputStream.close();
    			}
    		} catch (Exception e) {
    			logger.warn("关闭数据流出错！错误信息：" + e.getMessage());
    			return null;
    		}
    	}
       
    }

    /**
     * 将数据转换成行
     * @param data 源数据
     * @param row 行对象
     * @return
     */
    private static void convertDataToRow(Map<String, Object> data, Row row){
        int cellNum = 0;
        Cell cell;
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getName() ? "" : data.getName());
        cell = row.createCell(cellNum++);
        if (null != data.getAge()) {
            cell.setCellValue(data.getAge());
        } else {
            cell.setCellValue("");
        }
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getLocation() ? "" : data.getLocation());
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getJob() ? "" : data.getJob());
    }
}
