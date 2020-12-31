package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: CategoryServiceImpl
 * @Author： 云峰
 * @Description： 对Category表查出的数据进行操作
 * @Create： 2020--12--15  16:38
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao  categoryDao=new CategoryImpl();
    /* *
        * @MethodName: findAll
        * @Description:查出所有的分类信息
        * @Params: []
        * @Return: java.util.List<cn.itcast.travel.domain.Category>
     */
    @Override
    public List<Category> findAll() {
        //1.从redis数据库中取值
        //1.1 创建jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2 查询sortedest中的分数和cname
        Set<Tuple> categories = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cgs=null;
        //2.判断redis是否可以查出数值
        //2.1 查不出数据
        if(categories==null || categories.size()==0){
            cgs=categoryDao.findAll();
            for (Category cg : cgs) {
                jedis.zadd("category",cg.getCid(),cg.getCname());
            }
        }else{//2.2 查得出数据
            cgs=new ArrayList<Category>();
            for (Tuple tuple:categories) {
                Category category = new Category();
                category.setCid((int)tuple.getScore());
                category.setCname(tuple.getElement());
                cgs.add(category);
            }
        }
        return cgs;
    }
}
