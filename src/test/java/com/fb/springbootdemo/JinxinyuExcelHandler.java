package com.fb.springbootdemo;

import java.util.List;

import com.fb.springbootdemo.utils.excel.ExcelDataVO;
import com.fb.springbootdemo.utils.excel.ExcelReader;

public class JinxinyuExcelHandler {
	public static void main(String[] args) {
	    // 设定Excel文件所在路径
        String excelFileName = "D:/develop/excel/2018年工资.xlsx";
        // 读取Excel文件内容
        List<ExcelDataVO> readResult = ExcelReader.readExcel(excelFileName);
	}
}
