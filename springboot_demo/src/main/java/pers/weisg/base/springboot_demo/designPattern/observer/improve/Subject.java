package pers.weisg.base.springboot_demo.designPattern.observer.improve;


/**
 * @author weisg
 * @description 接口, 让WeatherData 来实现
 * @date 2019/12/27 0027
 */
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
