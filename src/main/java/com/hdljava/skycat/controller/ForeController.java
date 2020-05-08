package com.hdljava.skycat.controller;

import com.github.pagehelper.PageHelper;
import com.hdljava.skycat.pojo.*;
import com.hdljava.skycat.service.*;

import comparator.*;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    AttributeValueService attributeValueService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderService orderService;

    @RequestMapping("foreHome")
    public String home(Model model){
        List<Category> categories = categoryService.List();
        productService.fill( categories );
        productService.fillByRow( categories );
        model.addAttribute( "cs",categories );
       return "fore/home";
    }

    @RequestMapping("foreRegister")
    public String register(Model model, User user){
        //HtmlUtils.htmlEscape？ 诸如 <script>alert('papapa')</script> 这样的名称，会导致网页打开就弹出一个对话框。 那么在转义之后，就没有这个问题了。
        String name = HtmlUtils.htmlEscape( user.getName() );
        user.setName( name );
        //判断用户名是否存在
        if (userService.isExist(name)){
          String msg="用户名存在";
          model.addAttribute("msg",msg);
          model.addAttribute( "user",null );
          return "fore/register";
        }
        userService.add( user );
        return "redirect:RegisterSuccessPage";
    }

    /**
     * 通过用户名和密码：登录验证
     * @param name
     * @param password
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("forelogin")
    public String login(@RequestParam("name") String name,@RequestParam("password") String password,
                        Model model, HttpSession session){
        //HtmlUtils.htmlEscape？ 诸如 <script>alert('papapa')</script> 这样的名称，会导致网页打开就弹出一个对话框。 那么在转义之后，就没有这个问题了。
        String n = HtmlUtils.htmlEscape( name);
        String p = HtmlUtils.htmlEscape(password);
        User user = userService.isRight( n, p );
        //判断用户名是否存在
        if (user==null){
            String msg="用户或密码不正确";
            model.addAttribute("msg",msg);
            return "fore/login";
        }
        session.setAttribute( "user",user );
        return "redirect:foreHome";
    }

    /**
     * 退出登录 通过删除session
     * @param session
     * @return
     */
    @RequestMapping("foreLogout")
    public String home(HttpSession session){
        session.removeAttribute( "user" );
        return "redirect:LoginPage";
    }

    @RequestMapping("foreProduct")
    public String Product(int pid,Model model){
        Product product = productService.get( pid );
        product.setProductDetailImages(  productImageService.List( pid,
                ProductImageService.type_detail ));
        product.setProductSingleImages(  productImageService.List( pid,
                ProductImageService.type_single ));
        //得到产品属性值集合
        List<attributeValue> attributeValues = attributeValueService.list( pid );
        //评价集合
        List<Review> reviews = reviewService.List( pid );
        //给产品添加销售数和评价数
        productService.setSaleCountAndReview( product);
        model.addAttribute( "reviews",reviews );
        model.addAttribute( "p",product );
        model.addAttribute( "avs",attributeValues );

        return "fore/product";
    }
    @RequestMapping("foreCheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session){
        User user = (User)session.getAttribute( "user" );
        if (user!=null){
            System.out.println(user.getName()+"==="+user.getPassword());
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping("foreLoginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name,@RequestParam("password") String password,  HttpSession session){
        //进行转义
        String NAME = HtmlUtils.htmlEscape( name );
        String PW = HtmlUtils.htmlEscape( password );
        User user = userService.get( NAME, PW );
        if (user!=null){
            session.setAttribute( "user",user );
            return "success";
        }else {
            return "fail";
        }
    }
    @RequestMapping("foreCategory")
    public String Category(int cid,String sort,Model model){
        Category category = categoryService.get( cid );
        //给这类属性添加其产品
        productService.fill( category );
        //给所以的产品添加评价数和销售数
        productService.setSaleCountAndReview(category.getProducts());
        if(sort!=null) {
            switch (sort) {
                /**
                 * 根据Collections这个类进行排列，第二个参数传入排序的条件
                 */
                case "review":
                    Collections.sort( category.getProducts(), new ProducReviewComparator() );
                    break;
                case "saleCount":
                    Collections.sort( category.getProducts(), new ProducSaleCountComparator() );
                    break;
                case "date":
                    Collections.sort( category.getProducts(), new ProductDataComparator() );
                    break;
                case "all":
                    Collections.sort( category.getProducts(), new ProductAllComparator() );
                    break;
                case "price":
                    Collections.sort( category.getProducts(), new ProducPriceComparator() );
                    break;
            }
        }
        model.addAttribute( "c",category );
        return "fore/category";
    }
    @RequestMapping("foreSearch")
    public String search(String keyWord,Model model) {
        PageHelper.offsetPage( 0, 20 );
        List<Product> products = productService.search( keyWord);
        productService.setSaleCountAndReview( products );
        model.addAttribute( "ps", products );
        return "fore/searchResult";
    }
//点击立即购买  -》进入结算界面
    @RequestMapping("foreBuyOne")
    public String buyProduct(int pid,int num,Model model,HttpSession session) {
        Product product = productService.get( pid );
        int oiId=0;
//        订单是否存在
        boolean found=false;
        User user = (User) session.getAttribute( "user" );
        List<OrderItem> orderItems = orderItemService.listByUser( user.getId() );
        for (OrderItem orderItem:orderItems){
//            如果该用户订单中已经存在要添加的商品  添加订单数
            if(orderItem.getProduct().getId().intValue()==product.getId().intValue()){
                orderItem.setNumber( orderItem.getNumber()+num );
                orderItemService.update( orderItem );
                found=true;
                //跳转到订单界面
                oiId=orderItem.getId();
                break;
            }
        }
        //如果没有发现该商品订单 创建新订单
        if(!found){
            OrderItem orderItem = new OrderItem();
            orderItem.setNumber( num );
            orderItem.setPid( pid );
            orderItem.setUid( user.getId() );
            orderItemService.add( orderItem );
            oiId=orderItem.getId();
        }
        return "redirect:foreBuy?oiid="+oiId;
    }

    @RequestMapping("foreBuy")
    public String buy( String[] oiid,Model model,HttpSession session) {
        List<OrderItem> orderItems= new ArrayList<>(  );
        float total=0;
        for (String soiid:oiid) {
            int id = Integer.parseInt( soiid );
            OrderItem orderItem = orderItemService.get( id );
            //计算总价钱
            total+=orderItem.getProduct().getPromotePrice().floatValue() * orderItem.getNumber();
            orderItems.add( orderItem );
        }
        session.setAttribute( "ois",orderItems );
        model.addAttribute( "total",total );
        return "fore/buyPage";
    }

    @RequestMapping("foreAddCart")
    @ResponseBody
    public String addCart( int pid, int num,Model model,HttpSession session) {
        Product product = productService.get( pid );
        User user = (User)session.getAttribute( "user" );
        boolean found = false;
        List<OrderItem> orderItems = orderItemService.listByUser( user.getId() );
        for (OrderItem orderItem:orderItems){
//            如果该用户订单中已经存在要添加的商品  添加订单数
            if(orderItem.getProduct().getId().intValue()==product.getId().intValue()){
                orderItem.setNumber( orderItem.getNumber()+num );
                orderItemService.update( orderItem );
                found=true;
                break;
            }
        }
        //如果没有发现该商品订单 创建新订单
        if(!found){
            OrderItem orderItem = new OrderItem();
            orderItem.setNumber( num );
            orderItem.setPid( pid );
            orderItem.setUid( user.getId() );
            orderItemService.add( orderItem );
        }
        return  "success";
    }
    @RequestMapping("foreCart")
       public String cart(Model model,HttpSession session){
        User user = (User) session.getAttribute( "user" );
        List<OrderItem> orderItems = orderItemService.listByUser( user.getId() );
        model.addAttribute( "ois",orderItems );
        return "fore/cart";
    }
    //更改购物车的产品
    @RequestMapping("foreChangeOrderItem")
    @ResponseBody
    public String deleteOrderItem(Model model,HttpSession session,int num,int pid){
        User user = (User) session.getAttribute( "user" );
        if(user!=null){
            List<OrderItem> orderItems = orderItemService.listByUser( user.getId() );
            //查看订单项中和该产品相同的Id 并更新该产品的订单数
            for (OrderItem orderItem : orderItems) {
                if(orderItem.getProduct().getId()==pid){
                    orderItem.setNumber( num );
                    orderItemService.update( orderItem );
                    break;
                }
            }
            return "success";
        }
        return "false";
    }

    @RequestMapping("foreDeleteOrderItem")
    public String deleteOrderItem(HttpSession session,int oiid){
        User user = (User) session.getAttribute( "user" );
        if(user!=null){
            orderItemService.delete( oiid );
            return "success";
        }
        return "false";
    }

    @RequestMapping("foreCreateOrder")
    public String createOrder(Order order,Model model,HttpSession session){
        float total=0;
        User user = (User) session.getAttribute( "user" );
//        订单码 日期加上10000随机数
        String orderCode = new SimpleDateFormat( "yyyyMMddmmssSSS" ).format( new Date())+RandomUtils.nextInt(10000);
        order.setOrderCode( orderCode );
        order.setCreateDate( new Date());
        order.setUid( user.getId() );
        order.setStatus( OrderService.waitPay );
        List<OrderItem> ois = (List<OrderItem>) session.getAttribute( "ois" );
        total = orderService.add( order, ois );
        return "redirect:foreAlipay?oid="+order.getId()+"&total="+total;
    }
//付款后
    @RequestMapping("forePayed")
    public String payed(int oid, Model model){
        Order order = orderService.get( oid );
        order.setStatus( OrderService.waitDelivery );
//        OrderService.
        order.setPayDate( new Date(  ) );
        orderService.update( order );
        model.addAttribute( "order",order );
        return "fore/payed";
    }

    @RequestMapping("forebought")
    public String bought( HttpSession session, Model model){
        User user =(User)session.getAttribute( "user" );
        List<Order> orders = orderService.list( user.getId(), OrderService.delete );
        orderItemService.finOrder( orders );
        model.addAttribute( "os",orders);
        return "fore/bought";
    }
//确认收货，如果确认，钱将真正从alibb转入商家账户
    @RequestMapping("foreConfirmPay")
    public String confirmPay(int oid,Model model){
        Order order = orderService.get( oid );

        //用户确认收货
        order.setConfirmDate( new Date(  ) );
        orderService.update( order );

        orderItemService.finOrder(order  );
        model.addAttribute( "order",order );
        return "fore/confirmPay";
    }
    //确认付款后界面
    @RequestMapping("foreOrderConfirmPay")
    public String orderConfirmPay(int oid,Model model){
        Order order = orderService.get( oid );

        order.setStatus( OrderService.waitReview );
        orderService.update( order );
        return "fore/orderConfirmed";
    }
//删除订单
    @RequestMapping("foreDeleteOrder")
    @ResponseBody
    public String deleteOrder(int oid,HttpSession session){
//        User user = (User) session.getAttribute( "user" );
        Order order = orderService.get( oid );
        order.setStatus( OrderService.delete );
        orderService.update( order );
        return "success";
    }
    //评价产品
    @RequestMapping("foreReview")
    public String review(int oid,Model model){
        Order order = orderService.get( oid );
        orderItemService.finOrder( order );
        //得到第一个产品的信息用来显示
        Product product = order.getOrderItems().get( 0 ).getProduct();
        //得到该产品的累计评价
        List<Review> reviews = reviewService.List( product.getId() );
        //得到销量和累积评价
        productService.setSaleCountAndReview( product );
        model.addAttribute( "p",product );
        model.addAttribute( "o",order );
        model.addAttribute( "rs",reviews );
        return "fore/review";
    }

    //评价产品
    @RequestMapping("foreDoReview")
    public String doReview(@RequestParam("oid") int oid, @RequestParam("pid") int pid,
                         String content,HttpSession session){
        //改变订单的状态
        Order order = orderService.get( oid );
        order.setStatus( OrderService.finish );
        orderService.update( order );
//        得到产品信息和用户信息并更新订单
//        Product product = productService.get( pid );
        String c = HtmlUtils.htmlEscape( content );
        User user = (User) session.getAttribute( "user" );

        Review review = new Review();
        review.setUid( user.getId() );
        review.setContent( c );
        review.setPid(pid);
        review.setCreatedate( new Date(  ) );
        reviewService.add( review );
        return "redirect:foreReview?oid="+oid+"&showonly=true";
    }
//用户自己可以发货
    @RequestMapping("adminOderDeliver")
    @ResponseBody
    public String delivery(int id){
        Order order = orderService.get( id );
        order.setDeliveryDate( new Date() );
        order.setStatus( OrderService.waitConfirm);
        orderService.update( order );
        return "success";
    }
}
