package pers.weisg.base.springboot_demo.designPattern.adapter.interfaceadapter;

import pers.weisg.base.springboot_demo.designPattern.adapter.objectadapter.Phone;
import pers.weisg.base.springboot_demo.designPattern.adapter.objectadapter.Voltage220V;
import pers.weisg.base.springboot_demo.designPattern.adapter.objectadapter.VoltageAdapter;

public class Client {

    public static void main(String[] args) {
        AbsAdapter absAdapter = new AbsAdapter() {
            //只需要去覆盖我们 需要使用 接口方法
            @Override
            public void m1() {
                System.out.println("使用了m1的方法");
            }
        };
        absAdapter.m1();
    }

}
