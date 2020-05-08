package com.hdljava.skycat.service;

import com.hdljava.skycat.pojo.Order;
import com.hdljava.skycat.pojo.OrderItem;
import com.hdljava.skycat.pojo.attribute;

import java.util.List;

public interface OrderService {
    String waitPay = "waitPay";//等待付款
    String waitDelivery = "waitDelivery";//等待派递
    String waitConfirm = "waitConfirm";//等待确认
    String waitReview="waitReview"; //等待评价
    String finish="finish";
    String delete="delete";

    /**
     * 查询所有订单
     * @return
     */
    List<Order> List();
    /**
     * 增加
     * @param order
     */
    void add(Order order);
    /**
     *
     * @param id
     */
    void delete(int id);

    /**
     * 得到订单属性的信息
     * @return
     */
    Order get(int id);

    void update(Order order);

    float add(Order order, List<OrderItem> orderItems);

    /**
     *
     * @param uid 用户id
     * @param excludeStatus 排除某种状态的订单
     * @return
     */
    List<Order> list(int uid,String excludeStatus);

}
