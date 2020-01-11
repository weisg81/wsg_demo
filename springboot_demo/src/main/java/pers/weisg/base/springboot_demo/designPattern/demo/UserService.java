package pers.weisg.base.springboot_demo.designPattern.demo;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/19 0019
 */
public interface UserService {

    String getUserNameById(Long userId);

    Object getUserInfoByUserNo(String userNo);
}
