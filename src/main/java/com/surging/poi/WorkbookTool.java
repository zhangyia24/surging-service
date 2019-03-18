package com.surging.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Created by Administrator on 2018/11/1.
 */
public class WorkbookTool {
    public static Workbook createWorkbook(String fileName) throws Exception {
        Workbook wb = null;
        if(fileName.endsWith(".xls")) {
            wb = new HSSFWorkbook();
        } else if(fileName.endsWith(".xlsx")) {
            wb = new XSSFWorkbook();
        } else {
            throw new Exception("文件类型错误！");
        }
        OutputStream output=null;
        try{
                Sheet sheet = wb.createSheet("神器sheet");
                output = new FileOutputStream(fileName);
                wb.write(output);
        }catch(FileNotFoundException e) {
            System.out.println("文件创建失败，失败原因为：" + e.getMessage());
            throw new FileNotFoundException();
        }finally {

        }
        System.out.println(fileName + "文件创建成功！");

        return wb;
    }
    public static Sheet getSheet(Workbook wb , String sheetName) {

        Sheet sheet = wb.getSheet(sheetName);

        if(sheet == null) {
            System.out.println("表单" + sheetName + "不存在，试图创建该sheet，请稍后……");
            if(wb.getClass().toString().equals("class org.apache.poi.xssf.usermodel.XSSFWorkbook")){
                XSSFWorkbook xssfWorkbook=(XSSFWorkbook) wb;
                sheet = xssfWorkbook.createSheet(sheetName);
            }else if(wb.getClass().toString().equals("class org.apache.poi.hssf.usermodel.HSSFWorkbook")){
                HSSFWorkbook hssfWorkbook=(HSSFWorkbook) wb;
                sheet = hssfWorkbook.createSheet(sheetName);
            }
            System.out.println("名为" + sheetName +"的sheet创建成功！");
        }

        return sheet;
    }
    public static void write(Workbook workbook, FileOutputStream fileOut){
        try{
            workbook.write(fileOut);
            fileOut.flush();
            // 操作结束，关闭文件
            fileOut.close();
        }catch (IOException e) {}
        finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e1) {}
            }
        }


    }
}
