package pers.weisg.base.springboot_demo.designPattern.decorator;

import java.io.*;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/26 0026
 */
public class IoDecorator {

    protected volatile InputStream in;

    protected IoDecorator(InputStream inputStream){
        this.in = inputStream;
    }

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub

        //说明
        //1. InputStream 是抽象类, 类似我们前面讲的 Drink
        //2. FileInputStream 是  InputStream 子类，类似我们前面的 DeCaf, LongBlack
        //3. FilterInputStream  是  InputStream 子类：类似我们前面 的 Decorator 修饰者
        //4. DataInputStream 是 FilterInputStream 子类，具体的修饰者，类似前面的 Milk, Soy 等
        //5. FilterInputStream 类 有  protected volatile InputStream in; 即含被装饰者
        //6. 分析得出在jdk 的io体系中，就是使用装饰者模式

        DataInputStream dis = new DataInputStream(new FileInputStream("d:\\abc.txt"));
        InputStream dis2 = new DataInputStream(new FileInputStream("d:\\abc.txt"));
        //new FilterInputStream(new FileInputStream(""));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(""));
        bufferedInputStream.read();
        BufferedReader bufferedReader ;
        System.out.println(dis.read());
        System.out.println(dis2.read());
        dis.close();
    }
}
