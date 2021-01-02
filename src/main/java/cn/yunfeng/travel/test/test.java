package cn.yunfeng.travel.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName:
 * @Author： 云峰
 * @Description：
 * @Create： 2020--12--31  16:30
 */
public class test {
    public void show(){
        ApplicationContext ap=new ClassPathXmlApplicationContext("applicationContext.xml");
        ap.getBean("");
    }
}
