package ThreadTest;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Summer
 * @program: ThreadTest
 * @description 线程间通信
 * @create 2022-01-2022/1/26 14:09
 **/
public class MyThreadCommunication {
    public static void main(String[] args) {
        MyThreadComm t = new MyThreadComm();
        // 创建一个线程池
        ThreadFactory build = new ThreadFactoryBuilder()
                .setNameFormat("Thread-pool-%d")
                .build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
                2,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                build,
                new ThreadPoolExecutor.AbortPolicy());
        // 使用线程池执行两个线程
        executor.execute(t);
        executor.execute(t);
    }
}


class MyThreadComm implements Runnable {
    private int number = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                this.notify();
                if (number <= 20) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}