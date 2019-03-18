package com.surging.service;

import com.surging.entity.ExcelObject;
import com.surging.entity.SourceObjectInfo;
import com.surging.entity.SourceObjectList;
import com.surging.entity.SysInfo;
import com.surging.repository.SourceObjectInfoRepository;
import com.surging.repository.SourceObjectListRepository;
import com.surging.repository.SysInfoRepository;
import com.surging.tools.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.surging.poi.ReadExcelTool.readExcel;

/**
 * Created by zhangdongmao on 2019/1/12.
 */
@Service
public class ETLRangerService {
    @Autowired
    SysInfoRepository sysInfoRepository;
    @Autowired
    SourceObjectInfoRepository sourceObjectInfoRepository;
    @Autowired
    SourceObjectListRepository sourceObjectListRepository;

    public void excelAddSourceSystemRanger() throws Exception {
        String fileName="ETL抽取范围new.xlsx";
        int startRow=1;
        int sheetId=0;
        ExcelObject excelObject=readExcel(fileName,sheetId,startRow);
        int rowSize=excelObject.getRowSize();
        SysInfo sysInfo=new SysInfo();
        for (int i=0;i<rowSize;i++){
            Map<String,String> rowMap= excelObject.getRow(i);
            String sysAbbreviation=rowMap.get("源系统英文缩写");
            String sysName=rowMap.get("源系统名");
            String sysType=rowMap.get("测试&生产");
            String sid=rowMap.get("SID");
            String ip=rowMap.get("IP");
            String port=rowMap.get("端口号");
            String databaseType=rowMap.get("数据库类型");
            String dbUsername=rowMap.get("用户名");
            String dbPassword=rowMap.get("密码");
            sysInfo.setSysAbbreviation(sysAbbreviation);
            sysInfo.setSysName(sysName);
            sysInfo.setSysType(sysType);
            sysInfo.setIp(ip);
            sysInfo.setPort(port);
            sysInfo.setSid(sid);
            sysInfo.setDatabaseType(databaseType);
            sysInfo.setDbUsername(dbUsername);
            sysInfo.setDbPassword(dbPassword);
            sysInfoRepository.insertByObject(sysInfo);
        }
    }

    public void excelAddSourceTableRanger() throws Exception {
        String fileName="ETL抽取范围new.xlsx";
        int startRow=1;
        int sheetId=1;
        ExcelObject excelObject=readExcel(fileName,sheetId,startRow);
        int rowSize=excelObject.getRowSize();
        SourceObjectInfo sourceObjectInfo=new SourceObjectInfo();
        for (int i=0;i<rowSize;i++){
            Map<String,String> rowMap= excelObject.getRow(i);
            String sysAbbreviation=rowMap.get("源系统英文缩写");
            String owner=rowMap.get("OWNER");
            String objectType=rowMap.get("对象类型");
            String databaseType=rowMap.get("数据库类型");
            String objectName="";
            if (databaseType.toLowerCase().equals("mysql")){
                objectName=rowMap.get("对象名").toLowerCase();
            }else if(databaseType.toLowerCase().equals("oracle")){
                objectName=rowMap.get("对象名").toUpperCase();
            }
            String ownerSplit="_"+owner;
            String objectNameSplit="_"+objectName.toLowerCase();
            if(owner.length()==0){
                ownerSplit="";
            }
            String uniqueName=sysAbbreviation+ownerSplit+objectNameSplit;
            // 获取当前系统时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sourceObjectInfo.setCreateDt(df.format(new Date()));
            sourceObjectInfo.setSysAbbreviation(sysAbbreviation);
            sourceObjectInfo.setObjectName(objectName);
            sourceObjectInfo.setOwner(owner);
            sourceObjectInfo.setObjectType(objectType);
            sourceObjectInfo.setDatabaseType(databaseType);
            sourceObjectInfo.setUniqueName(uniqueName);
            sourceObjectInfo.setLevel(Constant.TABLE_LEVEL);
            sourceObjectInfoRepository.insertByObject(sourceObjectInfo);
        }
    }

}
