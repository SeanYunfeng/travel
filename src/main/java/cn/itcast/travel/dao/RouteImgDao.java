package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @ClassName: RouteImgDao
 * @Author： 云峰
 * @Description： RouteImgDao的抽象类
 * @Create： 2020--12--17  9:58
 */
public interface RouteImgDao {
    /**
        * @MethodName: findByRid
        * @Description: 通过一个rid查出一个图片的list
        * @Params: [rid]
        * @Return: java.util.List<cn.itcast.travel.domain.RouteImg>
     */
    public List<RouteImg> findByRid(int rid);
}
