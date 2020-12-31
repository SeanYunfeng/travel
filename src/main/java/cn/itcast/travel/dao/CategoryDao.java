package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @ClassName: CategoryDao
 * @Author： 云峰
 * @Description： CategoryDao的抽象类
 * @Create： 2020--12--15  16:37
 */
public interface CategoryDao {
    /* *
        * @MethodName: findAll
        * @Description: 查出所用的分类信息
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Category>
     */
    List<Category> findAll();
}
