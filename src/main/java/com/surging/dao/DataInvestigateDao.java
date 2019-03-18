package com.surging.dao;

import com.surging.entity.SourceObjectInfo;
import com.surging.entity.SourceObjectList;
import com.surging.entity.SourceStructure;
import com.surging.entity.SysInfo;
import com.surging.tools.Constant;
import com.surging.tools.JdbcTool;
import com.surging.tools.SgDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


/**
 * Created by zhangdongmao on 2019/3/9.
 */
@Repository
public class DataInvestigateDao {
    @Autowired
    SgDataSource sgDataSource;
    private static Map<String,Map<String,String>>mapField=new HashMap<String,Map<String,String>>();// 存储每个表的所有属性和数据类型
    /**
     * @Author: zhangdongmao
     * @Date: 2019/3/11
     * @Description: 获取表的owner和类型
     * @Param: * @param null 1
     * @return:
     */
    public List<SourceObjectInfo> getObjectownerAndTypeAndUniqueName(SourceObjectInfo sourceObjectInfo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rset = null;
        String owner = "";
        String objectType = "";
        List<SourceObjectInfo> sourceObjectInfos=new ArrayList<>();
        String getTableownerSql = "";
        String uniqueName = "";
        String databaseType = sourceObjectInfo.getDatabaseType();
        String sysAbbreviation = sourceObjectInfo.getSysAbbreviation();
        SimpleDateFormat df=null;
        switch (databaseType) {
            case "mysql":
                getTableownerSql = "select table_schema,table_type from information_schema.tables where table_name=?";
                break;
            case "oracle":
                getTableownerSql = "select owner,object_type from all_objects where object_name=?";
                break;
        }
        try {
            conn = sgDataSource.getConnection(sysAbbreviation);
            ps = conn.prepareStatement(getTableownerSql);
            ps.setString(1, sourceObjectInfo.getObjectName());
            rset = ps.executeQuery();
            while (rset.next()) {
                SourceObjectInfo sourceObjectInfoRest=new SourceObjectInfo();
                owner=rset.getString(1);
                objectType=rset.getString(2);
                uniqueName = sourceObjectInfo.getSysAbbreviation().toLowerCase() + "_" + owner.toLowerCase() + "_" + sourceObjectInfo.getObjectName().toLowerCase();
                sourceObjectInfoRest.setDatabaseType(databaseType);
                sourceObjectInfoRest.setSysAbbreviation(sysAbbreviation);
                sourceObjectInfoRest.setObjectName(sourceObjectInfo.getObjectName());
                sourceObjectInfoRest.setOwner(owner);
                sourceObjectInfoRest.setObjectType(objectType);
                sourceObjectInfoRest.setUniqueName(uniqueName);
                //设置等级为owner级别
                sourceObjectInfoRest.setLevel(Constant.OWNER_LEVEL);
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sourceObjectInfoRest.setCreateDt(df.format(new Date()));
                sourceObjectInfos.add(sourceObjectInfoRest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sgDataSource.free(rset, ps);
            sgDataSource.backConnection(sysAbbreviation, conn);
        }
        return sourceObjectInfos;
    }

    public SourceObjectInfo getDataVolume(SourceObjectInfo sourceObjectInfo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rset = null;
        String owner = sourceObjectInfo.getOwner();
        String objectName = sourceObjectInfo.getObjectName();
        String dataVolume = "";
        String getDataVolumeSql = "";
        String sysAbbreviation = sourceObjectInfo.getSysAbbreviation();
        String[] ownerArr = owner.split(",");
        for (int i = 0; i < ownerArr.length; i++) {
            String objectCoordinate = ownerArr[i] + "." + objectName;
            getDataVolumeSql = "select count(1) from " + objectCoordinate;
            try {
                conn = sgDataSource.getConnection(sysAbbreviation);
                ps = conn.prepareStatement(getDataVolumeSql);
                rset = ps.executeQuery();
                while (rset.next()) {
                    dataVolume=rset.getString(1);
                }
                sourceObjectInfo.setDataVolume(dataVolume);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                sgDataSource.free(rset, ps);
                sgDataSource.backConnection(sysAbbreviation, conn);
            }
        }
        return sourceObjectInfo;
    }

    /**
     * @Author: zhangdongmao
     * @Date: 2019/3/11
     * @Description: 获取表的主键和索引信息
     * @Param: * @param null 1
     * @return:
     */
    public SourceObjectInfo getIndexAndPrimary(SourceObjectInfo sourceObjectInfo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rset = null;
        String owner = sourceObjectInfo.getOwner();
        String objectName = sourceObjectInfo.getObjectName();
        String dataVolume = "";
        Map<String,List<String>>mapUnionKey=new HashMap<>();
        Map<String,String>mapOnlyKey=new HashMap<String,String>();
        String getIndexAndPrimarySql = "";
        String sysAbbreviation = sourceObjectInfo.getSysAbbreviation();
        String databaseType = sourceObjectInfo.getDatabaseType();
        try{
            conn = sgDataSource.getConnection(sysAbbreviation);
            DatabaseMetaData metaDB=conn.getMetaData();
            ResultSet rsKey=metaDB.getIndexInfo(owner,null, objectName,true,false);
            //            ResultSet rsKey=metaDB.getPrimaryKeys(owner, null, objectName);//获取一张表的主键信息
            List keyList=new ArrayList();
            while(rsKey.next()){
                System.out.println("字段名"+rsKey.getObject("COLUMN_NAME"));
                System.out.println("可为空"+rsKey.wasNull());
                System.out.println("是否唯一"+rsKey.getString("NON_UNIQUE"));
                System.out.println("索引目录"+rsKey.getString("INDEX_QUALIFIER"));
                System.out.println("类型"+rsKey.getString("TYPE"));
                System.out.println("序号"+rsKey.getString("ORDINAL_POSITION"));
                System.out.println("-------------------------------------------------------------");
                keyList.add(rsKey.getObject("COLUMN_NAME"));//存储主键的列名
            }

            if(keyList.size()>1){
                System.out.println(keyList.get(0));
                mapUnionKey.put(objectName, keyList);//联合主键
            }else{
                System.out.println(keyList.get(0));
                mapOnlyKey.put(objectName, (String) keyList.get(0));//唯一主键
            }
            for(String key:mapUnionKey.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
                 String value = mapUnionKey.get(key).toString();//
                System.out.println("联合主键key:"+key+" vlaue:"+value);
            }
        }catch (Exception e){

        }
//        if (databaseType == "mysql") {
//            getIndexAndPrimarySql = "SELECT CONSTRAINT_NAME,COLUMN_NAME,ORDINAL_POSITION FROM INFORMATION_SCHEMA.`KEY_COLUMN_USAGE`  WHERE table_name=?  AND CONSTRAINT_SCHEMA=?";
//            for (int i = 0; i < ownerArr.length; i++) {
//                try {
//                    conn = sgDataSource.getConnection(sysAbbreviation);
//                    ps = conn.prepareStatement(getIndexAndPrimarySql);
//                    ps.setString(1, sourceObjectInfo.getObjectName());
//                    ps.setString(2, ownerArr[i]);
//                    rset = ps.executeQuery();
//                    while (rset.next()) {
//                        System.out.println(rset.getString("CONSTRAINT_NAME"));
//                        System.out.println(rset.getString("COLUMN_NAME"));
//                        System.out.println(rset.getInt("ORDINAL_POSITION"));
//                    }
//                    dataVolume = StringUtils.strip(dataVolumeList.toString(), "[]");
//                    sourceObjectInfo.setDataVolume(dataVolume);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                } finally {
//                    sgDataSource.free(rset, ps);
//                    sgDataSource.backConnection(sysAbbreviation, conn);
//                }
//            }
//        } else if (databaseType == "oracle") {
//            getIndexAndPrimarySql = "SELECT * FROM user_indexes WHERE table_name=? AND TABLE_OWNER=? AND uniqueness='UNIQUE'";
//            for (int i = 0; i < ownerArr.length; i++) {
//                try {
//                    conn = sgDataSource.getConnection(sysAbbreviation);
//                    ps = conn.prepareStatement(getIndexAndPrimarySql);
//                    ps.setString(1, objectName);
//                    ps.setString(2, ownerArr[i]);
//                    rset = ps.executeQuery();
//                    while (rset.next()) {
//                        dataVolumeList.add(rset.getInt(1) + "");
//                    }
//                    dataVolume = StringUtils.strip(dataVolumeList.toString(), "[]");
//                    sourceObjectInfo.setDataVolume(dataVolume);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                } finally {
//                    sgDataSource.free(rset, ps);
//                    sgDataSource.backConnection(sysAbbreviation, conn);
//                }
//            }
//        }

        return sourceObjectInfo;
    }

    /**
     * @Author: zhangdongmao
     * @Date: 2019/3/11
     * @Description: 获取表的字段信息
     * @Param: * @param null 1
     * @return:
     */
    public List<SourceStructure> getColumnInfo(SourceObjectInfo sourceObjectInfo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rset = null;
        String getColumnInfoSql="";
        List<SourceStructure> sourceStructures=new ArrayList<>();
        String owner = sourceObjectInfo.getOwner();
        String objectName = sourceObjectInfo.getObjectName();
        String sysAbbreviation = sourceObjectInfo.getSysAbbreviation();
        String databaseType=sourceObjectInfo.getDatabaseType();
            try {
                conn = sgDataSource.getConnection(sysAbbreviation);
                if(databaseType.equals("mysql")) {
                    getColumnInfoSql="select * from information_schema.columns where table_name =? and table_schema=?";
                    ps = conn.prepareStatement(getColumnInfoSql);
                    ps.setString(1, objectName);
                    ps.setString(2, owner);
                    rset = ps.executeQuery();
                    while(rset.next()){
                        SourceStructure sourceStructure=new SourceStructure();
                        sourceStructure.setUniqueName(sourceObjectInfo.getUniqueName());
                        sourceStructure.setColumnName(rset.getString("COLUMN_NAME"));
                        sourceStructure.setColumnType(rset.getString("COLUMN_TYPE"));
                        if(rset.getString("IS_NULLABLE").equals("YES")){
                            sourceStructure.setIsNullable("Y");
                        }else{
                            sourceStructure.setIsNullable("N");
                        }
                        sourceStructure.setComment(rset.getString("COLUMN_COMMENT"));
                        sourceStructures.add(sourceStructure);
                    }
                }else if(databaseType.equals("oracle")){
                    getColumnInfoSql="select COLUMN_NAME,DATA_TYPE,DATA_LENGTH,DATA_PRECISION,DATA_SCALE,NULLABLE from user_tab_columns where table_name=? order by COLUMN_ID";
                    ps = conn.prepareStatement(getColumnInfoSql);
                    ps.setString(1, objectName);
                    rset = ps.executeQuery();
                    while(rset.next()){
                        SourceStructure sourceStructure=new SourceStructure();
                        sourceStructure.setUniqueName(sourceObjectInfo.getUniqueName());
                        sourceStructure.setColumnName(rset.getString("COLUMN_NAME"));
                        sourceStructure.setColumnType(rset.getString("DATA_TYPE"));
                        sourceStructure.setDataLength(rset.getString("DATA_LENGTH"));
                        sourceStructure.setDataPrecision(rset.getString("DATA_PRECISION"));
                        sourceStructure.setDataScale(rset.getString("DATA_SCALE"));
                        sourceStructure.setIsNullable(rset.getString("NULLABLE"));
                        sourceStructures.add(sourceStructure);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                sgDataSource.free(rset, ps);
                sgDataSource.backConnection(sysAbbreviation, conn);
            }
        return sourceStructures;
    }
}
