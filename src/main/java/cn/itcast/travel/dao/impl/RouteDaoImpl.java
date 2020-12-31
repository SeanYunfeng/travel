package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RouteDaoImpl
 * @Author： 云峰
 * @Description： 使用template对Route表的数据进行操作
 * @Create： 2020--12--15  20:39
 */
public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /* *
        * @MethodName: findTotalCount
        * @Description: 返回总的记录数
        * @Params: [cid]
        * @Return: int
     */
    @Override
    public int findTotalCount(int cid,String rname) {
//        String sql="select count(*) from tab_route where cid=?";
        //1.定义SQL模板
        String sql="select count(*) from tab_route where 1=1 ";
        StringBuffer sb=new StringBuffer(sql);//用来存入判断条件的
        List params=new ArrayList();
        //2.判断是否有值（cid，rname）
        if(cid!=0){
            sb.append("and cid=? ");
            params.add(cid);
        }
        if(rname!=null&&rname.length()>0){
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");
        }
        sql=sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    /* *
        * @MethodName: findByPage
        * @Description: 返回一个当前页的数据
        * @Params: [cid, start, pageSize]
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {

//        String sql="select * from tab_route where cid=? limit ?, ?";
        //1.首先定义出一个sql语句的模板出来
        String sql="select * from tab_route where 1=1 ";
        StringBuffer sb=new StringBuffer(sql);//用来存入判断条件的
        List params=new ArrayList();
        //2.判断是否有值（cid，rname）
        if(cid!=0){
            sb.append(" and cid=? ");
            params.add(cid);
        }
        if(rname!=null&&rname.length()>0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ?, ? ");//分页条件
        params.add(start);
        params.add(pageSize);
        sql=sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }
    /* *
     * @MethodName: findByRid
     * @Description:通过一个rid查出一个route对象
     * @Params: [rid]
     * @Return: cn.itcast.travel.domain.Route
     */
    @Override
    public Route findByRid(int rid) {
        String sql="select * from tab_route where rid=? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
    /* *
        * @MethodName: popular_tourism
        * @Description: 取出人气旅游的线路
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> popular_tourism() {
        String sql="select * from tab_route order by (select count(*) from tab_favorite where tab_route.rid=tab_favorite.rid) desc limit 0,4";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }
    /* *
        * @MethodName: latestTravel
        * @Description:取出最新旅游的线路
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> latestTravel() {
        String sql="select * from tab_route  order by(rdate) desc limit 0,4 ";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }
    /* *
        * @MethodName: themeTravel
        * @Description: 取出主题旅游的线路
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> themeTravel() {
        String sql="select * from tab_route  where isThemeTour=1 order by(rdate) desc limit 0,4  ";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }
    /* *
        * @MethodName: domesticTravel
        * @Description:国内游的数据
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> domesticTravel() {
        String sql="select * from tab_route where cid=5 order by (select count(*) from tab_favorite where tab_route.rid=tab_favorite.rid) desc limit 0,6";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }
    /* *
        * @MethodName: overseasTravel
        * @Description: 境外游的数据
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> overseasTravel() {
        String sql="select * from tab_route where cid=8 order by (select count(*) from tab_favorite where tab_route.rid=tab_favorite.rid) desc limit 0,6";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }
    /* *
        * @MethodName: popularTours
        * @Description: 查找出各个类别中收藏最多的旅游数据
        * @Params: [cid]
        * @Return: java.util.List<cn.itcast.travel.domain.Route>
     */
    @Override
    public List<Route> popularTours(int cid) {
        String sql="select * from tab_route where cid=? order by(count) desc limit 0,5";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),cid);
    }


}
