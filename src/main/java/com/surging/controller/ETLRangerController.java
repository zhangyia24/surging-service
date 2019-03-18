package com.surging.controller;

import com.surging.entity.Result;
import com.surging.entity.SysInfo;
import com.surging.service.ETLRangerService;
import com.surging.service.SysInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/12.
 */
@RestController
@RequestMapping(value = "/etlRanger")
public class ETLRangerController {
    @Autowired
    private ETLRangerService etlRangerService;
    @Autowired
    private SysInfoService sysInfoService;

    @RequestMapping(value = "/selectSourceSystemRanger")
    public String selectSourceSystemRanger(Model model) throws Exception {
        List<SysInfo> sysInfos=sysInfoService.selectAllFromSysInfo();
        model.addAttribute("sysInfos",sysInfos);
        return "/test2";
    }
    @RequestMapping(value = "/excelAddSourceSystemRanger")
    public String excelAddSourceSystemRanger() {
        try {
            etlRangerService.excelAddSourceSystemRanger();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok("初始化源系统信息成功!");
    }
    @RequestMapping(value = "/excelAddSourceObjectRanger")
    public String tableRanger()  {
        try {
            etlRangerService.excelAddSourceTableRanger();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok("初始化表信息成功!");
    }
}
