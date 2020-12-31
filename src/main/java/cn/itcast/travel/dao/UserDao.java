package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @ClassName: UserDao
 * @Author： 云峰
 * @Description： UserDao的抽象类
 * @Create： 2020--12--13  18:52
 */
public interface UserDao {

    /**
     * 通过一个用户名，查出user对象来
     * @MethodName: findByUserName
     * @Description:
     * @Param: [username]
     * @Return: cn.itcast.travel.domain.User
     */
    public User findByUserName(String username);
    /**
     *保存一个用户对象到数据库中
     * @MethodName: saveUser
     * @Description: 保存一个用户对象到数据库中
     * @Params: [user]
     * @Return: void
     */
    public void saveUser(User user);
    /**
     * @MethodName: findByCode
     * @Description: 根据激活码查询用户
     * @Params: [code]
     * @Return: cn.itcast.travel.domain.User
     */
    public User findByCode(String code);
    /**
     * @MethodName: updateStatus
     * @Description:修改用户状态
     * @Params: [user]
     * @Return: void
     */
    public void updateStatus(User user);
    /**
     * @MethodName: findByUserNameAndPassword
     * @Description:根据用户名和密码来返回一个用户
     * @Params: [user]
     * @Return: cn.itcast.travel.domain.User
     */
    public User findByUserNameAndPassword(String username,String password);
    /**
        * @MethodName: updateUserInfo
        * @Description: 更新用户的数据
        * @Params: [uid, newUser]
        * @Return: void
     */
    public void updateUserInfo(int uid, User newUser);

    /**
        * @MethodName: findByUid
        * @Description: 通过一个uid来查找出对象来
        * @Params: [uid]
        * @Return: cn.itcast.travel.domain.User
     */
    public User findByUid(int uid);
    /**
        * @MethodName: updateImage
        * @Description: 更新图片信息到数据库中
        * @Params: [uid, filename]
        * @Return: void
     */
    public void updateImage(int uid, String filename);
}
