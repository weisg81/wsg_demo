package pers.weisg.base.springboot_demo.base.demo;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/28 0028
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        stackOverflowError();//java.lang.StackOverflowError
    }

    private static void stackOverflowError(){
        stackOverflowError();
    }
}
