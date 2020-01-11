package pers.weisg.base.springboot_demo.designPattern.observer.improve;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/27 0027
 */
public class CurrentConditions implements Observer{

    // 温度，气压，湿度
    private float temperature;
    private float pressure;
    private float humidity;


    // 显示
    public void display() {
        System.out.println("***Today mTemperature: " + temperature + "***");
        System.out.println("***Today mPressure: " + pressure + "***");
        System.out.println("***Today mHumidity: " + humidity + "***");
    }
    // 更新 天气情况，是由 WeatherData 来调用，我使用推送模式
    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }
}
