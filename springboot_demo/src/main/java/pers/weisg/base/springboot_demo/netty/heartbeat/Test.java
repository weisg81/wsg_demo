package pers.weisg.base.springboot_demo.netty.heartbeat;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/9 0009
 */
public class Test {
    public static void main(String[] args) throws Exception{
        System.out.println(System.nanoTime()); //纳秒  10亿分之1
        Thread.sleep(1000);
        System.out.println(System.nanoTime());
    }
}
