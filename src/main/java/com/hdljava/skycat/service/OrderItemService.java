package com.hdljava.skycat.service;

import com.hdljava.skycat.pojo.Order;
import com.hdljava.skycat.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    void add(OrderItem orderItem);
    void delete(int id);
    void update(OrderItem orderItem);
    OrderItem get(int id);
    List<OrderItem> list();

    void finOrder(List<Order> orders);
    void finOrder(Order order);

    int getSaleCount(int pid);

    List<OrderItem> listByUser(int uid);

}
