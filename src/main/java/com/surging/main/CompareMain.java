package com.surging.main;

import com.surging.entity.ExcelObject;
import com.surging.poi.ExcelColumnOption;
import com.surging.poi.WorkbookTool;
import com.surging.poi.WriteExcelTool;
import com.surging.tools.ExcelCostant;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;

import static com.surging.poi.ReadExcelTool.readExcel;

/**
 * Created by Administrator on 2018/11/1.
 */
public class CompareMain {
    public static void main(String[] args) throws Exception {
        String fileName="source.xlsx";
        String targetFileName="target.xlsx";
        String resultFileName="result.xlsx";
        int startRow=1;
        int sheetId=0;
        ExcelObject excelObjectSource=readExcel(fileName,sheetId,startRow);

        ExcelObject excelObjectTarget=readExcel(targetFileName,sheetId,startRow);
        excelObjectSource.excelCompare(excelObjectTarget,"表英文名");
        excelObjectSource.print();
        ExcelObject excelObject= ExcelColumnOption.excelColumnFilter(excelObjectSource,ExcelCostant.EXCEL_COMPAIR_ALL_EXISTS);
//        excelObjectTarget.getColumn("源库记录数");
//        excelObject
        Workbook workbook = WorkbookTool.createWorkbook(resultFileName);
        Sheet sheet = WorkbookTool.getSheet(workbook, "神器sheet");
        WriteExcelTool.writeExcelObject(sheet,excelObject);
        FileOutputStream fileOut = new FileOutputStream(resultFileName);
        WorkbookTool.write(workbook,fileOut);
//        excelObject.print();
    }
}
