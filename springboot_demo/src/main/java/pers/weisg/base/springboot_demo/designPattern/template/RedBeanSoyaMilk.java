package pers.weisg.base.springboot_demo.designPattern.template;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/30 0030
 */
public class RedBeanSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        System.out.println(" 加入上好的红豆 ");
    }
}
