package com.surging.controller;

import com.surging.service.TableStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/10/22.
 */
@Controller
@RequestMapping(value = "/table")
public class TableStructureController {
    String source_schema;
    String target_schema;
    String source_table_name;
    String target_table_name;
    String ddl_sql;
    String column;
    @Autowired
    TableStructureService tableStructureService;

    @RequestMapping(value = "/listTableColumn",method = RequestMethod.GET)
    @ResponseBody
    public String listTableColumn(@RequestParam(value = "tableName")String tableName){
        List<Map> list=tableStructureService.listTableColumn(tableName);
        source_schema=(String)list.get(0).get("TABLE_SCHEMA");
        target_schema=(String)list.get(0).get("TABLE_SCHEMA");
        source_table_name=(String)list.get(0).get("TABLE_NAME");
        target_table_name=(String)list.get(0).get("TABLE_NAME");
        ddl_sql="create table "+target_schema+"."+target_table_name+"(";
        StringBuffer stringBuffer=new StringBuffer(ddl_sql);
        for(int i=0;i<list.size();i++){
            column=list.get(i).get("COLUMN_NAME")+" "+list.get(i).get("COLUMN_TYPE")+",\n";
            stringBuffer.append(column);
        }
        stringBuffer.append(");\n");
        System.out.println(stringBuffer);
        return "aaa";
    }
}
