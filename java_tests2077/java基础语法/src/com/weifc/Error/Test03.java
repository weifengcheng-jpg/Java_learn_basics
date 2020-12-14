package com.weifc.Error;

public class Test03 {
    public static void main(String[] args) {
        Test03 test03 = new Test03();
        int sum = 0;
        System.out.println(sum);
        sum = test03.add(1, 2);
        System.out.println(sum);
    }

    public int add(int a, int b) {
        return a + b;
    }
}
