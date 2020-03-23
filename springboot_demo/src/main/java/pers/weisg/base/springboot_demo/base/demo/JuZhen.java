package pers.weisg.base.springboot_demo.base.demo;

import java.util.Date;

/**
 * Description 矩阵相乘最重要的方法是一般矩阵乘积。它只有在第一个矩阵的列数（column）和第二个矩阵的行数（row）相同时才有意义
 * Author WEISANGENG
 * Date 2020/3/21
 **/
public class JuZhen {

    private Date date = null;
    private String[] strs = null;

    public void testJstack(){
        date = new Date();
        strs = new String[350*1024];
        System.out.println("--------------------------");
        System.out.println(date);
        /*try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }



    public static void main(String args[]) {
        JuZhen juZhen = new JuZhen();
        boolean f = true;
        while(f){
            juZhen.testJstack();
        }


        System.out.println("==========================");
        System.out.println(juZhen.date);

    }


    public static void testJuZhen(){
        /**
         * 行*列相加
         * 第一行
         * 2*1+3*5+4*2
         * 2*5+3*9+4*7
         * 2*2+3*10+4*(-5)
         * 2*8+3*(-3)+4*(-18)
         * 第二行。。。
         *
         *
         */
        int[][] a = {{2, 3, 4}, {4, 6, 5}};
        int[][] b = {{1, 5, 2, 8}, {5, 9, 10, -3}, {2, 7, -5, -18}};
        int[][] c = new int[2][4];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                c[i][j] = 0;
                for (int k = 0; k < a[i].length; k++)
                    c[i][j] += a[i][k] * b[k][j];
                System.out.print(c[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
