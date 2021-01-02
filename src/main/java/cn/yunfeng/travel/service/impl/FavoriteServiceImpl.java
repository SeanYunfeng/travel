package cn.yunfeng.travel.service.impl;

import cn.yunfeng.travel.dao.FavoriteDao;
import cn.yunfeng.travel.dao.impl.FavoriteDaoImpl;
import cn.yunfeng.travel.domain.Favorite;
import cn.yunfeng.travel.domain.PageBean;
import cn.yunfeng.travel.domain.Route;
import cn.yunfeng.travel.service.FavoriteService;

import java.util.List;

/**
 * @ClassName: FavoriteServiceImpl
 * @Author： 云峰
 * @Description：  对favorite表的查出数据进行操作
 * @Create： 2020--12--17  14:08
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();

    /* *
        * @MethodName: isFavorite
        * @Description: 判断是否被收藏
        * @Params: [rid, uid]
        * @Return: boolean
     */
    @Override
    public boolean isFavorite(int rid, int uid) {
        Favorite favorite=favoriteDao.findByRidAndUid(rid,uid);
        return favorite !=null;//如果favorite=null 返回false ！=null 返回true
    }

    /* *
        * @MethodName: addFavorite
        * @Description: 添加一个收藏线路
        * @Params: [rid, uid]
        * @Return: void
     */
    @Override
    public void addFavorite(int rid, int uid) {
        favoriteDao.addFavorite(rid,uid);//收藏表中增加一个收藏路线
        favoriteDao.updateFavoriteCount(rid);//更新路线表中的收藏数量
    }
    /* *
        * @MethodName: favoriteQuery
        * @Description: 按照收藏数量排序
        * @Params: [currentPage, pageSize]
        * @Return: cn.yunfeng.travel.domain.PageBean<cn.yunfeng.travel.domain.Route>
     */
    @Override
    public PageBean<Route> favoriteQuery(String rname,int currentPage, int pageSize) {
        PageBean<Route> pageBean=new PageBean<>();
        //1.记录当前页数
        pageBean.setCurrentPage(currentPage);
        //2.记录每页多少数据
        pageBean.setPageSize(pageSize);
        //3.调用favoriteDao.findTotalCount()方法来，查出表中最大记录数
        int totalCount=favoriteDao.findTotalCount(rname);
        pageBean.setTotalCount(totalCount);
        //4.查找出每页的数据
        int start=(currentPage-1)*pageSize;//找出开始的记录的起点
        List<Route> list=favoriteDao.findByPage(start,pageSize,rname);
        for (int i = 0; i < list.size(); i++) {
            int count;//收藏次数
            int number=((currentPage-1)*pageSize)+i+1;
            list.get(i).setNumber(number);//定义每一个route在总数据中的序号
        }
        pageBean.setList(list);
        //5.计算总的页数
        int totalPage=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
    /* *
        * @MethodName: myCollection
        * @Description: 找出用户收藏的线路
        * @Params: [uid]
        * @Return: java.util.List<cn.yunfeng.travel.domain.Route>
     */
    @Override
    public List<Route> myCollection(int uid) {
        return favoriteDao.myCollection(uid);
    }

    /* *
        * @MethodName: removeRoute
        * @Description: 删除收藏的线路
        * @Params: [rid, uid]
        * @Return: void
     */
    @Override
    public void removeRoute(int rid, int uid) {
        //1.调用删除一个收藏线路的方法
        favoriteDao.removeRoute(rid,uid);
        //2.更新收藏线路的收藏次数
        favoriteDao.updateFavoriteCount(rid);

    }

}
