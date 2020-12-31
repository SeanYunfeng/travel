package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName: CategoryServlet
 * @Author： 云峰
 * @Description： 从前端收取关于Category的数据,然后调用方法
 * @Create： 2020--12--15  13:10
 */
@WebServlet("/Category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService=new CategoryServiceImpl();
    /* *
        * @MethodName: findAll
        * @Description:查出所有的分类信息
        * @Params: [request, response]
        * @Return: void
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> info = categoryService.findAll();
        if(info!=null){//返回信息有值
            writeValue(info,response);
        }
    }
}
