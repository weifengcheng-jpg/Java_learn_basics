package com.weifc.struct;

public class Demo07 {
    public static void main(String[] args) {

        for (int i = 101; i <= 150; i++) {
            boolean tag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    tag = false;
                    break;
                }
            }
            if (tag) {
                System.out.print(i + "\t");
            }
        }
    }
}
