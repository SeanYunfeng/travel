package cn.yunfeng.travel.test;

import cn.yunfeng.travel.dao.impl.UserDaoImpl;
import cn.yunfeng.travel.domain.User;
import cn.yunfeng.travel.service.UserService;
import cn.yunfeng.travel.web.servlet.UserServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName:
 * @Author： 云峰
 * @Description：
 * @Create： 2020--12--31  14:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:applicationContext.xml"})
public class DataSorceTest {
    @Autowired
    private JdbcTemplate template;
    @Autowired
    private UserDaoImpl userDao;
    @Resource
    private UserService userService;
    @Test
    public void jdbcTest(){
        String sql="select * from tab_user";
        List<User> list=template.query(sql,new BeanPropertyRowMapper<>(User.class));
        System.out.println(list.size());
    }
    @Test
    public void UserDaoTest(){
        User user=userDao.findByUid(24);
        System.out.println(user.getName());
    }
    @Test
    public void UserServiceText(){
        User user=userService.findByUid(24);
        System.out.println(user.getName());
    }
    @Test
    public void servletTest(){
        UserServlet userServlet=new UserServlet();

    }





}
