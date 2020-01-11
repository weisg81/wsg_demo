package pers.weisg.base.springboot_demo.base.demo;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/25 0025
 */
public class T1 {
    private static List<Integer> list = new ArrayList<>();

    /*public static void main(String[] args) {
        //int[] arr = new int[128];

        //FileInputStream inputStream = new FileInputStream();
        baoshuTest();
         *//*List<Integer> list2 = new ArrayList<>();
         list2.add(2);
         list2.add(3);
         list2.add(5);
         list2.add(7);
         list2.add(9);
        System.out.println(list2+",,,"+list2.size());
        list2.remove(2);
        System.out.println(list2+",,,"+list2.size());*//*

    }*/


    public static void baoshuTest(){
        int arr[] = new int[25];
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = i+1;
        }
        System.out.println(Arrays.toString(arr));
        int idx = 3;//从第三个人开始报数

        int flag = 2;//标记，每报到数是2，要重新开始报数

        List<Integer> arrList = new ArrayList<>(arr.length);

        for (int i = 0; i < arr.length - idx; i++) {
            arrList.add(arr[i + idx]);
        }
        for (int i = 0; i < idx; i++) {
            arrList.add(arr[i]);
        }
        System.out.println(arrList);
        int baoshuIdx = 0;
        int indexFlag = 0;
        while(baoshuIdx != -1){
            baoshuIdx = baoshu(arrList, flag, indexFlag);
            if(baoshuIdx == -1){
                break;
            }
            //删除当前数，重新报数
            if(baoshuIdx == (arrList.size()-1)){
                indexFlag = 0;
            }else if(baoshuIdx == 0){
                indexFlag = 0;
            } else {
                indexFlag ++;
            }
            arrList.remove(baoshuIdx);
        }
        System.out.println("-------------------------------------");
        System.out.println(list);

    }
    public static int baoshu(List<Integer> arrList, int flag, int arrIdxs){
        if(arrList == null || arrList.size() == 0){
            return -1;
        }

        if(arrList.size() == 1){
            list.add(arrList.get(0));
            return 0;
        }
        int flags = 1;
        if(arrIdxs == (arrList.size()-1)){

            flags = 2;
            for (int i = 0; i < arrList.size(); i++) {
                if(flags == flag){
                    list.add(arrList.get(i));
                    return i;
                }
                flags ++;
            }
        }else{
            for (int i = arrIdxs; i < arrList.size(); i++) {
                if(flags == flag){
                    list.add(arrList.get(i));
                    return i;
                }
                flags ++;
            }
        }

        return -1;//返回报到对应的标数的是哪个人编号
    }

    public static void baoshuLinkList(){
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < linkedList.size() ; i++) {
            linkedList.add((i+1));
        }

        int idx = 3;//从第三个人开始报数

        int flag = 2;//标记，每报到数是2，要重新开始报数
        System.out.println(linkedList);
        //linkedLis
        
    }

    public static void main(String[] args) {
        baoshuLinkList();
    }

}
