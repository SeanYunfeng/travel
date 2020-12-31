package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserServlet
 * @Author： 云峰
 * @Description： 从前端拿到关于用户的数据，然后调用service层的方法
 * @Create： 2020--12--15  13:10
 */
@WebServlet("/User/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    /* *
        * @MethodName: register
        * @Description: 用户注册的servlet方法
        * @Params: [request, response]
        * @Return: void
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证验证码
        String check = request.getParameter("check");
        HttpSession session=request.getSession();
        String checkCode =(String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//保正验证码只可使用一次
        if(checkCode==null||!checkCode.equalsIgnoreCase(check)){
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码输入错误!");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        //2.封装对象
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service完成注册

        boolean flag=userService.register(user);
        ResultInfo info = new ResultInfo();
        //4.响应结果
        if(flag){
            //注册成功
            info.setFlag(true);
        }else{
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败!!用户名冲突");
        }
        //5.将info对象序列化json
        writeValueAsString(info,response);
    }
    /* *
        * @MethodName: activeUser
        * @Description: 邮件激活的servlet方法
        * @Params: [request, response]
        * @Return: void
     */
    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取验证码
        String code = request.getParameter("code");
        //2.判断验证码是否为空
        if(code!=null){
            boolean flag=userService.active(code);
            //判断是否激活成功
            String msg=null;
            if(flag){
                msg="激活成功，请<a href='http://localhost:8080/travel/login.html'> 登录 </a>";
            }else{
                msg="激活失败，请<a href=''> 请联系管理员！</a>";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
    /* *
        * @MethodName: login
        * @Description: 用户登录的servlet方法
        * @Params: [request, response]
        * @Return: void
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证验证码
        String check = request.getParameter("check");
        HttpSession session=request.getSession();
        String checkCode =(String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//保正验证码只可使用一次
        if(checkCode==null||!checkCode.equalsIgnoreCase(check)){
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码输入错误!");
            writeValueAsString(info,response);
            return;
        }
        //1.取出用户信息
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            //2.封装用户信息
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.通过数据库验证登录信息
        User loginUser= userService.login(user);
        //4.判断返回用户是否为空,返回信息给前端
        ResultInfo info = new ResultInfo();
        if(loginUser==null){
            //不存在该用户
            info.setFlag(false);
            info.setErrorMsg("用户不存在，请注册");
        }
        else if(!loginUser.getStatus().equals("Y")){
            //该用户没有激活
            info.setFlag(false);
            info.setErrorMsg("用户未激活!");
        }
        else if(loginUser.getStatus().equals("Y")){
            request.getSession().setAttribute("User",loginUser);
            //该用户激活并且存在
            info.setFlag(true);
        }
        //将返回信息写回前台
        writeValueAsString(info,response);
    }
    /* *
        * @MethodName: findOne
        * @Description: 找到一个用户信息
        * @Params: [request, response]
        * @Return: void
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("User");
        //将用户对象写到前端
        writeValueAsString(user,response);

    }
    /* *
        * @MethodName: exit
        * @Description: 用户退出的servlet方法
        * @Params: [request, response]
        * @Return: void
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁Session
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
    /* *
        * @MethodName: changeUserInfo
        * @Description: 修改用户的信息
        * @Params: [request, response]
        * @Return: void
     */
    public void changeUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        User newUser = new User();
        //2.封装对象
        try {
            BeanUtils.populate(newUser,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.从session里面找到要更新信息的用户的uid
        User oldUser=(User)request.getSession().getAttribute("User");
        //4.调用service方法，重新更新用户信息
        User updateUser=userService.updateUserInfo(oldUser.getUid(),newUser);
        //5.将更新好的用户，存入session作用域里面
        request.getSession().setAttribute("User",updateUser);
        response.sendRedirect(request.getContextPath()+"/my.jsp");
    }
    /* *
        * @MethodName: fileUpload
        * @Description: 上传图片的方法
        * @Params: [request, response]
        * @Return: void
     */
    public void fileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.首先判断上传的数据是否是多段数据（只有是多段数据才可以是上传文件）
        if (ServletFileUpload.isMultipartContent(request)) {
            // 2. 创建FileItemFactory实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            // 3. 创建用于解析上传数据的工具类ServletFileUpload
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                //3.1 解析所有的数据
                List<FileItem> items = servletFileUpload.parseRequest(request);
                //3.2 循环遍历所有的每一个表单项
                for (FileItem item : items) {
                    // 3.3 普通表单项
                   //4. 将文件写入某个磁盘
                   String dir = this.getServletContext().getRealPath("/img/userImg");
                   File imgdir = new File(dir);//如果给目录不存在就新建
                   if(!imgdir.exists()){
                       imgdir.mkdirs();
                   }
                   item.write(new File(dir+"\\"+item.getName()));
                   //调用service层的方法，保存图片路径到数据库中
                   User user = (User) request.getSession().getAttribute("User");
                   userService.updateImage(user.getUid(),item.getName());
                   User newUser=userService.findByUid(user.getUid());//返回更新好的user对象
                    //把更新好的user对象放入session域中
                   request.getSession().setAttribute("User",newUser);
                   response.sendRedirect(request.getContextPath()+"/my.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
