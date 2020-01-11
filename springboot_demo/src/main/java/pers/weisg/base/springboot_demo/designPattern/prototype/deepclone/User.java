package pers.weisg.base.springboot_demo.designPattern.prototype.deepclone;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/25 0025
 */
public class User {

    private String userNo;
    private String userName;

    private Integer age;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
