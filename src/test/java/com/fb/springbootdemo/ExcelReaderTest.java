package com.fb.springbootdemo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fb.springbootdemo.utils.excel.ExcelReader2;

public class ExcelReaderTest {

	public static void main(String[] args) {
	    // 设定Excel文件所在路径
        String excelFileName = "D:\\develop\\excel\\2018年工资.xlsx";
        // 读取Excel文件内容
        Map<String, Object> excelParam = new HashMap<>();
        excelParam.put("startSheet", 1);
//        excelParam.put("endSheet", 2);
        excelParam.put("startRow", 3);
        Integer[] columns = {1, 2, 8, 25, 29, 31};
        excelParam.put("columns", columns);
        Integer[] requireColumns = {1,2};
        excelParam.put("requireColumns",requireColumns);
        excelParam.put("numericScale", "0");
        excelParam.put("needSheetName", true);
        Map<String, Object> readResult = ExcelReader2.readExcel(excelFileName, excelParam);
        List<Map<String, Object>> successList = (List<Map<String, Object>>)readResult.get("successList");
        List<Map<String, Object>> failureList = (List<Map<String, Object>>)readResult.get("failureList");
        System.out.println("成功个数：" + successList.size());
        System.out.println("成功列表");
        for(int i =0; i<successList.size(); i++) {
        	System.out.println(successList.get(i));
        }
        System.out.println("失败个数：" + failureList.size());
        System.out.println("失败列表");
        for(int i =0; i<failureList.size(); i++) {
        	System.out.println(failureList.get(i));
        }
	}

}
