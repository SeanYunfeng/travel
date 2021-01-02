package cn.yunfeng.travel.dao;

import cn.yunfeng.travel.domain.Seller;

/**
 * @ClassName: SellerDao
 * @Author： 云峰
 * @Description： SellerDao抽象类
 * @Create： 2020--12--17  10:06
 */
public interface SellerDao {
    /**
        * @MethodName: findBySid
        * @Description:通过一个sid 查出一个seller对象
        * @Params: [sid]
        * @Return: cn.yunfeng.travel.domain.Seller
     */
    public Seller findBySid(int sid);
}
