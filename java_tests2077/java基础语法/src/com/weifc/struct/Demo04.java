package com.weifc.struct;

public class Demo04 {
    public static void main(String[] args) {
        // 练习2: 用while 或 for 循环输出 1 - 1000 之间能被5 整除的数, 并且每行输出3个

//        int count = 0;
//        for (int i = 1; i <= 1000; i++) {
//            if (i % 5 == 0) {
//                System.out.print(i + "\t");
//                count++;
//            }
//            if (count % 3 == 0) {
//                System.out.println();
//            }
//        }

        int i = 1;
        int count = 0;
        while (i <= 1000) {
            if (i % 5 == 0) {
                System.out.print(i + "\t");
                count++;
            }
            i++;
            if (count % 3 == 0) {
                System.out.println();
            }
        }
    }
}
