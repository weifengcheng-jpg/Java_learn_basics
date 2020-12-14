package com.weifc.oop.demo06;

public class Person {

    { // 执行顺序 2: 赋初始值
        System.out.println("匿名代码块");
    }

    // 执行顺序 1: 只执行一次
    static {
        System.out.println("静态代码块");
    }

    // 执行顺序 3:
    public  Person() {
        System.out.println("构造方法");
    }

    public static void main(String[] args) {
        Person person1 = new Person();
        System.out.println("======");
        Person person2 = new Person();
    }
}

/*
{
        // 代码块 (匿名代码块
    }

    static { // 类一加载就执行, 只执行一次
        // 静态代码块
    }

 */