package com.weifc.oop.demo03;

public class Person {

//    public  void say() {
//        System.out.println("说了一句话");
//    }

    public Person() {
        System.out.println("Person 无参执行了");
    }

    protected String name = "weifc";

    public void print() {
        System.out.println("Person");
    }

}
