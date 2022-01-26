package ThreadTest;

import java.io.IOException;

/**
 * @author Summer
 * @program: ThreadTest
 * @description 使用同步代码块和同步方法实现线程安全
 * @create 2022-01-2022/1/26 10:49
 **/
public class MyThreadSafety {
    public static void main(String[] args) {
        MyThread1 t = new MyThread1();
        Thread t1 = new Thread(t);
        t1.setName("线程1");
        Thread t2 = new Thread(t);
        t2.setName("线程2");
        Thread t3 = new Thread(t);
        t3.setName("线程3");
        t1.start();
        t2.start();
        t3.start();

        MyThread2 m = new MyThread2();
        Thread m1 = new Thread(m);
        m1.setName("M1");
        Thread m2 = new Thread(m);
        m2.setName("M2");
        Thread m3 = new Thread(m);
        m3.setName("M3");
        m1.start();
        m2.start();
        m3.start();

    }
}

class MyThread1 extends Thread {
    private static int gift = 10;

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    /**
     * 同步方法
     */
    private synchronized void show() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (gift > 0) {
            System.out.println(Thread.currentThread().getName() + ": " + gift);
            gift--;
        }
    }
}

class MyThread2 extends Thread {
    private static int ticket = 10;

    @Override
    public void run() {
        while (true) {
            // this作为同步监视器，指向MyThread2，多个线程公用同一把锁
            synchronized (this) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}