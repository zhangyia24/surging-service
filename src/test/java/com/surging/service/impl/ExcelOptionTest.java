package com.surging.service.impl;

import com.surging.SurgingApplication;
import com.surging.entity.*;
import com.surging.repository.OdsObjectMetadataRepository;
import com.surging.repository.ObjectStructureRepository;
import com.surging.repository.SourceObjectInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;

import static com.surging.poi.ReadExcelTool.readExcel;

/**
 * Created by zhangdongmao on 2019/1/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SurgingApplication.class)
@WebAppConfiguration
public class ExcelOptionTest {
    @Autowired
    SourceObjectInfoRepository sourceObjectInfoRepository;
    @Autowired
    OdsObjectMetadataRepository odsObjectMetadataRepository;
    @Autowired
    ObjectStructureRepository objectStructureRepository;
    @Test
    public void insertSourceObjectInfo() throws Exception {
        String fileName = "上线表.xlsx";
        int startRow = 1;
        int sheetId = 1;
        ExcelObject excelObject = readExcel(fileName, sheetId, startRow);
        int rowSize = excelObject.getRowSize();
        SourceObjectInfo sourceObjectInfo = new SourceObjectInfo();
        String sysAbbreviation;
        String owner;
        String dbEnv = "生产";
        String objectName;
        //增量字段	分区字段	切割字段	抽取策略
        for (int i = 0; i < rowSize; i++) {
            Map<String, String> rowMap = excelObject.getRow(i);
            sysAbbreviation = rowMap.get("源系统");
            objectName = rowMap.get("源表名");
            owner = rowMap.get("schema");
            if (sysAbbreviation.length() != 0) {
                sourceObjectInfo.setSysAbbreviation(sysAbbreviation);
            }
            if (objectName.length() != 0) {
                sourceObjectInfo.setObjectName(objectName);
            }
            sourceObjectInfo.setOwner(owner);
            sourceObjectInfo.setDbEnv(dbEnv);
            sourceObjectInfoRepository.insertByObject(sourceObjectInfo);
        }
    }
    @Test
    public void insertOdsObjectMetadata() throws Exception {
        String fileName = "1111.xlsx";
        int startRow = 1;
        int sheetId = 0;
        ExcelObject excelObject = readExcel(fileName, sheetId, startRow);
        int rowSize = excelObject.getRowSize();
        OdsObjectMetadata odsObjectMetadata = new OdsObjectMetadata();
        String databaseName="sdata_full";
        String uniqueName;
        String extract_type;
        String dbEnv = "生产";
        //增量字段	分区字段	切割字段	抽取策略
        for (int i = 0; i < rowSize; i++) {
            Map<String, String> rowMap = excelObject.getRow(i);
            uniqueName = rowMap.get("目标表英文名");
            extract_type = rowMap.get("抽取方式");
            odsObjectMetadata.setDatabaseName(databaseName);
            odsObjectMetadata.setUniqueName(uniqueName);
            odsObjectMetadata.setExtractType(extract_type);
            odsObjectMetadataRepository.insertByObject(odsObjectMetadata);
        }
    }
    @Test
    public void insertScriptStore() throws Exception {
        List<ObjectStructure> coordinates= objectStructureRepository.selectCoordinateFromPro();
        ScriptStore scriptStore=new ScriptStore();
        String uniqueName;
        String colName;
        String colType;
        String dbEnv = "生产";
        String objectName;
        //增量字段	分区字段	切割字段	抽取策略
        for (int i = 0; i < coordinates.size(); i++) {
            uniqueName=coordinates.get(i).getUniqueName();
            List<ObjectStructure> objectMetadataList= objectStructureRepository.selectAllByUniNameFromPro(uniqueName);
            StringBuffer hiveDdl = new StringBuffer("CREATE TABLE "+coordinates.get(i).getDatabaseName()+"."+uniqueName+"{\n");
            for (ObjectStructure objectMetadata:objectMetadataList){
                colName=objectMetadata.getColName();
                colType=objectMetadata.getColType();
                hiveDdl.append(colName+"\t"+colType+"\t"+"comment ''"+"\n");
            }
            System.out.println(hiveDdl);
            hiveDdl.setLength(0);
        }
    }
}
