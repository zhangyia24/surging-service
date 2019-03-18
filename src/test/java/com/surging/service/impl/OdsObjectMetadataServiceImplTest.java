package com.surging.service.impl;

import com.surging.SurgingApplication;
import com.surging.entity.ExcelObject;
import com.surging.entity.ObjectStructure;
import com.surging.repository.ObjectStructureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

import static com.surging.poi.ReadExcelTool.readExcel;

/**
 * Created by zhangdongmao on 2019/1/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SurgingApplication.class)
@WebAppConfiguration
public class OdsObjectMetadataServiceImplTest {
    @Autowired
    ObjectStructureRepository objectStructureRepository;
    @Test
    public void insertByObject() throws Exception {
        String fileName="table_structure.xlsx";
        int startRow=1;
        int sheetId=0;
        ExcelObject excelObject=readExcel(fileName,sheetId,startRow);
        int rowSize=excelObject.getRowSize();
        ObjectStructure objectMetadata=new ObjectStructure();
        String sysAbbreviation;
        String colName;
        String colType;
        String objectName;
        for (int i=0;i<rowSize;i++){
            Map<String,String> rowMap= excelObject.getRow(i);
            sysAbbreviation=rowMap.get("源系统名").toLowerCase();
            objectName=rowMap.get("表名").toLowerCase();
            colName=rowMap.get("字段名").toLowerCase();
            colType=rowMap.get("字段类型").toLowerCase();
            if (sysAbbreviation.length()!=0){
                objectMetadata.setSysAbbreviation(sysAbbreviation);
            }
            if(objectName.length()!=0){
                objectMetadata.setObjectName(objectName);
            }
            objectMetadata.setUniqueName(objectMetadata.getSysAbbreviation()+"_"+objectMetadata.getObjectName());
            objectMetadata.setColName(colName);
            objectMetadata.setColType(colType);
            if(colName.equals("data_dt")){
                objectMetadata.setPartitionKey("Y");
            }else {
                objectMetadata.setPartitionKey("");
            }
//            System.out.println("源系统名:"+objectMetadata.getSysAbbreviation()+"表名:"+objectMetadata.getObjectName()+"字段名"+
//            objectMetadata.getColName()+"字段类型:"+objectMetadata.getColType()+
//            "分区:"+objectMetadata.getPartitionKey());
            objectStructureRepository.insertByObjectFromPro(objectMetadata);
        }
    }

}