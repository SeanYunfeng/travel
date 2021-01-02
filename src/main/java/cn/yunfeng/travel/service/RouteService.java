package cn.yunfeng.travel.service;

import cn.yunfeng.travel.domain.PageBean;
import cn.yunfeng.travel.domain.Route;

import java.util.List;

/**
 * @ClassName: RouteService
 * @Author： 云峰
 * @Description： RouteService的抽象类
 * @Create： 2020--12--15  20:26
 */
public interface RouteService {
    /**
     * 对旅游线路的分页
     * @param cid 线路类别
     * @param currentPage 当前第几页
     * @param pageSize 一页有几行数据
     * @param rname  搜索内容
     * @return cn.yunfeng.travel.domain.PageBean<cn.yunfeng.travel.domain.Route>
    */
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);
    /* *
        * @MethodName: findOne
        * @Description:通过一个rid查出一个线路
        * @Params: [parseInt]
        * @Return: cn.yunfeng.travel.domain.Route
     */
    public Route findOne(int rid);
    /* *
        * @MethodName: popular_tourism
        * @Description:取出用户人气旅游
        * @Params: []
        * @Return: java.util.List<cn.yunfeng.travel.domain.Route>
     */
    public List<Route> popular_tourism();
    /* *
        * @MethodName: latestTravel
        * @Description:取出最新旅游的线路
        * @Params: []
        * @Return: java.util.List<cn.yunfeng.travel.domain.Route>
     */
    public List<Route> latestTravel();
    /* *
        * @MethodName: themeTravel
        * @Description:取出主体旅游
        * @Params: []
        * @Return: java.util.List<cn.yunfeng.travel.domain.Route>
     */
    public List<Route> themeTravel();
    /* *
        * @MethodName: domesticTravel
        * @Description: 取出国内游的最新数据
        * @Params: []
        * @Return: java.util.List<cn.yunfeng.travel.domain.Route>
     */
    public List<Route> domesticTravel();

    /* *
        * @MethodName: overseasTravel
        * @Description: 取出境外游的数据
        * @Params: []
        * @Return: java.util.List<cn.yunfeng.travel.domain.Route>
     */
    public List<Route> overseasTravel();
    /* *
        * @MethodName: popularTours
        * @Description: 每个类别查询出收藏最多的旅游线路
        * @Params: [cid]
        * @Return: java.util.List<cn.yunfeng.travel.domain.Route>
     */
    public List<Route> popularTours(int cid);

}
