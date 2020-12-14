package com.weifc.Error;

public class Test02 {
    public static void main(String[] args) {
        try {
            new Test02().test(1,0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

//    // 假设这方法中, 处理不了这个异常. 方法上抛出异常(请看下面
//    public void test(int a, int b) {
//        if (b == 0) { // 主动的抛出异常 throw throws
//            throw new ArithmeticException(); // 主动抛出异常, 主要(一般)在方法里使用
//        }
//        // System.out.println(a/b);
//    }



    // 假设这方法中, 处理不了这个异常. 方法上抛出异常
    public void test(int a, int b) throws ArithmeticException{
        if (b == 0) {
            throw new ArithmeticException(); // 主动的抛出异常, 一般在方法中使用
        }
    }

}


//// 选中 Ctrl + Alt + T
//        try {
//
//                } catch (Exception e) {
//                System.exit(1);
//                e.printStackTrace(); // 打印错误的栈信息
//                } finally {
//
//                }