package pers.weisg.base.springboot_demo.base.demo;

import java.util.Arrays;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/22 0022
 */
public class SortDemo {



    public static void main(String[] args) {
        /*int[] arr = {89,23,20,10,8,7,5,4,2};
        bubbleSort(arr);
        System.out.println("====================================");
        int[] arr2 = {89,23,20,10,8,7,5,4,2};
        sleectSort(arr2);*/
        /*System.out.println("====================================");
        int[] arr3 = {81,89,84,88,87,90,8};
        insertSort(arr3);*/

        int[] arr = {30, 40, 60, 10, 20, 50};
        quicksort(arr);
    }

    /**
     * 冒泡排序：从小到大排序
     * @param arr
     */
    private static void bubbleSort(int[] arr){
        if(arr == null){
            return;
        }
        System.out.println("-------------------before sort--------------------");
        System.out.println(Arrays.toString(arr));
        int temp ;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] > arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("-------------------after sort--------------------");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序：从小到大排序
     * @param arr
     */
    private static void sleectSort(int[] arr){
        if(arr == null){
            return;
        }
        System.out.println("-------------------before sort--------------------");
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length-1; i++) {
            int minIdx = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j] < arr[minIdx]){
                    minIdx = j;
                }
            }
            if(minIdx != i){
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
        System.out.println("-------------------after sort--------------------");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序：从小到大排序 可以参考：https://blog.csdn.net/qq_28081081/article/details/80594386
     * @param arr
     */
    private static void insertSort(int[] arr){
        if(arr == null){
            return;
        }
        System.out.println("-------------------before sort--------------------");
        System.out.println(Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];//保存每次需要插入的那个数
            int j ;
            for (j = i; j > 0 && arr[j-1] > temp; j --) {
                arr[j] = arr[j-1];//把大于需要插入的数往后移动。最后不大于temp的数就空出来j
            }
            arr[j] = temp;
        }
        System.out.println("-------------------after sort--------------------");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序：从小到大排序
     * @param arr
     */
    private static void quicksort(int[] arr){
        if(arr == null){
            return;
        }
        System.out.println("-------------------before sort--------------------");
        System.out.println(Arrays.toString(arr));
        sort(arr,0, arr.length -1);

        System.out.println("-------------------before after--------------------");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 可以参考：https://blog.csdn.net/qq_28081081/article/details/80597455
     * 步骤为：
     *
     * 从数列中挑出一个元素，称为"基准"（pivot），
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     * @param arr
     * @param start
     * @param end
     */
    private static void sort(int[] arr, int start, int end){
        if(end <= start){
            return;
        }
        int middle = arr[start];//基准
        int low = start;
        int hight = end;
        while(low < hight){
            //从右到左，找到小于基准数的位置
            while(low < hight && arr[hight] >= middle){
                -- hight;
            }
            arr[low] = arr[hight];//找到小于基准数的值 将该值交换到low的位置
            //从左到右，找到大于基准数的位置
            while(low < hight && arr[low] < middle){
                ++low;
            }
            arr[hight] = arr[low];//找到大于基准数的位置 将该值交换到hight的位置
        }
        arr[low] = middle;
        sort(arr,start, low - 1);
        sort(arr,low + 1, end);
    }
}
