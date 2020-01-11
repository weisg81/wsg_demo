package pers.weisg.base.springboot_demo.designPattern.adapter.objectadapter;

//适配器类
public class VoltageAdapter implements IVoltage5V {

    // 关联关系-聚合
    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        // TODO Auto-generated method stub
        //获取到220V电压
        int srcV = voltage220V.output220V();
        int dstV = srcV / 44 ; //转成 5v
        return dstV;
    }

}

