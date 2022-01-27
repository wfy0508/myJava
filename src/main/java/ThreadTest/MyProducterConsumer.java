package ThreadTest;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

/**
 * @author Summer
 * @program: ThreadTest
 * @description 生产者消费者问题
 * @create 2022-01-2022/1/27 9:55
 **/
public class MyProducterConsumer {
    public static void main(String[] args) {
        Worker worker = new Worker();
        Producer producer = new Producer(worker);
        Consumer consumer1 = new Consumer(worker);
        Consumer consumer2 = new Consumer(worker);
        producer.setName("生产者");
        consumer1.setName("消费者1");
        consumer2.setName("消费者2");
        producer.start();
        consumer1.start();
        consumer2.start();
    }
}

/**
 * 定义一个工具类Worker，用户生产和消费产品
 */
class Worker {
    private int product = 0;

    public synchronized void productGoods() {
        if (product < 20) {
            product++;
            System.out.println(Thread.currentThread().getName() + "开始生产编号为：" + product + " 的产品");
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consumeGoods() {
        if (product > 0) {
            System.out.println(Thread.currentThread().getName() + "开始消费编号为：" + product + " 的产品");
            product--;
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread {
    private int product;
    private Worker worker;

    public Producer(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始生产产品......");
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.productGoods();
        }
    }
}

class Consumer extends Thread {
    private Worker worker;

    public Consumer(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始消费产品......");
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.consumeGoods();
        }
    }
}