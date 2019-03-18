package com.surging.poi.poiService;

import com.surging.entity.ExcelObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.surging.poi.ReadExcelTool.readExcel;


/**
 * Created by Administrator on 2018/10/31.
 */
public class SqlGenerateService {
    public ExcelObject oracleSqlGenerate(String fileName,int sheetId,int startRow,String date) throws Exception {
        Map<String,String> oracleSqlMap=new HashMap<>();
        ExcelObject excelObject=new ExcelObject();
        String oracleSqlFull="";
        String oracleSqlIncrement="";
        Map<String,String> rowMapDto=null;
        excelObject=readExcel(fileName,sheetId,startRow);
        List<Map<String, String>> excelList = excelObject.getExcelList();
        List<String> nameList = excelObject.getNameList();
        nameList.add("源库全量sql");
        nameList.add("源库增量sql");
        excelObject.setNameList(nameList);
        String extractType;
        String dbType;
        try{
            for(int i=0;i<excelList.size();i++){
                rowMapDto=excelList.get(i);
                if (rowMapDto.get("是否增量抽取")==null){
                    System.out.println("----------SqlGenerateService----------null-------------------");

                }
                extractType=rowMapDto.get("是否增量抽取").toUpperCase();
                dbType=rowMapDto.get("JDBC连接串").toLowerCase();
                if(dbType.matches("(.*)oracle(.*)")){
                    oracleSqlFull="select '"+rowMapDto.get("Schema")+"." +rowMapDto.get("表英文名")
                            +"',count(*) from "
                            +rowMapDto.get("Schema")+"."+rowMapDto.get("表英文名")+";";
                    excelList.get(i).put("源库全量sql",oracleSqlFull);
//                    System.out.println(oracleSqlFull);

                    if (extractType.equals("Y")){
//                        System.out.println("--------------------Y-------------------------");
                        oracleSqlIncrement="select '"+rowMapDto.get("Schema")+"." +rowMapDto.get("表英文名")
                                +"',count(*) from "
                                +rowMapDto.get("Schema")+"."+rowMapDto.get("表英文名")
                                +" where trunc("+rowMapDto.get("增量字段")+") = to_date('"+date+"','YYYY-MM-DD');";
                        excelList.get(i).put("源库增量sql",oracleSqlIncrement);
//                        System.out.println(oracleSqlIncrement);
                    }else if(extractType.equals("N")){
//                        System.out.println("--------------------N-------------------------");
                        oracleSqlIncrement=" ";
                        oracleSqlMap.put("源库增量sql",oracleSqlIncrement);
                        excelList.get(i).put("源库增量sql",oracleSqlIncrement);
                    }
                }


                excelObject.setExcelList(excelList);
            }
        }catch (Exception e){

        }

        return excelObject;
    }
}
