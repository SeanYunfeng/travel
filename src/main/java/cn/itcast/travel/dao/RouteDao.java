package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @ClassName: RouteDao
 * @Author： 云峰
 * @Description： RouteDao的抽象类
 * @Create： 2020--12--15  20:36
 */
public interface RouteDao {

    /**
        * @MethodName: findTotalCount
        * @Description: 返回总的记录数
        * @Params: [cid]
        * @Return: int
     */
    public int findTotalCount(int cid,String rname);
    /**
        * @MethodName: findByPage
        * @Description: 返回一个当前页的记录
        * @Params: [cid, start, pageSize]
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    public List<Route> findByPage(int cid,int start,int pageSize,String rname);
    /**
        * @MethodName: findByRid
        * @Description: 通过一个rid查出一个route对象
        * @Params: [rid]
        * @Return: cn.itcast.travel.domain.Route
     */
    public Route findByRid(int rid);
    /**
        * @MethodName: popular_tourism
        * @Description: 取出人气旅游的数据
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    public List<Route> popular_tourism();
    /**
        * @MethodName: latestTravel
        * @Description: 显示最新旅游的线路
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    public List<Route> latestTravel();
    /**
        * @MethodName: themeTravel
        * @Description: 取出主题旅游的线路
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    public List<Route> themeTravel();
    /**
        * @MethodName: domesticTravel
        * @Description: 取出最热的国内游
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    public List<Route> domesticTravel();

    /**
        * @MethodName: overseasTravel
        * @Description: 取出境外游的数据
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    public List<Route> overseasTravel();
    /**
        * @MethodName: popularTours
        * @Description: 取出各个类别中收藏最多的旅游数据
        * @Params: [cid]
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    public List<Route> popularTours(int cid);


}
