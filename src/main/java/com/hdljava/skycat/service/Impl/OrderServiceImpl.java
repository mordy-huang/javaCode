package com.hdljava.skycat.service.Impl;

import com.hdljava.skycat.mapper.OrderMapper;
import com.hdljava.skycat.pojo.Order;
import com.hdljava.skycat.pojo.OrderExample;
import com.hdljava.skycat.pojo.OrderItem;
import com.hdljava.skycat.pojo.User;
import com.hdljava.skycat.service.OrderItemService;
import com.hdljava.skycat.service.OrderService;
import com.hdljava.skycat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;
    @Override
    public List<Order> List() {
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause( "id asc" );
        List<Order> orders = orderMapper.selectByExample( orderExample );
        //添加用户订单的用户信息
        setUser( orders );
        return orders;
    }

    private void setUser(List<Order > orders) {
        for (Order order: orders) {
            order.setUser( userService.get( order.getUid() ) );
        }
    }

    @Override
    public void add(Order order) {
         orderMapper.insert( order );
    }

    @Override
    public void delete(int id) {
         orderMapper.deleteByPrimaryKey( id );
    }

    @Override
    public Order get(int id) {
       return  orderMapper.selectByPrimaryKey( id );
    }

    @Override
    public void update(Order order) {
      orderMapper.updateByPrimaryKeySelective( order );
    }
    //,生产一个订单 更新订单项 并结算金额
    //添加事务管理
    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public float add(Order order, List<OrderItem> orderItems){
        float total=0;
        add( order );
        if (false) throw new RuntimeException();
        for (OrderItem orderItem: orderItems) {
            //设置一个订单项
            orderItem.setOid(order.getId());
            orderItemService.update(orderItem);
            total+=orderItem.getNumber()*orderItem.getProduct().getPromotePrice().floatValue();
        }
        return total;
    }

    @Override
    public List<Order> list(int uid, String excludeStatus) {
        OrderExample example =new OrderExample();
        example.createCriteria().andStatusNotEqualTo( excludeStatus ).andUidEqualTo( uid );
        example.setOrderByClause( "id asc" );
        return orderMapper.selectByExample( example );
    }


}
