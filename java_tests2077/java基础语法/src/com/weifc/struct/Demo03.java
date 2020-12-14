package com.weifc.struct;

public class Demo03 {
    public static void main(String[] args) {
        int odd_and = 0;
        int even_number_and = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                even_number_and += i;
            } else {
                odd_and += i;
            }
        }

        System.out.println("偶数和: " + even_number_and);
        System.out.println("奇数和: " + odd_and);
    }
}
