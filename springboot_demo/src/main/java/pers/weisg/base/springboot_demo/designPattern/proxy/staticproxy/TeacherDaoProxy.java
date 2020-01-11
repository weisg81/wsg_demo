package pers.weisg.base.springboot_demo.designPattern.proxy.staticproxy;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/27 0027
 */
public class TeacherDaoProxy implements ITeacherDao {
    private ITeacherDao target;//目标对象，通过接口来聚合

    public TeacherDaoProxy(ITeacherDao iTeacherDao) {
        this.target = iTeacherDao;
    }

    @Override
    public void teach() {
        System.out.println("开始代理  完成某些操作。。。。。");
        target.teach();
        System.out.println("提交。。。。。");
    }
}
