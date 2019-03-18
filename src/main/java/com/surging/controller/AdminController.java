package com.surging.controller;

import com.surging.entity.Administrator;
import com.surging.entity.Result;
import com.surging.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/surgingAdmin")
public class AdminController {
    @Autowired
    AdministratorService administratorService;

    @RequestMapping("/index")
    public String index(){
        return "/admin/index";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "aaaa";
    }


    @PostMapping("/login")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password){
        Map<String,String> adminMap=new HashMap<String,String>();
        adminMap.put("username",username);
        adminMap.put("password",password);
        Administrator administrator=administratorService.selectByAdminMap(adminMap);
        if (administrator == null){
            return Result.error("登录失败");
        }else {
            return Result.ok("登录成功");
        }

    }
    @RequestMapping("/logintest")
    public String logintest(){
        return "/public/common";
    }
}