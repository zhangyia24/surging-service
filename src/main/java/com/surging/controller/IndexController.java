package com.surging.controller;

import com.surging.entity.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/21.
 */
@EnableAutoConfiguration
@Controller
@RequestMapping("/user")
public class IndexController {
    @RequestMapping("/list")
    public String  listUser(Model model) {
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i <10; i++) {
            userList.add(new User(i,"张三"));

        }

        model.addAttribute("users", userList);
        return "/user/list";
    }
    @RequestMapping("/list2")
    public String userList2(Model model) throws Exception {
        model.addAttribute("hello","Hello, Spring Boot!!!");
        return "/user/list2";
    }
}