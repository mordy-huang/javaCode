package com.hdljava.skycat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
@Controller
public class PageController {
    @RequestMapping("/RegisterPage")
    public String registerPage(){
        return("fore/register");
    }

    @RequestMapping("/RegisterSuccessPage")
    public String registerSuccess(){
        return("fore/successPage");
    }

    @RequestMapping("/LoginPage")
    public String loginSuccess(){
        return("fore/login");
    }
    @RequestMapping("foreAlipay")
    public String alipay(){
        return "fore/aliPay";
    }

}
