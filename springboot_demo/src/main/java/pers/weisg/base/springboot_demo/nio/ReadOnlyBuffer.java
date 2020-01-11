package pers.weisg.base.springboot_demo.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/3 0003
 */
public class ReadOnlyBuffer {
    public static void main(String[] args) {
        System.out.println((SelectionKey.OP_READ));
        System.out.println((SelectionKey.OP_ACCEPT));
        System.out.println((SelectionKey.OP_WRITE));
        /*ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        for (int i = 0; i < 64; i++) {
           byteBuffer.put((byte)i);
        }
        byteBuffer.flip();
        //得到一个只读的buffer
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());
        while(readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
        readOnlyBuffer.put((byte) 88);*/

    }
}
