package pers.weisg.base.springboot_demo.designPattern.strategy.improve;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/30 0030
 */
public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println(" 不会飞翔  ");
    }
}
