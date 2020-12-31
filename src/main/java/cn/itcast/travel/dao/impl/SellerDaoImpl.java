package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName: SellerDaoImpl
 * @Author： 云峰
 * @Description： 使用template对Seller表的操作
 * @Create： 2020--12--17  10:07
 */
public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /* *
     * @MethodName: findBySid
     * @Description:通过一个sid 查出一个seller对象
     * @Params: [sid]
     * @Return: cn.itcast.travel.domain.Seller
     */
    @Override
    public Seller findBySid(int sid) {
        String sql="select * from tab_seller where sid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),sid);
    }
}
