package pers.weisg.base.springboot_demo.base.demo;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/27 0027
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object object1 = new Object();//这样定义的默认是强引用
        Object object2 = object1;//object2 引用赋值

        object1 = null;//
        System.gc();
        System.out.println(object2);
    }
}
