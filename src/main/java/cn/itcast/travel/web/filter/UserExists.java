package cn.itcast.travel.web.filter;


import cn.itcast.travel.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: UserExists
 * @Author： 云峰
 * @Description： 过滤没有登录就访问个人信息管理
 * @Create： 2020--12--28  10:53
 */
@WebFilter(urlPatterns = "/*")
public class UserExists implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {

        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        String method=request.getServletPath();
        //判断是否为要拦截的jsp页面
        if(method.contains("my.jsp")||method.contains("updatePh.jsp")){
            User user=(User)request.getSession().getAttribute("User");
            if(user==null){
                response.sendRedirect(request.getContextPath()+"/login.html");//跳转回登录页面
            }else{
                chain.doFilter(req,rep);
            }
        }
        else{
            chain.doFilter(req,rep);
        }

    }

    @Override
    public void destroy() {

    }
}