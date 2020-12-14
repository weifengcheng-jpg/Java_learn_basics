package com.weifc.oop;

// 引用传递: 对象, 本质还是值传递
public class Demo02 {
    public static void main(String[] args) {
        Perosn perosn = new Perosn();
        perosn.name = "weifc";
        System.out.println(perosn.name);

    }

    // 返回值为空
    public static void change(int a) {
        a = 10;
    }
}

class Perosn {
    String name;
}