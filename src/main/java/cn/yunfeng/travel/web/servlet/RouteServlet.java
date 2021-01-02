package cn.yunfeng.travel.web.servlet;

import cn.yunfeng.travel.domain.PageBean;
import cn.yunfeng.travel.domain.Route;
import cn.yunfeng.travel.domain.User;
import cn.yunfeng.travel.service.FavoriteService;
import cn.yunfeng.travel.service.RouteService;
import cn.yunfeng.travel.service.impl.FavoriteServiceImpl;
import cn.yunfeng.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName: RouteServlet
 * @Author： 云峰
 * @Description： 从前端拿到关于Route的数据，然后调用service的方法进行操作
 * @Create： 2020--12--15  20:17
 */
@WebServlet("/Route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService=new FavoriteServiceImpl();

    /* *
     * @MethodName: pageQuery
     * @Description: 分页的方法
     * @Params: [request, response]
     * @Return: void
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.从前端页面得到数据
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        //tomcat7没有解决get乱码问题 (解决查找线路的问题)
        String rname = request.getParameter("rname");
        if (rname != null) {
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }
        if ("null".equals(rname)) {
            rname = null;
        }

        //2.String 数据转换为 int类型
        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);//取出数据的类别
        }
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;//不传值，默认为第一页
        }
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;//不传值，默认为一页5行数据
        }
        PageBean<Route> routePageBean = routeService.pageQuery(cid, currentPage, pageSize, rname);
        writeValueAsString(routePageBean, response);
    }

    /* *
        * @MethodName: findOne
        * @Description: 通过传入的id查找出线路来
        * @Params: [request, response]
        * @Return: void
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.从前端页面取到rid的值
        String rid=request.getParameter("rid");
        //2.调用RouteService里面的findOne方法
        Route route=routeService.findOne(Integer.parseInt(rid));
        //3.使用json返回数据
        writeValue(route,response);
    }

    /**
     * 判断该线路是否被收藏了
     * @param request 请求对象
     * @param response 响应对象
     * @return void
    */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.从前端页面取到一个rid
        String ridSir=request.getParameter("rid");
        //2.获得用户一个uid
        User user=(User)request.getSession().getAttribute("User");
        int uid;
        int rid;
        if(user==null){//用户未登录
            uid=0;
        }else{//用户已经登录
            uid=user.getUid();
        }
        if(ridSir!=null&&ridSir.length()>0&&!"null".equals(ridSir)){
            rid=Integer.parseInt(ridSir);
        }else{
            rid=0;
        }
        //3.调用favoriteService里面的方法
        boolean flag = favoriteService.isFavorite(rid, uid);
        writeValue(flag,response);
    }
    /* *
        * @MethodName: addFavorite
        * @Description: 添加到收藏列表中
        * @Params: [request, response]
        * @Return: void
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.从前端页面取到一个rid
        String ridSir=request.getParameter("rid");
        //2.获得用户一个uid
        User user=(User)request.getSession().getAttribute("User");
        int uid;
        int rid;
        if(user==null){//用户未登录
            uid=0;
        }else{//用户已经登录
            uid=user.getUid();
        }
        if(ridSir!=null&&ridSir.length()>0&&!"null".equals(ridSir)){
            rid=Integer.parseInt(ridSir);
        }else{
            rid=0;
        }
        //3.调用favoriteService里面增加一个收藏的方法
        favoriteService.addFavorite(rid, uid);
    }
    /* *
        * @MethodName: popular_tourism
        * @Description: 取出人气旅游线路
        * @Params: [request, response]
        * @Return: void
     */
    public void popularTourism(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用RouteService的方法popular_tourism
        List<Route> routes=routeService.popular_tourism();
        writeValue(routes,response);
    }
    /* *
        * @MethodName: latestTravel
        * @Description: 取出最新旅游的线路
        * @Params: [request, response]
        * @Return: void
     */
    public void latestTravel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用RouteService的方法latestTravel
        List<Route> routes=routeService.latestTravel();
        writeValue(routes,response);
    }
    /* *
        * @MethodName: themeTravel
        * @Description:取出主题旅游的线路
        * @Params: [request, response]
        * @Return: void
     */
    public void themeTravel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用RouteService的方法themeTravel
        List<Route> routes=routeService.themeTravel();
        writeValue(routes,response);
    }
    /* *
        * @MethodName: domesticTravel
        * @Description: 取出国内游的数据
        * @Params: [request, response]
        * @Return: void
     */
    public void domesticTravel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用RouteService的方法domesticTravel
        List<Route> routes=routeService.domesticTravel();
        writeValue(routes,response);
    }
    /* *
        * @MethodName: overseasTravel
        * @Description: 取出境外游的数据
        * @Params: [request, response]
        * @Return: void
     */
    public void overseasTravel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用RouteService的方法overseasTravel
        List<Route> routes=routeService.overseasTravel();
        writeValue(routes,response);
    }
    /* *
        * @MethodName: favoriteQuery
        * @Description: 按照收藏数量排序
        * @Params: [request, response]
        * @Return: void
     */
    public void favoriteQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.从前端页面得到数据
        String currentPageStr = request.getParameter("currentPage");//获取当前页
        String pageSizeStr = request.getParameter("pageSize");//取得每页多少数据

        //tomcat7没有解决get乱码问题 (解决查找线路的问题)
        String rname = request.getParameter("rname");
        if (rname != null) {
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }
        if ("null".equals(rname)) {
            rname = null;
        }
        //2.String 数据转换为 int类型
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;//不传值，默认为第一页
        }
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 10;//不传值，默认为一页10行数据
        }
        PageBean<Route> routePageBean=favoriteService.favoriteQuery(rname,currentPage,pageSize);
        writeValueAsString(routePageBean, response);
    }
    /* *
        * @MethodName: popularTours
        * @Description: 查询每个类别中收藏最多的路线
        * @Params: [request, response]
        * @Return: void
     */
    public void popularTours(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.取到cid
        String cidStr=request.getParameter("cid");
        int cid = 5;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);//取出数据的类别
        }
        List<Route> routes=routeService.popularTours(cid);
        writeValue(routes,response);
    }
    /* *
        * @MethodName: myCollection
        * @Description: 取到用户收藏的线路
        * @Params: [request, response]
        * @Return: void
     */
    public void myCollection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session域中取出uid出来
        User user = (User) request.getSession().getAttribute("User");
        List<Route> routes=favoriteService.myCollection(user.getUid());
        writeValue(routes,response);
    }

    /* *
        * @MethodName: removeRoute
        * @Description: 删除你收藏的线路
        * @Params: [request, response]
        * @Return: void
     */
    public void removeRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.从前台得到rid的值
        String ridStr=request.getParameter("rid");
        int rid=0;
        if(ridStr.length() > 0 && !ridStr.equals("null")){
            rid=Integer.parseInt(ridStr);
        }
        //2.调用session层的用户对象出来
        User user=(User)request.getSession().getAttribute("User");
        //3.调用服务层的删除收藏线路方法
        favoriteService.removeRoute(rid,user.getUid());
    }
}