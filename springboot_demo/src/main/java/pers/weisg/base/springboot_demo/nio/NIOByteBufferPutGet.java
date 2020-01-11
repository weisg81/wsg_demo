package pers.weisg.base.springboot_demo.nio;

import java.nio.ByteBuffer;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/3 0003
 */
public class NIOByteBufferPutGet {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byteBuffer.putInt(111);
        byteBuffer.putLong(9L);
        byteBuffer.putChar('三');
        byteBuffer.putShort((short)55);
        //取出
        byteBuffer.flip();
        System.out.println("================================");
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());

        System.out.println(byteBuffer.getShort());

        System.out.println(byteBuffer.getChar());
    }
}
