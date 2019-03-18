package com.surging.sqoop;

/**
 * Created by zhangdongmao on 2019/2/16.
 */
import org.apache.hadoop.conf.Configuration;
import org.apache.sqoop.Sqoop;
import org.apache.sqoop.tool.SqoopTool;
import org.apache.sqoop.util.OptionsFileUtil;


public class SqoopTest {

    private static void importDataFromMysql() throws Exception {
//        String[] args = new String[] {
//                "--connect","jdbc:mysql://localhost:3306/test",
//                "--driver","com.mysql.jdbc.Driver",
//                "-username","root",
//                "-password","root",
//                "--table","tbl_user",
//                "-m","1",
//                "--target-dir","/user/java/user"
//        };
//
//        String[] expandArguments = OptionsFileUtil.expandArguments(args);
//
//        SqoopTool tool = SqoopTool.getTool("import");
////
//        Configuration conf = new Configuration();
//        conf.set("fs.defaultFS", "hdfs://192.168.0.95:9000");//设置HDFS服务地址
//        Configuration loadPlugins = SqoopTool.loadPlugins(conf);
////
//        Sqoop sqoop = new Sqoop((com.cloudera.sqoop.tool.SqoopTool) tool, loadPlugins);
//        return Sqoop.runSqoop(sqoop, expandArguments);
////        return 11;


        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://192.168.0.99:8020/");//设置HDFS服务地址
        String[] arg = new String[] {"--connect","jdbc:mysql://localhost:3306/test",
                "--username","root",
                "--password","root",
                "--table","tbl_user",
                "--m","1",
                "--target-dir","/user/java/user",
                "--input-fields-terminated-by","\t"
        };
        String[] expandArguments = OptionsFileUtil.expandArguments(arg);
        SqoopTool tool = SqoopTool.getTool("import");
        Configuration loadPlugins = SqoopTool.loadPlugins(conf);
        Sqoop sqoop = new Sqoop((com.cloudera.sqoop.tool.SqoopTool) tool, loadPlugins);

        int res = Sqoop.runSqoop(sqoop, expandArguments);
        if (res == 0) {
            System.out.println ("成功");
        }else {
            System.out.println("失败");
        }
    }

    public static void main(String[] args) throws Exception {
        importDataFromMysql();
    }

}
