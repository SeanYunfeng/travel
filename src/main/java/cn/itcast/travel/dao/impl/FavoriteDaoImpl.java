package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: FavoriteDaoImpl
 * @Author： 云峰
 * @Description： 使用template对favorite表的操作
 * @Create： 2020--12--17  14:11
 */
public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /* *
        * @MethodName: findByRidAndUid
        * @Description: 通过rid和uid来找到收藏对象
        * @Params: [rid, uid]
        * @Return: cn.itcast.travel.domain.Favorite
     */
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {

        Favorite favorite;
        try {
            String sql="select * from tab_favorite where rid=? and uid=?";
            favorite=template.queryForObject(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),rid,uid);
        } catch (Exception e) {
            return null;
        }
        return favorite;
    }
    /* *
        * @MethodName: findCount
        * @Description: 查询该线路被收藏的次数
        * @Params: [rid]
        * @Return: int
     */
    @Override
    public int findCount(int rid) {
        String sql="select count(*) from tab_favorite where rid=?";
        return template.queryForObject(sql,Integer.class,rid);
    }

    /* *
        * @MethodName: addFavorite
        * @Description: 收藏线路
        * @Params: [rid, uid]
        * @Return: void
     */
    @Override
    public void addFavorite(int rid, int uid) {
        String sql="insert into tab_favorite values(?,?,?)";
        template.update(sql,rid,new Date(),uid);
    }
    /* *
        * @MethodName: findTotalCount
        * @Description: 查出最多的记录数
        * @Params: [rname]
        * @Return: int
     */
    @Override
    public int findTotalCount(String rname) {
        //1.定义一个sql模板
        String sql="select count(*) from tab_route where 1=1";
        StringBuffer sb=new StringBuffer(sql);
        List params=new ArrayList();
        //2.判断rname是否有值
        if(rname!=null&&rname.length()>0){
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");
        }
        sql=sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }
    /* *
        * @MethodName: findByPage
        * @Description: 通过当前的页码，找出每页的数据
        * @Params: [start, pageSize, rname]
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> findByPage(int start, int pageSize, String rname) {
        String sql="select * from tab_route where 1=1";//定义sql模板
        StringBuffer sb=new StringBuffer(sql);
        List params=new ArrayList();
        //2.判断rname是否有值
        if(rname!=null&&rname.length()>0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        //嵌套查询
        sb.append(" order by (select count(*) from tab_favorite where tab_route.rid=tab_favorite.rid) desc ");
        sb.append(" limit ?, ? ");//数据的数量
        params.add(start);
        params.add(pageSize);
        sql=sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }
    /* *
        * @MethodName: updateFavoriteCount
        * @Description: 更新路线表的收藏数量
        * @Params: [rid]
        * @Return: void
     */
    @Override
    public void updateFavoriteCount(int rid) {
        //1.调用查找出收藏的次数
        int count=findCount(rid);
        //2.将查出的count的次数更新到数据库中
        String sql1="update tab_route set count=? where rid=?";
        template.update(sql1,count,rid);
    }

    /* *
        * @MethodName: myCollection
        * @Description: 查询出我收藏的线路
        * @Params: [uid]
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> myCollection(int uid) {
        String sql="select r.rid,r.rname,r.price,r.rimage from tab_route as r, tab_favorite as f where r.rid=f.rid and uid=?";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),uid);
    }

    /* *
     * @MethodName: removeRoute
     * @Description: 删除收藏的线路
     * @Params: [rid,uid]
     * @Return: void
     */
    @Override
    public void removeRoute(int rid,int uid) {
        String sql="delete from tab_favorite where rid=? and uid=?";
        template.update(sql,rid,uid);
    }
}
