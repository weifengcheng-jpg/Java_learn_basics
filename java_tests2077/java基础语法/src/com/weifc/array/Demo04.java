package com.weifc.array;

import java.util.Arrays;

public class Demo04 {
    public static void main(String[] args) {
        int[] a = {1, 3, 2, 9, 8};
        int[] new_array = sort(a);

        System.out.println(Arrays.toString(new_array));
    }

    public static int[] sort(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j+1] > array[j]) {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        return array;
    }
}
