package pers.weisg.base.springboot_demo.designPattern.composite;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/30 0030
 */
public abstract class OrganizationComponent {

    private String name; // 名字
    private String des; // 说明

    public OrganizationComponent(String name, String des) {
        this.name = name;
        this.des = des;
    }

    protected  void add(OrganizationComponent organizationComponent) {
        //默认实现
        throw new UnsupportedOperationException();
    }

    protected  void remove(OrganizationComponent organizationComponent) {
        //默认实现
        throw new UnsupportedOperationException();
    }

    //方法print, 做成抽象的, 子类都需要实现
    protected abstract void print();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
