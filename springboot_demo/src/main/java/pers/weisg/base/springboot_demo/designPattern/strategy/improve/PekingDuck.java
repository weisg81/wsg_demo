package pers.weisg.base.springboot_demo.designPattern.strategy.improve;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/30 0030
 */
public class PekingDuck extends Duck {
    public PekingDuck() {
        //假如北京鸭可以飞翔，但是飞翔技术一般
        flyBehavior = new BadFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("~~北京鸭~~~");
    }
}
