package com.weifc.array;

public class Demo02 {
    public static void main(String[] args) {
        int[] arrays = {1, 2, 3, 4, 5};

//        for (int i = 0; i < arrays.length; i++) {
//            System.out.println(arrays[i]);
//        }

        // JDK1.5 没有下标
        for (int array : arrays) {
            System.out.println(array);
        }
    }
}
