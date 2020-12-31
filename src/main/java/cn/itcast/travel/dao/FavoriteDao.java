package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @ClassName: FavoriteDao
 * @Author： 云峰
 * @Description： FavoriteDao的抽象类
 * @Create： 2020--12--17  14:10
 */
public interface FavoriteDao {
    /* *
        * @MethodName: findByRidAndUid
        * @Description: 是否可以找到这个收藏的对象
        * @Params: [rid, uid]
        * @Return: cn.itcast.travel.domain.Favorite
     */
    public Favorite findByRidAndUid(int rid,int uid);

    /* *
        * @MethodName: findCount
        * @Description: 通过一个rid查找出该线路的收藏次数
        * @Params: [rid]
        * @Return: int
     */
    public int findCount(int rid);

    /* *
        * @MethodName: addFavorite
        * @Description: 添加一个收藏线路
        * @Params: [rid, uid]
        * @Return: void
     */
    public void addFavorite(int rid, int uid);
    /* *
        * @MethodName: findTotalCount
        * @Description: 找出总共的数量
        * @Params: [rname]
        * @Return: int
     */
    public int findTotalCount(String rname);
    /* *
        * @MethodName: findByPage
        * @Description: 通过一个确定的页，来找出
        * @Params: [start, pageSize, rname]
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    public List<Route> findByPage(int start, int pageSize, String rname);
    /* *
        * @MethodName: updateFavoriteCount
        * @Description: 更新收藏线路中的count（收藏次数）的值
        * @Params: [rid]
        * @Return: void
     */
    public void updateFavoriteCount(int rid);
    /* *
        * @MethodName: myCollection
        * @Description: 查找出用户的收藏路线
        * @Params: [uid]
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    public List<Route> myCollection(int uid);

    /* *
        * @MethodName: removeRoute
        * @Description: 删除一个收藏线路
        * @Params: [rid, uid]
        * @Return: void
     */
    public void removeRoute(int rid, int uid);
}
