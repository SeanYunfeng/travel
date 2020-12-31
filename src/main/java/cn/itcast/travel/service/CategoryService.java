package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @ClassName: CategoryService
 * @Author： 云峰
 * @Description： CategoryService的抽象类
 * @Create： 2020--12--15  16:38
 */
public interface CategoryService {
    /* *
        * @MethodName: findAll
        * @Description:查出所有的分类信息
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Category>
     */
    List<Category> findAll();

}
