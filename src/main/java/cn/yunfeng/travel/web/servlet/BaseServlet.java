package cn.yunfeng.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName: BaseServlet
 * @Author： 云峰
 * @Description： servlet的父类(核心类),执行所有每个servlet的方法
 * @Create： 2020--12--15  13:09
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成方法的分类
        //1.获取请求路径
        String uri = req.getRequestURI();
        //2.获取方法的名称
        String methodName= uri.substring(uri.lastIndexOf('/')+1);
        try {
            //3.获取方法的对象
            Method method = this.getClass().getMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
            //4.执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    /* *
        * @MethodName: writeValue
        * @Description: 将对象序列化，让后打印到前端页面去
        * @Params: [obj, response]
        * @Return: void
     */
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=uft-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }



    /* *
        * @MethodName: writeValueAsString
        * @Description: 将对象序列化成字符串，让后打印到前端页面去
        * @Params: [obj, response]
        * @Return: void
     */
    public void writeValueAsString(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json=mapper.writeValueAsString(obj);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
