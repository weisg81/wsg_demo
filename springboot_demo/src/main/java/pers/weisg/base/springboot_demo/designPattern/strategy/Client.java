package pers.weisg.base.springboot_demo.designPattern.strategy;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/30 0030
 */
public class Client {
    public static void main(String[] args) {
        Duck duck = new PekingDuck();
        duck.display();
        duck.fly();
        duck.quack();
        duck.swim();
    }
}
