package com.surging.controller;


import com.alibaba.fastjson.JSONObject;
import com.surging.entity.Result;
import com.surging.service.ISourceObjectListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangdongmao on 2019/3/12.
 */
@RestController
@RequestMapping(value = "/sourceObjectList")
public class SourceObjectListController {
    @Autowired
    ISourceObjectListService sourceObjectListService;
    @PostMapping("/getsourceObjectList")
    public String getSourceObjectList(){
        return Result.ok(JSONObject.toJSON(sourceObjectListService.selectAll()));
    }
}
