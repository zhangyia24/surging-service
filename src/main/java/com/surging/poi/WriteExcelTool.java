package com.surging.poi;

import com.surging.entity.ExcelObject;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Created by Administrator on 2018/10/31.
 */
public class WriteExcelTool {


    public static void writeExcelCell(Sheet sheet,int rowNum,int columnNum,String value){
        if(sheet==null){
            System.out.println("sheet is null----------------");
        }
        if(value==null){
            System.out.println("value is null----------------");
        }

        Row row = sheet.getRow(rowNum);
        if(row==null){
            row = sheet.createRow(rowNum);
        }
        row.createCell(columnNum).setCellValue(value);

    }

    public static void writeExcelObject(Sheet sheet, ExcelObject excelObject){
        int startRow=0;
        if(excelObject.getNameList()!=null){
            startRow=1;
            for (int columnNum=0;columnNum<excelObject.getColumnSize();columnNum++){
                System.out.println(excelObject.getNameList().get(columnNum));
                writeExcelCell(sheet,0,columnNum,excelObject.getNameList().get(columnNum));
            }
        }

        for(int rowNum=startRow;rowNum<excelObject.getRowSize()+startRow;rowNum++){
            for (int columnNum=0;columnNum<excelObject.getColumnSize();columnNum++){
                writeExcelCell(sheet,rowNum,columnNum,excelObject.getCell(rowNum-startRow,columnNum));
            }

        }

//        for (int i=startRow;i<3;i++){
//            rowMap=excelList.get(i);
//            String fullSql = rowMap.get("源库全量sql");
//            int fullSqlIndex = nameList.indexOf("源库全量sql");
////            System.out.println(fullSql);
//            writeExcelCell(xssfSheet,i,fullSqlIndex,fullSql);
//            String incrementSql = rowMap.get("源库增量sql");
//            int incrementSqlIndex = nameList.indexOf("源库增量sql");
////            System.out.println(incrementSql);
//            writeExcelCell(xssfSheet,i,incrementSqlIndex,incrementSql);
//        }
    }
}
