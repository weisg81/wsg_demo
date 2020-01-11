package pers.weisg.base.springboot_demo.designPattern.template.improve;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/30 0030
 */
public class PureSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        //空实现
    }

    @Override
    boolean customerWantCondiments() {
        return false;
    }
}
