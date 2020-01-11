package pers.weisg.base.springboot_demo.designPattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author weisg
 * @description TODO
 * @date 2020/1/6 0006
 */

public class InfoCollege implements College {

    List<Department> departmentList;


    public InfoCollege() {
        departmentList = new ArrayList<Department>();
        addDepartment("信息安全专业", " 信息安全专业 ");
        addDepartment("网络安全专业", " 网络安全专业 ");
        addDepartment("服务器安全专业", " 服务器安全专业 ");
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "信息工程学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        // TODO Auto-generated method stub
        Department department = new Department(name, desc);
        departmentList.add(department);
    }

    @Override
    public Iterator createIterator() {
        // TODO Auto-generated method stub
        return new InfoColleageIterator(departmentList);
    }

}
