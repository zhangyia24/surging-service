package com.surging.controller;

import com.surging.entity.SysInfo;
import com.surging.service.SysInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/13.
 */
@Controller
@RequestMapping(value = "/sysInfo")
public class SysInfoController {
    @Autowired
    private SysInfoService sysInfoService;

    @RequestMapping(value = "/selectAllFromSysInfo")
    public String selectAllFromSysInfo(Model model) throws Exception {
        List<SysInfo> sysInfos=sysInfoService.selectAllFromSysInfo();
        model.addAttribute("sysInfos",sysInfos);
        return "/test2";
    }

}
