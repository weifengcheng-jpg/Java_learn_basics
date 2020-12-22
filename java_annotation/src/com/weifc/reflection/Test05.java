package com.weifc.reflection;

public class Test05 {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.m);

        /*
        1. 加载到内存, 会产生一个类对应Class对象
        2. 链接, 链接结束后 m = 0
        3. 初始化
            <clinit>() {
            System.out.println("A类静态代码块初始化");
        m = 300;
        m = 100;
        //合并之后就等于100了
            }
         */
    }
}

class A{
    static {
        System.out.println("A类静态代码块初始化");
        m = 300;
    }

    static int m = 100;

    public A() {
        System.out.println("A类的无参构造初始化");
    }

}