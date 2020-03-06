package pers.weisg.base.springboot_demo.base.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Description 这个类作用是什么
 * Author WEISANGENG
 * Date 2020/2/27
 **/
public class MTest {

    /**
     * 给定一个正整数,编写程序计算有多少对质数的和等于输入的这个正整数,并输出结果。
     */
    public static int getZhishuDuiShu(int n) {
        int sum = 0;
        if (n <= 2) {
            return 0;
        }
        for (int i = 2; i <= n / 2; i++) {//在检查质数的时候同时检查后面的一半
            if (isZhiShu(i) && isZhiShu(n - i)) {
                sum++;
                System.out.println("(" + i + "," + (n - i) + ")");
            }
        }
        return sum;
    }

    public static boolean isZhiShu(int n) {
        int sqrt = (int) Math.sqrt(n);//求一半的进行比较就好了
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串反过来
     */
    public static void reverseStr(String str) {
        //byte[] bytes = str.getBytes();
        StringBuffer reverse = new StringBuffer(str).reverse();
        System.out.println(reverse);
    }

    /**
     * 问题：有m个苹果和n个篮子，有几种放法？篮子可以为空（131和113是一种放法）
     * 分析：当m=0,或者n=1时候，只有一种放法，篮子只有一个，
     * 当n=0,记作0种放法
     * 当苹果数小于篮子数时候，即m<n时，放法和m个苹果，m个篮子的情况时相等
     * 当苹果数大于或者等于篮子数时候，又可以分为两种情况
     * 一种是篮子都满的时候，一种是至少有一个空篮子的时候
     */
    public static int zhuhePaixun(int m, int n) {
        if (n == 0) {
            return 0;
        }
        if (m == 0 || n == 1) {
            //System.out.println();
            return 1;
        }
        //苹果数小于篮子数，如2个苹果放5个篮子，和2个苹果放2个篮子的放法是一样的
        if (m < n) {
            return zhuhePaixun(m, m);
        } else if (m >= n) {
            //苹果数大于等于篮子数,分为两种情况
            //情况一：每个篮子里面都放有一个苹果，决定放法数的就是剩余苹果在n个篮子的放法数篮子里都有苹果，
            //  如果每个篮子都有苹果，则每个篮子各取出1个苹果，对结果没有影响 F(m,n)=F(m−n,n)
            //情况二：至少有一个空篮子，即m个苹果在n-1个篮子的情况下的方法F(m,n)=F(m,n−1)
            return zhuhePaixun(m, n - 1) + zhuhePaixun(m - n, n);
        }
        return 0;
    }

    /**
     * 判断两个字符串是否是同构的（isIsomorphic）
     */
    public static boolean isIsomorphic(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            if (map.containsKey(s1.charAt(i))) {
                if (map.get(s1.charAt(i)) != s2.charAt(i)) {
                    return false;
                }
            } else {
                map.put(s1.charAt(i), s2.charAt(i));
            }
        }
        return true;
    }

    /**
     * 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，
     * 小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
     */
    public static void fibonacci(int f1, int f2, int m) {
        if (m < 3) {
            System.out.println(1);
        }
        int f;
        for (int i = 3; i <= m; i++) {
            f = f2;
            f2 = f1 + f2;
            f1 = f;
            System.out.println(f2);
        }
    }

    /**
     * 题目：打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身。
     * 例如：153是一个 "水仙花数 "，因为153=1的三次方＋5的三次方＋3的三次方。
     */
    public static void shuixianhua() {

        int a, b, c;
        for (int i = 101; i < 1000; i++) {
            a = i % 10;
            b = i / 10 % 10;
            c = i / 100;
            if (a * a * a + b * b * b + c * c * c == i) {
                System.out.println(i);
            }
        }
    }

    /**
     * 题目：一个数如果恰好等于它的因子之和，
     * 这个数就称为 "完数 "。例如6=1＋2＋3.编程     找出1000以内的所有完数。
     */
    public static void yinzifen() {
        for (int i = 1; i <= 1000; i++) {
            int t = 0;
            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0) {
                    t += j;
                }
            }
            if (t == i) {
                System.out.println(i);
            }
        }
    }

    /**
     * 题目：两个乒乓球队进行比赛，各出三人。甲队为a,b,c三人，乙队为x,y,z三人。
     * 已抽签决定比赛名单。有人向队员打听比赛的名单。a说他不和x比，c说他不和x,z比，请编程序找出三队赛手的名单。
     */
    public static void abcxyz() {

        for (char a = 'x'; a <= 'z'; a++) {
            for (char b = 'x'; b <= 'z'; b++) {
                if (a != b) {   //避免参赛队员重复比赛
                    for (char c = 'x'; c <= 'z'; c++) {
                        if (a != c && b != c) {      //避免参赛队员重复比赛
                            if (a != 'x' && c != 'x' && c != 'z') {   //根据题意判断
                                System.out.println("a和" + a + "，b和" + b + "，c和" + c + "进行比赛");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 题目：猴子吃桃问题：猴子第一天摘下若干个桃子，当即吃了一半，还不瘾，又多吃了一个
     * 第二天早上又将剩下的桃子吃掉一半，又多吃了一个。
     * 以后每天早上都吃了前一天剩下的一半零一个。到第10天早上想再吃时，见只剩下一个桃子了。求第一天共摘了多少。
     */
    public static void test17() {
        int x = 1;
        for (int i = 10; i > 1; i--) {
            x = (x + 1) * 2;
        }
        System.out.println(x);
    }

    /**
     * 打印出如下图案（菱形）
     */
    public static void lianxi19() {
        int H = 7, W = 7;//高和宽必须是相等的奇数
        for (int i = 0; i < (H + 1) / 2; i++) {
            for (int j = 0; j < W / 2 - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k < (i + 1) * 2; k++) {
                System.out.print('*');
            }
            System.out.println();
        }
        for (int i = 1; i <= H / 2; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= W - 2 * i; k++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }

    /**
     * 题目：一个5位数，判断它是不是回文数。即12321是回文数，个位与万位相同，十位与千位相同。
     */
    public static boolean ver(int num) {
        if (num < 0 || (num != 0 && num % 10 == 0))
            return false;
        int ver = 0;
        while (num > ver) {
            ver = ver * 10 + num % 10;
            num = num / 10;
        }
        return (num == ver || num == ver / 10);
    }

    /**
     * 题目：打印出杨辉三角形（要求打印出10行如下图）
     */
    public static void lianxi33() {
        int[][] a = new int[10][10];
        for (int i = 0; i < 10; i++) {
            a[i][i] = 1;
            a[i][0] = 1;
        }
        for (int i = 2; i < 10; i++) {
            for (int j = 1; j < i; j++) {
                a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 2 * (10 - i) - 1; k++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(a[i][j] + "   ");
            }
            System.out.println();
        }
    }

    /**
     * 实现十进制数转十六进制数
     */
    public static String intToHex(int n) {
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            s = s.append(b[n % 16]);
            n = n / 16;
        }
        a = s.reverse().toString();
        return a;
    }

    //写出我们的kmp搜索算法

    /**
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表, 是子串对应的部分匹配表
     * @return 如果是-1就是没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要处理 str1.charAt(i) ！= str2.charAt(j), 去调整j的大小
            //KMP算法核心点, 可以验证...
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {//找到了 // j = 3 i
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串(子串) 的部分匹配值表
    public static int[] kmpNext(String dest) {
        //创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串是长度为1 部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) ，我们需要从next[j-1]获取新的j
            //直到我们发现 有  dest.charAt(i) == dest.charAt(j)成立才退出
            //这时kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            //当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    //二分查找的非递归实现

    /**
     * @param arr    待查找的数组, arr是升序排序
     * @param target 需要查找的数
     * @return 返回对应下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) { //说明继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;//需要向左边查找
            } else {
                left = mid + 1; //需要向右边查找
            }
        }
        return -1;
    }


    public static void main(String[] args) {

        //System.out.println(getZhishuDuiShu(1000));
        //reverseStr("abcdefghijk");

        //System.out.println(zhuhePaixun(8, 5));

        /*System.out.println(isIsomorphic("egg", "add"));
        System.out.println(isIsomorphic("foo", "bar"));
        System.out.println(isIsomorphic("paper", "title"));*/

        //fibonacci(1,1,4);
        //shuixianhua();
        //System.out.println((7/2));
        //abcxyz();

        //test17();
        //lianxi19();
        //System.out.println(ver(12321));

        //lianxi33();

        /*byte b = 10;   // 二进制表示00001010
        //*********Found**********
        byte c = 0X000f;
        b = (byte) (b ^ c);
        //*********Found**********
        System.out.println("b的结果是：" + b);*/

        //System.out.println(intToHex(987));

        //String str1 = "BBC ABCDAB ABCDABCDABDE";
        /*StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10000000; i++) {
            sb.append("ABCDAB" + i);
        }
        sb.append("ABCDABCDABDE");
        String str1 = sb.toString();
        String str2 = "ABCDABD";

        long startTime = System.currentTimeMillis();
        int[] next = kmpNext("ABCDABD"); //[0, 1, 2, 0]

        int index = kmpSearch(str1, str2, next);
        long endTime = System.currentTimeMillis();
        System.out.println("index=" + index); // 15了


        System.out.println("kmp cost " + (endTime - startTime) + " ms");
        long startTime2 = System.currentTimeMillis();
        System.out.println(str1.indexOf(str2));
        long endTime2 = System.currentTimeMillis();
        System.out.println("cost " + (endTime2 - startTime2) + " ms");*/

        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 100);
        System.out.println("index=" + index);//

    }


}
