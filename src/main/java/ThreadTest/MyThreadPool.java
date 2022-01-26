package ThreadTest;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Summer
 * @program: ThreadTest
 * @description 使用线程池
 * @create 2022-01-2022/1/26 11:28
 **/
public class MyThreadPool {
    public static void main(String[] args) {
        ThreadFactory threadPool = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                2,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(20),
                threadPool,
                new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(new Num1());
        threadPoolExecutor.execute(new Num2());
        threadPoolExecutor.shutdown();
    }
}

class Num1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }

        }
    }
}

class Num2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}