package cn.itcast.travel.test;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName:
 * @Author： 云峰
 * @Description：
 * @Create： 2020--12--30  15:59
 */
public class FavoriteServiceImplTest {
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Test
    public void isFavorite() {
        Favorite favorite=favoriteDao.findByRidAndUid(10,20);
    }

    @Test
    public void addFavorite() {
        favoriteDao.addFavorite(10,20);//收藏表中增加一个收藏路线
        favoriteDao.updateFavoriteCount(10);//更新路线表中的收藏数量
    }

    @Test
    public void favoriteQuery() {
        PageBean<Route> pageBean=new PageBean<>();
        int currentPage=10;
        int pageSize=5;
        String rname="你";
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


    }

    @Test
    public void myCollection() {
        favoriteDao.myCollection(10);
    }

    @Test
    public void removeRoute() {
        //1.调用删除一个收藏线路的方法
        favoriteDao.removeRoute(10,5);
        //2.更新收藏线路的收藏次数
        favoriteDao.updateFavoriteCount(10);
    }
}