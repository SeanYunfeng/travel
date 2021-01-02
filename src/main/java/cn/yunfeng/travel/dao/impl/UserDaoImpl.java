package cn.yunfeng.travel.dao.impl;

import cn.yunfeng.travel.dao.UserDao;
import cn.yunfeng.travel.domain.User;
import cn.yunfeng.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName: UserDaoImpl
 * @Author： 云峰
 * @Description： 使用template对User表进行操作
 * @Create： 2020--12--13  18:52
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /* *
     * @MethodName: findByUserName
     * @Description: 通过一个用户名，查出user对象来
     * @Params: [username]
     * @Return: cn.yunfeng.travel.domain.User
     */
    @Override
    public User findByUserName(String username) {
        User user;
        try {
            //1.定义SQL语句
            String sql="select * from tab_user where username=?";
            //2.返回一个对象
            user=template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),username);
        } catch (Exception e) {
            return null;
        }
        return user;
    }
    /* *
     * @MethodName: saveUser
     * @Description: 保存一个用户对象到数据库中
     * @Params: [user]
     * @Return: void
     */
    @Override
    public void saveUser(User user) {
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
    /* *
        * @MethodName: findByCode
        * @Description: 根据激活码查询用户
        * @Params: [code]
        * @Return: cn.yunfeng.travel.domain.User
     */
    @Override
    public User findByCode(String code) {
        User user;
        try {
            //1.定义SQL语句
            String sql="select * from tab_user where code=?";
            //2.返回一个对象
            user=template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),code);
        } catch (Exception e) {
            return null;
        }
        return user;
    }
    /* *
        * @MethodName: updateStatus
        * @Description:修改用户状态
        * @Params: [user]
        * @Return: void
     */
    @Override
    public void updateStatus(User user) {
        String sql="update tab_user set status='Y' where  uid=?";
        template.update(sql,user.getUid());
    }
    /* *
        * @MethodName: findByUserNameAndPassword
        * @Description:根据用户名和密码来返回一个用户
        * @Params: [user]
        * @Return: cn.yunfeng.travel.domain.User
     */
    @Override
    public User findByUserNameAndPassword(String username,String password) {
        User user;
        try {
            //1.定义SQL语句
            String sql="select * from tab_user where username=? and password=?";
            //2.返回一个对象
            user=template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),username,password);
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    /* *
        * @MethodName: updateUserInfo
        * @Description: 更新用户的修改数据
        * @Params: [uid, newUser]
        * @Return: void
     */
    @Override
    public void updateUserInfo(int uid, User newUser) {
        String sql="update tab_user set username=?, email=?,name=?,telephone=?,sex=? where uid=?";
        template.update(sql,newUser.getUsername(),
                newUser.getEmail(),
                newUser.getName(),
                newUser.getTelephone(),
                newUser.getSex(),uid);

    }

    /* *
        * @MethodName: findByUid
        * @Description: 通过一个uid,来找出用户对象
        * @Params: [uid]
        * @Return: cn.yunfeng.travel.domain.User
     */
    @Override
    public User findByUid(int uid) {
        String sql="select * from tab_user where uid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),uid);
    }

    /* *
        * @MethodName: updateImage
        * @Description: 更新图片信息到数据库中
        * @Params: [uid, filename]
        * @Return: void
     */
    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    @Override
    public void updateImage(int uid, String filename) {
        String sql="update tab_user set uimage=? where uid=?";
        template.update(sql,filename,uid);
    }

}
