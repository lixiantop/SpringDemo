package org.example.springlearndemo.util;

public class SequentialThreadExecutionJoin {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread T1 is executing");
            try {
                Thread.sleep(1000); // 模拟任务耗时
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Thread T2 is executing");
            try {
                Thread.sleep(1000); // 模拟任务耗时
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t3 = new Thread(() -> {
            System.out.println("Thread T3 is executing");
            try {
                Thread.sleep(1000); // 模拟任务耗时
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        try {
            t1.start();
            t1.join(); // 等待T1完成
            t2.start();
            t2.join(); // 等待T2完成
            t3.start();
            t3.join(); // 等待T3完成
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void test() {
        System.out.println("test");
    }
}