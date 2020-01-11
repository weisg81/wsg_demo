package pers.weisg.base.springboot_demo.designPattern.prototype.deepclone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Client {

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        DeepProtoType p = new DeepProtoType();
        p.name = "宋江";
        p.deepCloneableTarget = new DeepCloneableTarget("大牛", "小牛");

        //方式1 完成深拷贝

//		DeepProtoType p2 = (DeepProtoType) p.clone();
//
//		System.out.println("p.name=" + p.name + "p.deepCloneableTarget=" + p.deepCloneableTarget.hashCode());
//		System.out.println("p2.name=" + p.name + "p2.deepCloneableTarget=" + p2.deepCloneableTarget.hashCode());

        //方式2 完成深拷贝
        DeepProtoType p2 = (DeepProtoType) p.deepClone();

        System.out.println("p.name=" + p.name + "p.deepCloneableTarget=" + p.deepCloneableTarget.hashCode());
        System.out.println("p2.name=" + p.name + "p2.deepCloneableTarget=" + p2.deepCloneableTarget.hashCode());
        System.out.println("-------------------------------------------");

        DeepProtoType deepProtoType = deepClone(p2);

        System.out.println("p.name=" + p.name + "p.deepCloneableTarget=" + p.deepCloneableTarget.hashCode());
        System.out.println("deepProtoType.name=" + p.name + "deepProtoType.deepCloneableTarget=" + deepProtoType.deepCloneableTarget.hashCode());




    }

    /**
     * 泛型方法 深拷贝
     * @param obj
     * @param <T>
     * @return
     */
    public static <T extends Serializable> T deepClone(T obj){
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;

        T cloneObj = null;

        try {
            //序列化
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);//当前对象以对象流的方式输出

            //反序列化 分配内存，写入原始对象，生成新对象
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cloneObj = (T)objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(byteArrayOutputStream != null){
                    byteArrayOutputStream.close();
                }
                if(byteArrayInputStream != null){
                    byteArrayInputStream.close();
                }
                if(objectOutputStream != null){
                    objectOutputStream.close();
                }
                if(objectInputStream != null){
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return cloneObj;

    }

}
