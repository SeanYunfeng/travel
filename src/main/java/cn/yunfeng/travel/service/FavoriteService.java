package cn.yunfeng.travel.service;

import cn.yunfeng.travel.domain.PageBean;
import cn.yunfeng.travel.domain.Route;

import java.util.List;

/**
 * @ClassName: FavoriteService
 * @Author： 云峰
 * @Description： FavoriteService的抽象类
 * @Create： 2020--12--17  14:08
 */
public interface FavoriteService {
    /* *
        * @MethodName: isFavorite
        * @Description: 判断用户是否收藏了这个线路
        * @Params: [rid, uid]
        * @Return: boolean
     */
    public boolean isFavorite(int rid,int uid);

    /* *
        * @MethodName: addFavorite
        * @Description: 添加一个收藏线路
        * @Params: [rid, uid]
        * @Return: void
     */
    public void addFavorite(int rid, int uid);
    /* *
        * @MethodName: favoriteQuery
        * @Description: 按照收藏数量排序
        * @Params: [currentPage, pageSize]
        * @Return: cn.yunfeng.travel.domain.PageBean<cn.yunfeng.travel.domain.Route>
     */
    public PageBean<Route> favoriteQuery(String rname,int currentPage, int pageSize);

    /* *
        * @MethodName: myCollection
        * @Description: 查找出我的收藏线路
        * @Params: [uid]
        * @Return: java.util.List<cn.yunfeng.travel.domain.Route>
     */
    public List<Route> myCollection(int uid);

    /* *
        * @MethodName: removeRoute
        * @Description: 删除收藏线路
        * @Params: [rid, uid]
        * @Return: void
     */
    public void removeRoute(int rid, int uid);
}
