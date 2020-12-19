package com.weifc.demo03;

public class TestJoin02 implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程vip来了"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin02 testJoin02 = new TestJoin02();
        Thread thread = new Thread(testJoin02);
        thread.start();

        for (int i = 0; i <= 500; i++) {
            if (i == 200) {
                thread.join(); //插队
            }
            System.out.println("main..."+i);
        }
    }
}
