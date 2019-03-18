package com.surging.entity;

import com.surging.tools.ExcelCostant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/10/31.
 */
public class ExcelObject {
    private String excelName;
    private List<String> nameList=new ArrayList<>();
    private List<Map<String,String>> excelList=new ArrayList<>();

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public List<Map<String, String>> getExcelList() {
        return excelList;
    }

    public void setExcelList(List<Map<String, String>> excelList) {
        this.excelList = excelList;
    }

    public Map<String,String> getRow(int rowNum){
        return this.getExcelList().get(rowNum);
    }

    public int getRowSize(){ return this.getExcelList().size(); }

    public int getColumnSize(){ return this.getNameList().size(); }

    public void addCell(int rowNum,String columnName,String cellValue) {
        try {
            this.getExcelList().get(rowNum).put(columnName,cellValue);
        }catch (Exception e){
            Map<String,String> cellMap=new HashMap<>();
            cellMap.put(columnName,cellValue);
            this.getExcelList().add(cellMap);
        }
    }
    public String getCell(int rowNum,int columnNum){
        String cellValue=this.getRow(rowNum).get(this.getNameList().get(columnNum));
        if(cellValue==null)
            return "";
        return cellValue;
    }
    public void addColumn(String columnName,List<String> newColumnList) {
        this.getNameList().add(columnName);
        int newColumnSize=newColumnList.size();
        for(int i=0;i<newColumnSize;i++){
            this.addCell(i,columnName,newColumnList.get(i));
        }
    }
    public List<String> getColumn(String columnName){
        List<String> columnList=new ArrayList<>();
        for (int i=0;i<this.getRowSize();i++){
            columnList.add(this.getRow(i).get(columnName));
        }
        return columnList;
    }

    public  ExcelObject mergeExcelObject(List<ExcelObject> excelObjectList,String keyColumn){
        String[] keyColumnArray = keyColumn.split(",");

        return null;
    }
    public void excelCompare(ExcelObject excelObject,String compareColumn){
        List<String> sourceColumnList=new ArrayList<>();
        List<String> targetColumnList=new ArrayList<>();
        int sourceSize=this.getExcelList().size();
        int targetSize=excelObject.getExcelList().size();
        Map<String,String> rowMap;
        List<String> allExistsList=new ArrayList<>();
        List<String> onlySourceExistsList=new ArrayList<>();
        List<String> onlyTargetExistsList=new ArrayList<>();
        for(int i=0;i<sourceSize;i++){
            rowMap=this.getRow(i);
            sourceColumnList.add(rowMap.get(compareColumn));
        }
        for (int i=0;i<targetSize;i++){
            rowMap=excelObject.getRow(i);
            targetColumnList.add(rowMap.get(compareColumn));
        }
        allExistsList.clear();
        allExistsList.addAll(sourceColumnList);
        allExistsList.retainAll(targetColumnList);
        //两表中都存在的数据
        this.addColumn(ExcelCostant.EXCEL_COMPAIR_ALL_EXISTS,allExistsList);
        onlySourceExistsList.clear();
        onlySourceExistsList.addAll(sourceColumnList);
        onlySourceExistsList.removeAll(targetColumnList);
        //仅源表中存在的数据
        this.addColumn(ExcelCostant.EXCEL_COMPAIR_ONLY_SOURCE_EXISTS,onlySourceExistsList);
        onlyTargetExistsList.clear();
        onlyTargetExistsList.addAll(targetColumnList);
        onlyTargetExistsList.removeAll(sourceColumnList);
        //仅目标表中存在的数据
        this.addColumn(ExcelCostant.EXCEL_COMPAIR_ONLY_TARGET_EXISTS,onlyTargetExistsList);
    }

    public List<String> compareOutPutAllExists(ExcelObject excelObject,String compareColumn,String outPutColumns){
        List<String> columnListSource=new ArrayList<>();
        List<String> columnListTarget=new ArrayList<>();
        String[] columnArray=outPutColumns.split(",");
        int sourceSize=this.getExcelList().size();
        Map<String,String> rowMap;
        String key;
        for (int i=0;i<columnArray.length;i++){
            key=columnArray[i];

        }
        for(int i=0;i<sourceSize;i++){
            rowMap=this.getExcelList().get(i);
            columnListSource.add(rowMap.get(compareColumn));
        }


        return null;
    }

    public void print(){
        int columnSize=this.getColumnSize();
        int rowSize=this.getRowSize();
        StringBuffer nameBuffer=new StringBuffer("");
        StringBuffer rowBuffer=new StringBuffer("");
        for (int colNum=0;colNum<columnSize;colNum++){
            nameBuffer.append(this.getNameList().get(colNum)+" | ");
        }
        System.out.println(nameBuffer+"\n");
        for(int rowNum=0;rowNum<rowSize;rowNum++){
            for (int colNum=0;colNum<columnSize;colNum++){
                rowBuffer.append(this.getCell(rowNum,colNum)+" | ");
            }
            System.out.println(rowBuffer);
            rowBuffer.delete(0,rowBuffer.length());
        }
    }
}
