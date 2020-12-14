package com.weifc.array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Demo03 {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 9, 4, 28};

        System.out.println(a);
        // 打印数组元素

        // 打印数组元素 Arrays.toString
        System.out.println(Arrays.toString(a));
        Arrays.sort(a); // 数组进行排序 : 升序
        System.out.println(Arrays.toString(a));

        Arrays.fill(a, 0);
        System.out.println(Arrays.toString(a));
    }

    public void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {

        }
    }

}
