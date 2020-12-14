package com.weifc.method;

public class Demo02 {
    public static void main(String[] args) {
        Demo02 demo02 = new Demo02();
        demo02.test(1);
    }

//    public void method() {}
//    public void method(int i) {}
//    public void method(int i, double d) {}

    public void test(int ... i) { // 只能放到最后
        System.out.println(i[0]);
    }

}
