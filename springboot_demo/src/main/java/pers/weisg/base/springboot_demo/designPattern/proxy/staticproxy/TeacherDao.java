package pers.weisg.base.springboot_demo.designPattern.proxy.staticproxy;

public class TeacherDao implements ITeacherDao {

    @Override
    public void teach() {
        // TODO Auto-generated method stub
        System.out.println(" 老师授课中  。。。。。");
    }

}
