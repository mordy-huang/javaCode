package com.hdljava.skycat.service.Impl;

import com.hdljava.skycat.mapper.OrderItemMapper;
import com.hdljava.skycat.pojo.Order;
import com.hdljava.skycat.pojo.OrderItem;
import com.hdljava.skycat.pojo.OrderItemExample;
import com.hdljava.skycat.pojo.Product;
import com.hdljava.skycat.service.OrderItemService;
import com.hdljava.skycat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;
    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.insert( orderItem );
    }

    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey( id );
    }

    @Override
    public void update(OrderItem orderItem) {
           orderItemMapper.updateByPrimaryKeySelective( orderItem );
    }

    @Override
    public OrderItem get(int id) {
        OrderItem orderItem = orderItemMapper.selectByPrimaryKey( id );
        setProduct( orderItem );
        return  orderItem;
    }

    @Override
    public List<OrderItem> list() {
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause( "id asc" );
        List<OrderItem> orderItems = orderItemMapper.selectByExample( example );
        return orderItems;
    }

    @Override
    public void finOrder(List<Order> orders) {
        for (Order order:orders) {
             finOrder(order);
        }
    }

    /**
     * 根据订单的id查出所有订单项
     * @param order
     */
    @Override
    public void finOrder(Order order) {
        float orderMoney=0;//订单总金额
        int orderNumber = 0;//订单数量
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOidEqualTo( order.getId() );
        example.setOrderByClause( "id asc" );
        List<OrderItem> orderItems = orderItemMapper.selectByExample( example );
        SetProduct(orderItems);
        for (OrderItem orderItem:orderItems){
            orderMoney+= orderItem.getNumber()*orderItem.getProduct().getPromotePrice().floatValue();
            orderNumber+= orderItem.getNumber();
        }
        order.setOrderItems( orderItems );
        order.setOrderMoney( orderMoney );
        order.setOrderNumber( orderNumber );
    }



    private void SetProduct(List<OrderItem> orderItems) {
        for (OrderItem orderItem:orderItems) {
            //根据订单项的pid得到订单类-> 给订单项添加订单类的信息(实现一对多查询)
            orderItem.setProduct(  productService.get( orderItem.getPid()));
        }
    }

    /**
     * 计算该商品一共被购买多少
     * @param pid
     * @return
     */
    @Override
    public int getSaleCount(int pid) {
        int number=0;
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andPidEqualTo( pid );
        List<OrderItem> orderItems = orderItemMapper.selectByExample( example );
        for (OrderItem orderItem:orderItems) {
           number +=orderItem.getNumber();
        }
        return number;
    }

    @Override
    public List<OrderItem> listByUser(int uid) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andUidEqualTo( uid );
        List<OrderItem> orderItems = orderItemMapper.selectByExample( example );
        setProduct( orderItems );
        return orderItems;
    }

    public void setProduct(List<OrderItem> orderItems){
        for (OrderItem orderItem: orderItems) {
            setProduct( orderItem );
        }
    }
   private void setProduct(OrderItem orderItem){
       Product product = productService.get( orderItem.getPid() );
       orderItem.setProduct( product );
   }

}
