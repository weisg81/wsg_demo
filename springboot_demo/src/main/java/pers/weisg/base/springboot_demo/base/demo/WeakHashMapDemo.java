package pers.weisg.base.springboot_demo.base.demo;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/28 0028
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println();
        myWeakHashMap();
    }
    private static void myWeakHashMap(){
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = new String("WeakHashMap");
        map.put(key, value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map+"\t"+map.size());
    }
    private static void myHashMap(){
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = new String("HashMap");
        map.put(key, value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map+"\t"+map.size());
    }
}
