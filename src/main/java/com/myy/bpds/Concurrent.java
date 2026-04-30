package com.myy.bpds;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Concurrent {

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int id = i;
            threads.add(new Thread(() -> {
                // print(id);
                useReentrantLock(id);

            }, "Thread-" + i));
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
    }


    static final Object obj = new Object();

    static int next = 0;

    static String s = "ABCDEFG";

    public static void print(int id) {
        for (int i = 0; i < 10; i++) {
            synchronized (obj) {
                while (next != id) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.printf("%s %s\n", Thread.currentThread().getName(), s.charAt(id));
                next = (next + 1) % s.length();
                obj.notifyAll();
            }
        }
    }

    static final ReentrantLock lock = new ReentrantLock();

    static final List<Condition> conditions = new ArrayList<>();

    static int rNext = 0;

    static {
        for (int i = 0; i < s.length(); i++) {
            conditions.add(lock.newCondition());
        }
    }

    public static void useReentrantLock(int id) {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (rNext != id) {
                    conditions.get(id).await();
                }
                System.out.printf("%s %s\n", Thread.currentThread().getName(), s.charAt(id));
                rNext = (rNext + 1) % s.length();
                conditions.get((id + 1) % s.length()).signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    {
        ReentrantLock rLock = new ReentrantLock();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");
    }

    {
        CountDownLatch cdl = new CountDownLatch(1);
        cdl.countDown();
    }


}

class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(2);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cdl.countDown();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cdl.countDown();
        }).start();

        new Thread(() -> {
            try {
                cdl.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    }
}

