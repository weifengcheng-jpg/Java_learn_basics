package com.weifc.demo03;

public class demo01 implements Runnable {
    //1.线程中定义线程体使用的标识
    private boolean flag = true;

    @Override
    public void run() {
        //2.线程体使用该标识
        while (flag) {
            System.out.println("run... Thread");
        }
    }

    //3.对外提供方法改变标识
    public void stop() {
        this.flag = false;
    }
}
