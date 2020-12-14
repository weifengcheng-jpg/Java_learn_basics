package com.weifc.scanner;

import java.util.Scanner;

public class Demo02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("使用nextLine方式接收: ");

        // 判断是否还有输入
        if (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            System.out.println("输出的内容为: " + str);
        }

        scanner.close();
    }
}
