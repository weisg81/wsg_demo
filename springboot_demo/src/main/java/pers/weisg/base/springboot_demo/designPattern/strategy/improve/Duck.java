package pers.weisg.base.springboot_demo.designPattern.strategy.improve;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/30 0030
 */
public abstract class Duck {

    //属性, 策略接口
    protected FlyBehavior flyBehavior;
    //其它属性<->策略接口
    protected QuackBehavior quackBehavior;

    public Duck() {
    }

    public abstract void display();//显示鸭子信息

    public void quack() {
        System.out.println("鸭子嘎嘎叫~~");
    }

    public void swim() {
        System.out.println("鸭子会游泳~~");
    }

    public void fly() {
        //改进
        if(flyBehavior != null){
            flyBehavior.fly();
        }
    }

    public FlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public QuackBehavior getQuackBehavior() {
        return quackBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
