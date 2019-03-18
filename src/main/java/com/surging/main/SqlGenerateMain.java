package com.surging.main;

/**
 * Created by Administrator on 2018/10/31.
 */
public class SqlGenerateMain {

    public static void main(String[] args) throws Exception {
//        String fileName="20181101-ODS上线清单定稿.xlsx";
//        String targetFileName="target.xlsx";
//        String date="20181031";
//        int startRow=1;
//        int sheetId=1;
////        File file=new File(targetFileName);
////        if(!file.exists())
////            file.createNewFile();
//        InputStream is = new FileInputStream(targetFileName);
//        XSSFWorkbook workbook = new XSSFWorkbook(is);
//        XSSFSheet xssfSheet = workbook.getSheet("test");
//
//        SqlGenerateService sqlGenerateService=new SqlGenerateService();
//        ExcelObject excelObject = sqlGenerateService.oracleSqlGenerate(fileName, sheetId, startRow, date);
//        List<Map<String, String>> excelList = excelObject.getExcelList();
//        List<String> nameList = excelObject.getNameList();
//        Map<String, String> rowMap;
//        for (int i=0;i<nameList.size();i++){
//            writeExcelCell(xssfSheet,0,i,nameList.get(i));
//        }
//        for (int i=startRow;i<3;i++){
//            rowMap=excelList.get(i);
//            String fullSql = rowMap.get("源库全量sql");
//            int fullSqlIndex = nameList.indexOf("源库全量sql");
//            writeExcelCell(xssfSheet,i,fullSqlIndex,fullSql);
//            String incrementSql = rowMap.get("源库增量sql");
//            int incrementSqlIndex = nameList.indexOf("源库增量sql");
//            writeExcelCell(xssfSheet,i,incrementSqlIndex,incrementSql);
//        }
//        FileOutputStream fileOut = new FileOutputStream(targetFileName);
//        workbook.write(fileOut);
//        fileOut.flush();
//        fileOut.close();
    }
}
