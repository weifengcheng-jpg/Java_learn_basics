package com.weifc.scanner;

import java.util.Scanner;

public class Demo04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int i = 0;
        float f = 0;

        System.out.println("请输入整数: ");

        if (scanner.hasNextInt()) {
            i = scanner.nextInt();
            System.out.println("你输入的数据是整数数据: " + i);
        } else {
            System.out.println("你输入的不是整数数据");
        }

        System.out.println("请输入小数: ");

        if (scanner.hasNextFloat()) {
            f = scanner.nextFloat();
            System.out.println("你输入的数据是浮点数数据: " + f);
        } else {
            System.out.println("你输入的不是浮点数数据");
        }

        scanner.close();
    }
}
