package pers.weisg.base.springboot_demo.designPattern.iterator;

import java.util.Iterator;

/**
 * @author weisg
 * @description TODO
 * @date 2020/1/6 0006
 */
public interface College {
    public String getName();

    //增加系的方法
    public void addDepartment(String name, String desc);

    //返回一个迭代器,遍历
    public Iterator createIterator();
}
