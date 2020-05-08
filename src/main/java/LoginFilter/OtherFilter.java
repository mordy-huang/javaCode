package LoginFilter;


import com.hdljava.skycat.pojo.Category;
import com.hdljava.skycat.pojo.OrderItem;
import com.hdljava.skycat.pojo.User;
import com.hdljava.skycat.service.CategoryService;
import com.hdljava.skycat.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 一、URI
 * <1>什么是URI
 * URI，通一资源标志符(Uniform Resource Identifier， URI)，表示的是web上每一种可用的资源，如 HTML文档、图像、视频片段、程序等都由一个URI进行定位的。
 * <2>URI的结构组成
 * URI通常由三部分组成：
 * ①访问资源的命名机制；
 * ②存放资源的主机名；
 * ③资源自身的名称。
 * <3>URI举例
 * 如：https://blog.csdn.net/qq_32595453/article/details/79516787
 * 我们可以这样解释它：
 * ①这是一个可以通过https协议访问的资源，
 * ②位于主机 blog.csdn.net上，
 * ③通过“/qq_32595453/article/details/79516787”可以对该资源进行唯一标识（注意，这个不一定是完整的路径）
 */
public class OtherFilter extends HandlerInterceptorAdapter {
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;
//   所有资源不拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
          return true;
    }


    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
//        获取分类信息
        List<Category> categories = categoryService.List();
        request.getSession().setAttribute( "cs",categories );
//设置标志的跳转属性
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        request.getSession().setAttribute( "contextPath",contextPath );

        //计算购物车数量
        User user = (User) session.getAttribute( "user" );
        int cartProductNumber=0;
        if (user!=null){
            List<OrderItem> orderItems = orderItemService.listByUser( user.getId() );
            for (OrderItem orderItem : orderItems) {
                cartProductNumber+=orderItem.getNumber();
            }
            request.getSession().setAttribute( "cartProductNumber",cartProductNumber );
        }
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }
}
