package cn.yunfeng.travel.dao.impl;

import cn.yunfeng.travel.dao.CategoryDao;
import cn.yunfeng.travel.domain.Category;
import cn.yunfeng.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName: CategoryImpl
 * @Author： 云峰
 * @Description： 使用template对category表的操作
 * @Create： 2020--12--15  16:37
 */
public class CategoryImpl implements CategoryDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category";
        return template.query(sql,new BeanPropertyRowMapper<>(Category.class));
    }
}
