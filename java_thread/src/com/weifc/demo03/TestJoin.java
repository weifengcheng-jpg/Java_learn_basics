package com.weifc.demo03;

//测试join方法
//想象为插队
public class TestJoin implements Runnable {
    public static void main(String[] args) throws InterruptedException{
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        for (int i = 0; i < 100; i++) {
            if (i == 50) {
                thread.join(); //main线程阻塞
            }
            System.out.println("main..."+i);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("join...线程vip来了"+i);
        }
    }

}
