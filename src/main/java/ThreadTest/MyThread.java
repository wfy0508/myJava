package ThreadTest;

/**
 * @author Summer
 * @program: ThreadTest
 * @description 继承Thread
 * @create 2022-01-2022/1/26 10:17
 **/
public class MyThread {
    public static void main(String[] args) {
        ThreadExercise t1 = new ThreadExercise();
        t1.setName("线程1");
        t1.start();
    }
}

class ThreadExercise extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}