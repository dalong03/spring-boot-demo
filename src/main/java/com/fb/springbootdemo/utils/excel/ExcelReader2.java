package com.fb.springbootdemo.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Description: 读取Excel内容
 */
public class ExcelReader2 {


    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";
    private static final Logger logger = LoggerFactory.getLogger(ExcelReader2.class);

    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     * @param inputStream 读取文件的输入流
     * @param fileType 文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    /**
     * 读取Excel文件内容
     * @param fileName 要读取的Excel文件所在路径
     * @return 读取结果列表，读取失败时返回null
     */
    public static Map<String, Object> readExcel(String fileName, Map<String, Object> excelParam) {
    	
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
    		workbook = getWorkbook(inputStream, fileType);
    		
    		// 读取excel中的数据
    		Map<String, Object> ret = parseExcel(workbook, excelParam);
    		
    		return ret;
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
     * 解析Excel数据
     * @param workbook Excel工作簿对象
     * @return 解析结果
     */
    private static Map<String, Object> parseExcel(Workbook workbook, Map<String, Object> excelParam) {
    	Map<String, Object> ret = new HashMap<>();
    	List<Map<String, Object>> successList = new ArrayList<>();
    	List<Map<String, Object>> failureList = new ArrayList<>();
       Integer startSheet = 0; 
       Integer endSheet = null; 
       if(excelParam.get("startSheet") != null) {
    	   startSheet = (Integer)excelParam.get("startSheet");
       }
       if(excelParam.get("endSheet") != null) {
    	   endSheet = (Integer)excelParam.get("endSheet");
       } else {
    	   endSheet = workbook.getNumberOfSheets();
       }
       
       Boolean needSheetName= (Boolean)excelParam.get("needSheetName");
        // 解析sheet
        for (int sheetNum = startSheet; sheetNum < endSheet; sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);

            String sheetName = null;
            if(needSheetName != null && needSheetName == true) {
            	sheetName = sheet.getSheetName();
            }

            // 解析每一行的数据，构造数据对象
            Integer startRow = 0; 
            Integer endRow = null; 
            if(excelParam.get("startRow") != null) {
            	startRow = (Integer)excelParam.get("startRow");
            }
            if(excelParam.get("endRow") != null) {
            	endRow = (Integer)excelParam.get("endRow");
            } else {
            	endRow = sheet.getPhysicalNumberOfRows();
            }
            
            Integer[] columns = null;
            Integer[] requireColumns = (Integer[])excelParam.get("requireColumns");
            Integer startColumn = null; 
            Integer endColumn = null; 
            if(excelParam.get("columns") != null) {
            	columns = (Integer[])excelParam.get("columns");
            } else {
            	if(excelParam.get("startColumn") != null) {
            		startColumn = (Integer)excelParam.get("startColumn");
            	} else {
            		startColumn = 0;
            	}
            	if(excelParam.get("endColumn") != null) {
            		endColumn = (Integer)excelParam.get("endColumn");
            	}
            }
            
            String numericScale = (String)excelParam.get("numericScale");
            
            Map<String, Object> resultData = null;
            for (int i = startRow; i < endRow; i++) {
                Row row = sheet.getRow(i);
                if (null == row) {
                    continue;
                }
                if(columns != null) {
                	resultData = convertRowToMap(row, columns, numericScale);
                } else {
                	resultData = convertRowToMap(row, startColumn, endColumn, numericScale);
                }
//                if(requireColumns != null) {
//                	for(Integer j : requireColumns) {
//                		if(resultData.get("c" + j) == null || "".equals(resultData.get("c" + j))) {
//                			
//                		}
//                	}
//                }
//                if (null == resultData) {
//					logger.warn("第 " + row.getRowNum() + "行数据不合法");
//					failureList.add(resultData);
//                    continue;
//                }
                if(needSheetName != null && needSheetName) {
                	resultData.put("sheetName", sheetName);
                }
                successList.add(resultData);
            }
        }
        ret.put("successList", successList);
        ret.put("failureList", failureList);
        return ret;
    }

    /**
     * 将单元格内容转换为字符串
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell, String numericScale) {
        if(cell==null){
            return null;
        }
        Object value = null;
        switch (cell.getCellType()) {
            case NUMERIC:   //数字
            	value = cell.getNumericCellValue();
                break;
            case STRING:    //字符串
            	value = cell.getStringCellValue().replace(" ", "");
                break;
            case BOOLEAN:   //布尔
                value = cell.getBooleanCellValue();
                break;
            case BLANK:     // 空值
                break;
            case FORMULA:   // 公式
//            	value = cell.getCellFormula();
            	value = cell.getNumericCellValue();
                DecimalFormat df = new DecimalFormat(numericScale);
            	value = new BigDecimal((String)df.format(value));
                break;
            case ERROR:     // 故障
                break;
            default:
                break;
        }
        return value;
    }
    

    /**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     *
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     *
     * @param row 行数据
     * @return 解析后的行数据对象，行数据错误时返回null
     */
    private static Map<String, Object> convertRowToMap(Row row, Integer startColumn, Integer endColumn, String numericScale) {
    	Map<String, Object> resultData = new HashMap<String, Object>();

    	Cell cell;
		for(int i = startColumn; i < endColumn; i++) {
			cell = row.getCell(i);
			resultData.put("c" + i, getCellValue(cell, numericScale));
		}
        return resultData;
    }
    
    private static Map<String, Object> convertRowToMap(Row row, Integer[] columns, String numericScale) {
    	Map<String, Object> resultData = new HashMap<String, Object>();
    	
    	Cell cell;
		for(Integer i : columns) {
			cell = row.getCell(i);
			resultData.put("c" + i, getCellValue(cell, numericScale));
		}
    	
    	return resultData;
    }
}
