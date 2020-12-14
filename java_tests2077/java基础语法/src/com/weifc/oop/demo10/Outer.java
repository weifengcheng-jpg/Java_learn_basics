package com.weifc.oop.demo10;

public class Outer {

    // 局部内部类
    public void method() {
        class Inner {
            public void in() {}
        }
    }

}

// 一个java类中可以有多个 class 类, 但是只能有一个public class
//class A {
//
//}

//    private int id = 10;
//    public void out() {
//        System.out.println("这是外部类的方法");
//    }
//
//class static class Inner {
//    public void in() {
//        System.out.println("这是内部类的方法");
//    }
//
////        // 获得外部类的私有属性~
////        public void getID() {
////            System.out.println(id);
////        }
//
//
//}
