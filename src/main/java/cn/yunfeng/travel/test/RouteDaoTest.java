package cn.yunfeng.travel.test;

import cn.yunfeng.travel.domain.Route;
import cn.yunfeng.travel.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName:
 * @Author： 云峰
 * @Description：
 * @Create： 2020--12--30  16:34
 */
public class RouteDaoTest {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());


    @Test
    public void findByRid() {
        String sql="select * from tab_route where rid=? ";
        template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),10);
    }

    @Test
    public void popular_tourism() {
        String sql="select * from tab_route order by (select count(*) from tab_favorite where tab_route.rid=tab_favorite.rid) desc limit 0,4";
        template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }

    @Test
    public void latestTravel() {
        String sql="select * from tab_route  order by(rdate) desc limit 0,4 ";
        template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }

    @Test
    public void themeTravel() {
        String sql="select * from tab_route  where isThemeTour=1 order by(rdate) desc limit 0,4  ";
        template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }

    @Test
    public void domesticTravel() {
        String sql="select * from tab_route where cid=5 order by (select count(*) from tab_favorite where tab_route.rid=tab_favorite.rid) desc limit 0,6";
        template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }

    @Test
    public void overseasTravel() {
        String sql="select * from tab_route where cid=8 order by (select count(*) from tab_favorite where tab_route.rid=tab_favorite.rid) desc limit 0,6";
        template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }

    @Test
    public void popularTours() {
        int cid=5;
        String sql="select * from tab_route where cid=? order by(count) desc limit 0,5";
        template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),cid);
    }
}