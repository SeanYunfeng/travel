package cn.yunfeng.travel.service;

import cn.yunfeng.travel.domain.User;

/**
 * @ClassName: UserService
 * @Author： 云峰
 * @Description： UserService的抽象类
 * @Create： 2020--12--13  18:51
 */
public interface UserService {
    /*
     * @MethodName: register
     * @Description:注册用户
     * @Params: [user]
     * @Return: boolean
     */
    public boolean register(User user);

    /* *
        * @MethodName: active
        * @Description: 判断用户是否激活
        * @Params: [code]
        * @Return: boolean
     */
    public boolean active(String code);

    /*  *
     * @MethodName: active
     * @Description: 判断数据库存在该用户
     * @Params: [code]
     * @Return: boolean
     */
    public User login(User user);

    /* *
        * @MethodName: updateUserInfo
        * @Description: 更新用户的数据
        * @Params: [uid, newUser]
        * @Return: cn.yunfeng.travel.domain.User
     */
    public User updateUserInfo(int uid, User newUser);
    /* *
        * @MethodName: updateImage
        * @Description:  将图片信息保存到数据库中
        * @Params: [uid, filename]
        * @Return: void
     */
    public void updateImage(int uid, String filename);

    /* *
        * @MethodName: findByUid
        * @Description: 通过一个uid来找出一个User对象
        * @Params: [uid]
        * @Return: cn.yunfeng.travel.domain.User
     */
    public User findByUid(int uid);
}
