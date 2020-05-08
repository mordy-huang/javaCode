package com.hdljava.skycat.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hdljava.skycat.pojo.Category;

import com.hdljava.skycat.pojo.User;
import com.hdljava.skycat.service.UserService;
import com.hdljava.skycat.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/admin_listUser_Controller")
    public String listUser(Page page, Model model){
        PageHelper.offsetPage( page.getStart(), page.getCount() );
        List<User> users = userService.List();
        int total = (int) new PageInfo<>( users ).getTotal();
        page.setTotal( total );
        model.addAttribute( "page", page );
        model.addAttribute( "users", users );
        return "admin/listUser";
    }
}
