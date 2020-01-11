package pers.weisg.base.springboot_demo.base.demo;

import java.util.Random;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/28 0028
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args) {
        String str = "weisg";
        //java.lang.OutOfMemoryError: Java heap space
        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseParallelGC
        while(true){
            str += str+ new Random().nextInt(999999999)+new Random().nextInt(88888888);
            str.intern();
        }
    }
}
