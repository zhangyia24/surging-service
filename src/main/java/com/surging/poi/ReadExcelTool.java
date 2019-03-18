package com.surging.poi;

/**
 * Created by Administrator on 2018/10/31.
 */

import java.io.InputStream;

import com.surging.entity.ExcelObject;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;


/**
 * Created by Lenovo on 2018/1/21.
 */
public class ReadExcelTool {

    public static ExcelObject readExcel(String fileName, int sheetId,int startRow) throws Exception {
        ExcelObject excelObject=new ExcelObject();
        List<String> nameList=new ArrayList<>();
        List<String> columnList=new ArrayList<>();
        List<Map<String,String>> excelList=new ArrayList<>();
        XSSFRow xssfRow=null;
        InputStream is = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = workbook.getSheetAt(sheetId);
        if(xssfSheet==null){
            return null;
        }
        XSSFRow nameRow = xssfSheet.getRow(0);
        nameList=readColumn(nameRow);
        excelObject.setNameList(nameList);
        //遍历Row
        for(int rowNum=startRow;rowNum<=xssfSheet.getLastRowNum();rowNum++){
            xssfRow = xssfSheet.getRow(rowNum);
            Map<String,String> rowMap=new HashMap<>();
            if (xssfRow == null) {
                continue;
            }
            columnList=readColumn(xssfRow);
            for (int i=0;i<nameList.size();i++){
                String cell;
                try{
                    cell = columnList.get(i);
                }catch (Exception e){
                    cell=" ";
                }
                rowMap.put(nameList.get(i),cell);
            }
            excelList.add(rowMap);
        }
        excelObject.setExcelList(excelList);
        return excelObject;
    }

    public static List<String> readColumn(XSSFRow xssfRow){
        List<String> listRow=new ArrayList<>();
        String value=null;
        try {
            for(int column=0;column<xssfRow.getLastCellNum();column++){
                Cell cell = xssfRow.getCell(column, Row.CREATE_NULL_AS_BLANK);
                switch(cell.getCellType()){
                    case Cell.CELL_TYPE_BLANK:
                        value = cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        value = Boolean.toString(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        value = "";
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        value = cell.getStringCellValue();
                        if (value != null) {
                            value = value.replaceAll("#N/A", "").trim();
                        }
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            value = String.valueOf(cell.getDateCellValue());
                        } else {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String temp = cell.getStringCellValue();
                            // 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
                            if (temp.indexOf(".") > -1) {
                                value = String.valueOf(new Double(temp)).trim();
                            } else {
                                value = temp.trim();
                            }
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue().trim();
                        break;
                    default:
                        value = "";
                        break;
                }
                value.trim();
                listRow.add(value);
            }
        }catch (Exception e){
            System.out.println("--------------error------------------------");
        }

        return listRow;
    }
}
