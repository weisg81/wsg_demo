package pers.weisg.base.springboot_demo.designPattern.decorator;

public class Soy extends Decorator{

    public Soy(Drink obj) {
        super(obj);
        // TODO Auto-generated constructor stub
        setDes(" 豆浆  ");
        setPrice(2.5f);
    }

}
