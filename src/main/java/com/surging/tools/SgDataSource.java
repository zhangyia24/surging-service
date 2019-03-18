package com.surging.tools;

import com.surging.controller.QrtzController;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by zhangdongmao on 2019/3/9.
 */
@Component
public class SgDataSource implements DataSource {
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SgDataSource.class);
    private static Map<String,List<Connection>> connectionMap=new HashMap<>();

    public SgDataSource(){
        init();
        for(String key : connectionMap.keySet()){
            LOGGER.info(key+"系统连接个数:"+String.valueOf(connectionMap.get(key).size()));
        }
    }
    public void init() {
        Connection conn = null;
        //获取当前项目路径
        String root = System.getProperty("user.dir");
        String FileName="sgDataSource.properties";
        String filePath = root+File.separator+"src/main/resources"+File.separator+FileName;
        Set<String> sysAbbreviationSet=new HashSet<>();
        String line;
        String driver;
        String username;
        String password;
        String url;
        int initialSize;     //初始化连接数
        try{
            FileReader reader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(reader);
            while ((line = br.readLine()) != null){
                sysAbbreviationSet.add(line.split("\\.")[0]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            //此处通过外部的properties文件来获取数据库连接信息。
            InputStream in =Properties.class.getResourceAsStream("/sgDataSource.properties");
            Properties pro = new Properties();
            pro.load(in);
            for(String sysAbbreviation:sysAbbreviationSet){
                List<Connection> connectionList = new LinkedList<>();
                driver = pro.getProperty(sysAbbreviation+".driver");
                url = pro.getProperty(sysAbbreviation+".url");
                username = pro.getProperty(sysAbbreviation+".username");
                password = pro.getProperty(sysAbbreviation+".password");
                initialSize =Integer.parseInt(pro.getProperty(sysAbbreviation+".initialSize"));
                Class.forName(driver);

                //此处设置每个数据库连接的个数
                for(int i=0;i<initialSize;i++){
                    conn = DriverManager.getConnection(url,username,password);
                    connectionList.add(conn);
                }
                connectionMap.put(sysAbbreviation,connectionList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Connection getConnection(String sysAbbreviation) throws SQLException {
        Connection conn = null;
        if(connectionMap.get(sysAbbreviation) == null || connectionMap.get(sysAbbreviation).size() <= 0){
            Connection connection = newConnection(sysAbbreviation);
            connectionMap.get(sysAbbreviation).add(connection);
        }
        conn = connectionMap.get(sysAbbreviation).remove(0);
        return conn;
    }
    public Connection newConnection(String sysAbbreviation){
        Connection conn = null;
        String driver;
        String username;
        String password;
        String url;
        try{
            //此处通过外部的properties文件来获取数据库连接信息。
            InputStream in =Properties.class.getResourceAsStream("/sgDataSource.properties");
            Properties pro = new Properties();
            pro.load(in);
                List<Connection> connectionList = new LinkedList<>();
                driver = pro.getProperty(sysAbbreviation+".driver");
                url = pro.getProperty(sysAbbreviation+".url");
                username = pro.getProperty(sysAbbreviation+".username");
                password = pro.getProperty(sysAbbreviation+".password");
                Class.forName(driver);
                conn = DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    public void backConnection(String sysAbbreviation,Connection conn){
        connectionMap.get(sysAbbreviation).add(conn);
    }
    public static void free(ResultSet rst, PreparedStatement pst) {
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
            }
        }

    }
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
