package ThreadTest;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author Summer
 * @program: ThreadTest
 * @description 线程间通信
 * @create 2022-01-2022/1/26 14:09
 **/
public class MyThreadCommunication {
    public static void main(String[] args) {
        MyThreadComm t = new MyThreadComm();
        // 1 使用Google.guava中的ThreadFactoryBuilder， ThreadPoolExecutor创建一个线程池
        //ThreadFactory build = new ThreadFactoryBuilder()
        //        .setNameFormat("Thread-pool-%d")
        //        .build();
        //ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
        //        2,
        //        0,
        //        TimeUnit.MILLISECONDS,
        //        new LinkedBlockingDeque<>(),
        //        build,
        //        new ThreadPoolExecutor.AbortPolicy());
        //// 使用线程池执行两个线程
        //executor.execute(t);
        //executor.execute(t);
        //executor.shutdown();

        // 2 使用Executors创建一个大小为2的线程池
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(t);
        service.submit(t);
        service.shutdown();
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