package pers.weisg.base.springboot_demo.netty.protocoltcp;

import lombok.Data;

/**
 * @author weisg
 * @description 协议包
 * @date 2019/12/12 0012
 */
@Data
public class MessageProtocol {

    private int len; //关键
    private byte[] content;
}
