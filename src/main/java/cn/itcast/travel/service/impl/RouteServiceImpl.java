package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @ClassName: RouteServiceImpl
 * @Author： 云峰
 * @Description： 对route表查出数据的操作
 * @Create： 2020--12--15  20:26
 */
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao=new RouteDaoImpl();
    private RouteImgDao routeImgDao=new RouteImgDaoImpl();
    private SellerDao sellerDao=new SellerDaoImpl();
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        PageBean<Route> routePageBean = new PageBean<Route>();
        //封装PageBean
        //1.当前页数
        routePageBean.setCurrentPage(currentPage);
        //2.每页记录数
        routePageBean.setPageSize(pageSize);
        //3.总的记录数
        int totalCount=routeDao.findTotalCount(cid,rname);
        routePageBean.setTotalCount(totalCount);
        //4.单前页显示的记录集合
        int start=(currentPage-1)*pageSize;
        List<Route> list=routeDao.findByPage(cid,start,pageSize,rname);
        routePageBean.setList(list);
        //5.总页数
        int totalPage=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        routePageBean.setTotalPage(totalPage);
        return routePageBean;
    }
    /* *
     * @MethodName: findOne
     * @Description:通过一个rid查出一个线路
     * @Params: [parseInt]
     * @Return: cn.itcast.travel.domain.Route
     */
    @Override
    public Route findOne(int rid) {
        //1.通过rid在route表中查出基本的信息
        Route route=routeDao.findByRid(rid);
        //2.通过rid在route_img表中查出img的list
        List<RouteImg> imgs = routeImgDao.findByRid(rid);
        route.setRouteImgList(imgs);
        //3.通过sid来查出卖家seller对象
        Seller seller = sellerDao.findBySid(route.getSid());
        route.setSeller(seller);
        //4.通过rid查出线路被收藏的次数
        int count=favoriteDao.findCount(rid);
        route.setCount(count);
        return route;
    }

    /* *
        * @MethodName: popular_tourism
        * @Description: 获得人气旅游数据
        * @Params: [List<Route>]
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> popular_tourism() {
        //1.调用routeDao方法popular_tourism方法，取出人气旅游数据
        return routeDao.popular_tourism();
    }
    /* *
        * @MethodName: latestTravel
        * @Description:取出最新旅游的线路
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> latestTravel() {
        return routeDao.latestTravel();
    }
    /* *
        * @MethodName: themeTravel
        * @Description:取出主题旅游的线路
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> themeTravel() {
        return routeDao.themeTravel();
    }
    /* *
        * @MethodName: domesticTravel
        * @Description: 取出国内游的最热数据
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> domesticTravel() {
        return routeDao.domesticTravel();
    }
    /* *
        * @MethodName: overseasTravel
        * @Description: 从境外游中取出最热数据
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> overseasTravel() {
        return routeDao.overseasTravel();
    }
    /* *
        * @MethodName: popularTours
        * @Description: 查找出各个类别中收藏线路最多的旅游线路
        * @Params: [cid]
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> popularTours(int cid) {
        return routeDao.popularTours(cid);
    }

}
