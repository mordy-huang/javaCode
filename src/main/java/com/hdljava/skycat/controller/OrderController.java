package com.hdljava.skycat.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hdljava.skycat.pojo.Order;
import com.hdljava.skycat.pojo.OrderItem;
import com.hdljava.skycat.pojo.Product;
import com.hdljava.skycat.service.OrderItemService;
import com.hdljava.skycat.service.OrderService;
import com.hdljava.skycat.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@RequestMapping("")
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @RequestMapping("admin_Order_List")
    public String  list(Model model, Page page){
        PageHelper.offsetPage( page.getStart(),page.getCount() );
        List<Order> orders = orderService.List();
        orderItemService.finOrder( orders );
        int total = (int)new PageInfo<>( orders ).getTotal();
        page.setTotal( total );
        model.addAttribute( "orders",orders);
        model.addAttribute( "page",page );
        return "admin/listOrder";
    }
    @RequestMapping("admin_Order_Delivery")
    public String delivery(int id,int page){
        Order order = orderService.get( id );
        order.setDeliveryDate( new Date() );
        order.setStatus( OrderService.waitConfirm);
        orderService.update( order );
        System.out.println(page);
        return "redirect:admin_Order_List?start="+page;
    }
}
