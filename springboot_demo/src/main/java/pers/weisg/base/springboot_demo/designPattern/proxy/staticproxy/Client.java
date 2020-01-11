package pers.weisg.base.springboot_demo.designPattern.proxy.staticproxy;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/27 0027
 */
public class Client {
    public static void main(String[] args) {
        //创建目标对象(被代理对象)
        ITeacherDao teacherDao = new TeacherDao();

        //创建代理对象, 同时将被代理对象传递给代理对象
        TeacherDaoProxy teacherDaoProxy = new TeacherDaoProxy(teacherDao);

        //通过代理对象，调用到被代理对象的方法
        //即：执行的是代理对象的方法，代理对象再去调用目标对象的方法
        teacherDaoProxy.teach();
    }
}
