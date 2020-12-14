package com.weifc.oop.demo01;

public class Application {
    public static void main(String[] args) {
        Student weifc = new Student();
        weifc.name = "weifc";
        weifc.age = 19;

        System.out.println(weifc.name);
        System.out.println(weifc.age);
    }
}
