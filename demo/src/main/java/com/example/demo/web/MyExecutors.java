package com.example.demo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 线程池核心点：复用机制
 * 1.提前创建好固定的线程一直在运行状态---死循环实现（线程run方法用while死循环）
 * 2.提交的线程任务缓存到一个并发队列集合中，交给我们正在运行的线程执行
 * 3.正在运行的线程就从队列中获取改任务执行
 */
public class MyExecutors {
    private List<WorkThread> workThreads;
    private LinkedBlockingDeque<Runnable> BlockingDeque;

    private boolean isRun=true;
    /**
     * 最大线程数
     *
     * @param maxThreadCount
     */
    public MyExecutors(int maxThreadCount,int dequeSize) {
        BlockingDeque=new LinkedBlockingDeque<Runnable>(dequeSize);
        //提前创建好固定的线程一直在运行状态---死循环实现（线程run方法用while死循环）
        workThreads=   new ArrayList<WorkThread>(maxThreadCount);
        for (int i = 0; i < maxThreadCount; i++) {
            new WorkThread().start();
        }

    }
    class WorkThread extends Thread {
        @Override
        public void run() {
            while (isRun||BlockingDeque.size()>0) {
                Runnable runnable = BlockingDeque.poll();
                if (runnable!=null){
                    runnable.run();
                }
            }
        }
    }
    public boolean Execute(Runnable commnd){
        //在不违反容量限制的情况下立即将指定的元素插入此队列中
        return BlockingDeque.offer(commnd);
    }

    public static void main(String[] args) {
        MyExecutors myExecutors=new MyExecutors(2,20);
        for (int i = 0; i < 10; i++) {
            final int finalI=i;
            myExecutors.Execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"===="+finalI);
                }
            });

        }
        myExecutors.isRun=false;
    }
}


