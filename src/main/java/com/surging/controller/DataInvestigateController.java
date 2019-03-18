package com.surging.controller;

import com.alibaba.fastjson.JSONObject;
import com.surging.entity.Result;
import com.surging.entity.SourceObjectInfo;
import com.surging.entity.SourceObjectList;
import com.surging.service.IDataInvestigateService;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/12.
 */
@RestController
@RequestMapping(value = "/dataInvestigate")
public class DataInvestigateController {
    @Autowired
    IDataInvestigateService dataInvestigateService;

    @PostMapping(value = "/getObjectownerAndTypeAndUniqueName")
    public String getObjectownerAndTypeAndUniqueName(@RequestBody String tableList){
        JSONObject jsonObject = JSONObject.parseObject(tableList);
        String tableInfos=jsonObject.getString("tableList");
        List<SourceObjectInfo> sourceObjectInfos=JSONObject.parseArray(tableInfos,SourceObjectInfo.class);
        if(sourceObjectInfos !=null){
            sourceObjectInfos=dataInvestigateService.getObjectownerAndTypeAndUniqueName(sourceObjectInfos);
        }
        dataInvestigateService.getColumnInfo(sourceObjectInfos);
        return Result.ok(JSONObject.toJSON(sourceObjectInfos));
    }
    @PostMapping(value = "/getDataVolume")
    public String getDataVolume(@RequestBody String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        String tableInfos=jsonObject.getString("tableInfos");
        List<SourceObjectInfo> sourceObjectInfos=JSONObject.parseArray(tableInfos,SourceObjectInfo.class);
        if(sourceObjectInfos !=null){
            sourceObjectInfos=dataInvestigateService.getDataVolume(sourceObjectInfos);
        }
        return Result.ok(JSONObject.toJSON(sourceObjectInfos));
    }

}
