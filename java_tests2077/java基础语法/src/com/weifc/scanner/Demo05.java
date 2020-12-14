package com.weifc.scanner;

import java.util.Scanner;

public class Demo05 {
    public static void main(String[] args) {
        // 我们可以输入多个数字, 并求它总和 与平均数, 每输入一个数字用回车确定, 通过输入非数字来结束输入并输出执行结果:

        System.out.println("请输入数字: ");

        Scanner scanner = new Scanner(System.in);

        // 统计和
        double sum = 0;
        // 统计输入多少个数字
        int m = 0;

        while (scanner.hasNextDouble()) {
            double x = scanner.nextDouble();
            ++m;
            sum = sum + x;
        }

        System.out.println("输入了 " + m + "个数");
        System.out.println("总和: " + sum);
    }
}
