package com.surging.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.surging.entity.Result;
import com.surging.entity.SourceObjectInfo;
import com.surging.service.ISourceObjectInfoService;
import com.surging.service.impl.SourceObjectInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/3/7.
 */
@RestController
@RequestMapping(value = "/sourceObjectInfo")
public class SourceObjectInfoController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SourceObjectInfoController.class);
    @Autowired
    ISourceObjectInfoService sourceObjectInfoService;

    @PostMapping("/getSourceObjectListByLevel")
    public String selectAll(@RequestBody String data) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        int level=jsonObject.getInteger("level");
        List<SourceObjectInfo> sourceObjectInfos=sourceObjectInfoService.selectAllByLevel(level);
        if(sourceObjectInfos!=null){
            return Result.ok(JSON.toJSONString(sourceObjectInfos));
        }
        return Result.ok("列表为空");
    }
}
