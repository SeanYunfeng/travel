package cn.yunfeng.travel.dao.impl;

import cn.yunfeng.travel.dao.RouteImgDao;
import cn.yunfeng.travel.domain.RouteImg;
import cn.yunfeng.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName:
 * @Author： 云峰
 * @Description：
 * @Create： 2020--12--17  10:00
 */
public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql="select * from tab_route_img where rid=? ";
        return template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }
}
