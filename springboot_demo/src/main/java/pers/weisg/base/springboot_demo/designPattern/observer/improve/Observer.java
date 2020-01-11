package pers.weisg.base.springboot_demo.designPattern.observer.improve;

/**
 * @author weisg
 * @description 观察者接口，有观察者来实现
 * @date 2019/12/27 0027
 */
public interface Observer {

    void update(float temperature, float pressure, float humidity);
}
