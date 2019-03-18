package com.surging.tools;

import java.sql.*;

/**
 * Created by zhangdongmao on 2019/1/15.
 */
public class JdbcTool {

    private static String oracle_url = "jdbc:oracle:thin:@localhost:1521:ORCL";
    private static String url = "jdbc:mysql://localhost:3306/my?characterEncoding=UTF-8";
    private static String user = "system";
    private static String passwd = "root";

    private JdbcTool() {
    }

    public static Connection getConnection(String jdbcUrl,String username,String password,String databaseType) throws SQLException {
        String dbDriver="";
        switch(databaseType){
            case "mysql":
                dbDriver="com.mysql.jdbc.Driver";
                break;
            case "oracle":
                dbDriver="oracle.jdbc.OracleDriver";
                break;
            case "sqlserver":
                dbDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
                break;
        }
        try {
            Class.forName(dbDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection con
                = DriverManager.getConnection(jdbcUrl, username, password);
        return con;
    }


    public static void free(ResultSet rst, PreparedStatement pst, Connection con) {
        try {
            if (rst != null) {
                rst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

