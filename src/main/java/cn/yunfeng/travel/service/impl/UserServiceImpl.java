package cn.yunfeng.travel.service.impl;
import cn.yunfeng.travel.dao.UserDao;
import cn.yunfeng.travel.dao.impl.UserDaoImpl;
import cn.yunfeng.travel.domain.User;
import cn.yunfeng.travel.service.UserService;
import cn.yunfeng.travel.util.MailUtils;
import cn.yunfeng.travel.util.UuidUtil;

/**
 * @ClassName: UserServiceImpl
 * @Author： 云峰
 * @Description： 对User表中查出的数据的操作
 * @Create： 2020--12--13  18:51
 */

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
     /*
         * @MethodName: register
         * @Description:注册用户
         * @Params: [user]
         * @Return: boolean
      */
    public boolean register(User user) {
        User byUserName = userDao.findByUserName(user.getUsername());
        if(byUserName!=null){
            //1.用户名存在，注册失败
            return false;
        }
        //2.保存用户信息
        //2.1设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //2.2设置激活状态
        user.setStatus("N");
        userDao.saveUser(user);
        //3.激活邮件发送，邮件正文
        String content="<a href='http://localhost:8080/travel/User/activeUser?code="+user.getCode()+"'>" +
                "点击激活{黑马旅游网}</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        return true;
    }
    /*  *
        * @MethodName: active
        * @Description: 判断是否激活成功
        * @Params: [code]
        * @Return: boolean
     */
    @Override
    public boolean active(String code) {
        //1.根据激活码查询对象
        User user=userDao.findByCode(code);
        if(user!=null){
            //2. 修改用户的对象
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }
    }

    /* *
        * @MethodName: login
        * @Description: 判断登录是否成功
        * @Params: [user]
        * @Return: cn.yunfeng.travel.domain.User
     */
    @Override
    public User login(User user) {
        return userDao.findByUserNameAndPassword(user.getUsername(),user.getPassword());
    }

    /* *
        * @MethodName: updateUserInfo
        * @Description: 修改用户的信息
        * @Params: [uid, newUser]
        * @Return: cn.yunfeng.travel.domain.User
     */
    @Override
    public User updateUserInfo(int uid, User newUser) {
        //1.调用UserDao里面的updateUserInfo
        userDao.updateUserInfo(uid,newUser);
        //2.调用UserDao里面的findByUid
        return userDao.findByUid(uid);
    }

    /* *
        * @MethodName: updateImage
        * @Description: 将更新的图片信息保存到数据库中
        * @Params: [uid, filename]
        * @Return: void
     */
    @Override
    public void updateImage(int uid, String filename) {
        //1.调用userDao里面的updateImage
        userDao.updateImage(uid,filename);

    }

    /* *
        * @MethodName: findByUid
        * @Description: 根据一个Uid来找出一个User对象
        * @Params: [uid]
        * @Return: cn.yunfeng.travel.domain.User
     */
    @Override
    public User findByUid(int uid) {
        return userDao.findByUid(uid);
    }
}
