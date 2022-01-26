package ThreadTest;

/**
 * @author Summer
 * @program: ThreadTest
 * @description 实现Runnable接口
 * @create 2022-01-2022/1/26 10:41
 **/
public class MyRunnable {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnableExercise());
        t1.setName("Runnable线程");
        t1.start();

    }
}

class MyRunnableExercise implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}