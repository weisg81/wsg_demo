package pers.weisg.base.springboot_demo.base.demo;

import com.alibaba.fastjson.JSONObject;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/11 0011
 */
public class JosephuDemo {
    public static void main(String[] args) throws InterruptedException {
        Josephu josephu = new Josephu();
        josephu.countNo(5,12,10);
    }
}

class Josephu{
    public People first;

    //添加n个元素形成环状
    public void addPeople(int num){
        if(num<1){
            System.out.println("添加数目不正确");
            return;
        }
        People cur=null;
        for (int i = 1; i <= num; i++) {
            People people = new People(i);
            if(i==1){
                first=people;
            }else {
                cur.next=people;
            }
            people.next=first;
            cur=people;
        }
    }

    // 统计出圈的编号

    public void countNo(int startNo,int outNum,int totalNum) throws InterruptedException {
        if(startNo<1 || startNo>totalNum || totalNum<1){
            System.out.println("参数不对");
            return;
        }
        Josephu josephu = new Josephu();
        josephu.addPeople(totalNum);
        josephu.show();
        // 首先创建一个辅助指针，指向最后一个元素，first指向第一个元素
        People helper = null;
        People tmp = josephu.first;
        while(tmp.next!=josephu.first){
            tmp=tmp.next;
        }
        helper=tmp;
        System.out.println("-----------------------");
        //System.out.println(JSONObject.toJSONString(josephu.first));
        System.out.println(josephu.first.no);

        // 根据起始报数startNo,将first和helper整体向后移动startNo-1个位置
        for (int i = 0; i < startNo-1; i++) {
            josephu.first=josephu.first.next;
            helper=helper.next;
        }
        josephu.show();

        // 根据现有圈的情况开始报数，报道outNum的人出圈，报到outNum的人距离现有first的差距是移动outNum-1,报圈结束的标志是first=helper
        while (josephu.first!=helper){
            for (int i = 0; i < outNum-1; i++) {
                josephu.first=josephu.first.next;
                helper=helper.next;
            }
            System.err.print(josephu.first.no+">>");
            josephu.first=josephu.first.next;
            helper.next=josephu.first;
        }
        System.err.println(josephu.first.no);
    }

    public void show() throws InterruptedException {
        if(first!=null){
            People tmp = first;
            while(tmp.next!=first){
                System.err.print(tmp.no+">>");
                Thread.sleep(1L);
                tmp=tmp.next;
            }
            System.err.println(tmp.no);
        }
    }
}

class People{
    public int no;
    public People next;

    public People(int no) {
        this.no = no;
    }


}