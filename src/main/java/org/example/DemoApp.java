package org.example;

public class DemoApp {


    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (obj1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (obj2) {
                    System.out.println("I am acquire obj2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (obj2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (obj1) {
                    System.out.println("I am acquire obj2");
                }
            }
        });

        Thread observingThread = new Thread(() -> {
            while (true) {
                System.out.println(thread1.getState());
                System.out.println(thread2.getState());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        observingThread.start();
        thread1.start();
        thread2.start();
    }
}
