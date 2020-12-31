package cn.itcast.travel.test;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName:
 * @Author： 云峰
 * @Description：
 * @Create： 2020--12--30  14:43
 */
public class UserDaoTest {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Test
    public void findByUserName() {
        User user;
        try {
            //1.定义SQL语句
            String sql="select * from tab_user where username=?";
            //2.返回一个对象
            user=template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),"云峰");
            System.out.println(user);
        } catch (Exception e) {

        }
    }

    @Test
    public void saveUser() {
        User user=new User();
        //1.定义sql语句
        String sql="insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code)"
                +" values(?,?,?,?,?,?,?,?,?)";
        //2.执行SQL语句
        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    @Test
    public void findByCode() {
        User user;
        try {
            //1.定义SQL语句
            String sql="select * from tab_user where code=?";
            //2.返回一个对象
            user=template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),"aeElf");
            System.out.println(user);
        } catch (Exception e) {

        }

    }

    @Test
    public void updateStatus() {
        User user=new User();
        String sql="update tab_user set status='Y' where  uid=?";
        template.update(sql,user.getUid());
    }

    @Test
    public void findByUserNameAndPassword() {
        User user;
        try {
            //1.定义SQL语句
            String sql="select * from tab_user where username=? and password=?";
            //2.返回一个对象
            user=template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),"云峰",10000);
        } catch (Exception e) {

        }

    }

    @Test
    public void updateUserInfo() {
        User newUser=new User();
        String sql="update tab_user set username=?, email=?,name=?,telephone=?,sex=? where uid=?";
        template.update(sql,newUser.getUsername(),
                newUser.getEmail(),
                newUser.getName(),
                newUser.getTelephone(),
                newUser.getSex(),10);
    }


}