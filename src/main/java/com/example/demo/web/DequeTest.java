package com.example.demo.web;

import java.util.concurrent.LinkedBlockingDeque;

public class DequeTest {
    public static void main(String[] args) {
        //LinkedBlockingDeque(无界队列)
        //有界和无界的区别：有界对容量有限制的 无界是没有限制 （Integer.MAX_VALUE）
        LinkedBlockingDeque<String> strings=new LinkedBlockingDeque<>();
       // LinkedBlockingDeque<String> strings=new LinkedBlockingDeque<>(2); 设置界限超过两个报错
        strings.add("111");
        strings.add("222");
        strings.add("333");
        System.out.println(strings.poll());
        System.out.println(strings.poll());
        System.out.println(strings.poll());
        System.out.println(strings.poll());


    }
}
