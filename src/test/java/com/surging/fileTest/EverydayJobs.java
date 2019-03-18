package com.surging.fileTest;

import com.surging.entity.ExcelObject;
import com.surging.entity.SysInfo;

import java.io.*;
import java.util.Map;

import static com.surging.poi.ReadExcelTool.readExcel;

/**
 * Created by zhangdongmao on 2019/3/8.
 */
public class EverydayJobs {
    public static String line="";
    public static void main(String[] args) throws Exception {
        String pathname = "every_day_job.txt";
        String fileName="修改清单.xlsx";
        int startRow=1;
        int sheetId=0;
        ExcelObject excelObject=readExcel(fileName,sheetId,startRow);
        int rowSize=excelObject.getRowSize();
        for (int i=0;i<rowSize;i++){
            Map<String,String> rowMap= excelObject.getRow(i);
            String system=rowMap.get("源系统名");
            system=(" "+system+" ").toLowerCase();
            String table=rowMap.get("表名");
            table=(table+" ").toLowerCase();
            System.out.println(system);
            System.out.println(table);
            try (FileReader reader = new FileReader(pathname);
                 BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
            ) {
                //网友推荐更加简洁的写法
                while ((line = br.readLine()) != null) {
                    // 一次读入一行数据
                    if(line.toLowerCase().contains(system) && line.toLowerCase().contains(table)){
                        System.out.println(line);
                        String[] splited = line.split("\\s+");
                        splited[10]="\"\\\\001\"";
                        line=splited[0];
                        for (int j=1;j<splited.length;j++){
                            line=line+" "+splited[j];
                        }
                    }
                    System.out.println(line);
                    try {
                        File writeName = new File("everyday_jobs_new.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
                        writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
                        try (FileWriter writer = new FileWriter(writeName);
                             BufferedWriter out = new BufferedWriter(writer)
                        ) {
                            out.write(line+"\r\n"); // \r\n即为换行
                            out.flush(); // 把缓存区内容压入文件
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
