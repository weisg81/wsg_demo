package pers.weisg.base.springboot_demo.designPattern.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/20 0020
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OrderService orderService;
    @Override
    public String getUserNameById(Long userId) {
        //orderService.getOrderNoByOrderId(1L);
        return null;
    }

    @Override
    public Object getUserInfoByUserNo(String userNo) {
        return null;
    }
}
