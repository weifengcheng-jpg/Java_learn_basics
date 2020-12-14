package com.weifc.method;

public class Demo04 {
    //2! 2*1
    //5! 5*4*3*2*1
    public static void main(String[] args) {
        System.out.println(f(5));
    }

    //1!
    //5!
//    递归结构包括两个部分:
//    递归头: 什么时候不调用自身方法. 如果没有头, 将陷入死循环.
//    递归体: 什么时候需要调用自身方法
    //递归思想
    public static int f(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * f(n - 1);
        }
    }
}
