package pers.weisg.base.springboot_demo.designPattern.proxy.cglib;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/27 0027
 */
public class TeacherDao {

    public String teach() {
        System.out.println(" 老师授课中  ， 我是cglib代理，不需要实现接口 ");
        return "hello";
    }
}
