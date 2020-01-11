package pers.weisg.base.springboot_demo.base.demo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@Data
@ToString
@AllArgsConstructor
class User{
    String userName;
    int age;
}

/**
 * Description 原子引用
 * Author WEISANGENG
 * Date 2019/11/24
 **/
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User user1 = new User("z3",20);
        User user2 = new User("l4",22);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(user1);

        System.out.println(atomicReference.compareAndSet(user1, user2)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(user1, user2)+"\t"+atomicReference.get().toString());

    }
}
