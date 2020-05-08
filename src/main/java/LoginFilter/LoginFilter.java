package LoginFilter;

import com.hdljava.skycat.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.Arrays;

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
public class LoginFilter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
//        System.out.println(contextPath);
        //不需要拦截的界面
        String[] noNeedLoginPage=new String[]{"Home","login","CheckLogin","Category","Product","Register","searchResult","LoginAjax","Search"};

        String uri = request.getRequestURI();
//        System.out.println(uri);
         uri = StringUtils.remove( uri, contextPath );
//        System.out.println(uri);
        if (uri.startsWith("/fore")){
            //取分隔后的字符串
            String method = StringUtils.substringAfter( uri,"/fore" );
//            如果是需要登录才能访问的界面
            if(!Arrays.asList(noNeedLoginPage).contains( method )){
                //判断是否登录
                User user = (User) session.getAttribute( "user" );
                if (user==null){
                    response.sendRedirect( "LoginPage" );
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

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
