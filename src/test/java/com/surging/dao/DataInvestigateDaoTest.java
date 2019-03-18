package com.surging.dao;

import com.surging.entity.SourceObjectInfo;
import com.surging.entity.SysInfo;
import com.surging.tools.JdbcTool;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by zhangdongmao on 2019/3/9.
 */
public class DataInvestigateDaoTest {
    @Test
    public void getObjectownerAndType(SysInfo sysInfo, SourceObjectInfo sourceObjectInfo){
        Connection conn = null;
        String jdbcUrl;
        String dbUserName;
        String dbPassword;
        String databaseType;
        String getTableownerSql="";
        jdbcUrl=sysInfo.getJdbcUrl();
        dbUserName=sysInfo.getDbUsername();
        dbPassword=sysInfo.getDbPassword();
        databaseType=sysInfo.getDatabaseType();
        switch (databaseType){
            case "mysql":
                getTableownerSql="select table_schema,table_type from information_schema.tables where table_name=?";
                break;
            case "orcale":
                getTableownerSql="select owner,object_type from all_ojects where object_name=?";
                break;
        }
        try {
            conn= JdbcTool.getConnection(jdbcUrl,dbUserName,dbPassword,databaseType);
            PreparedStatement ps = conn.prepareStatement(getTableownerSql);
            ps.setString(1, sourceObjectInfo.getObjectName());
            ResultSet rset =ps.executeQuery();
            while (rset.next()) {
                sourceObjectInfo.setOwner(rset.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}