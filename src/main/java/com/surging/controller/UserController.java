package com.surging.controller;

import com.surging.entity.User1;
import com.surging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Song on 2017/2/15.
 * User控制层
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        return "/user/index";
    }

    @RequestMapping(value = "/drop",method = RequestMethod.GET)
    @ResponseBody
    public String dropTable(){
        userService.dropTable();
        return "aaaa";
    }
    @RequestMapping(value = "/createTable",method = RequestMethod.GET)
    @ResponseBody
    public String createTable(){
        userService.createTable();
        return "aaaa";
    }
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    @ResponseBody
    public String insert(@RequestParam(value = "name")String name){
        User1 user1=new User1();
        user1.setName(name);
        userService.insert(user1);
        return "aaaa";
    }
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
    public String findAll(){
        List<User1> list = null;
        list=userService.findAll();
        System.out.println(list.get(0).getName());
        return "aaaa";
    }

    @RequestMapping(value = "/findByNameLike",method = RequestMethod.GET)
    @ResponseBody
    public String getTableStructure(@RequestParam(value = "name")String name){
        List<User1> list = null;
        list=userService.findByNameLike(name);
        if(list==null){
            return "null";
        }else{
            System.out.println(list.get(0).getName());
            return "aaaa";
        }

    }
}
