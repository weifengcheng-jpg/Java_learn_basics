package base;

public class Demo03 {
    public static void main(String[] args) {
        // float 有限 离散
        // double
        // BigDecimal 数学工具类
        // 最好完全避免与浮点数的比较

        float f = 0.1f; // 0.1
        double d = 1.0/10; // 0.1;
        System.out.println(d);

        System.out.println(f==d); // false;

        System.out.println("=================");

        // 字符扩展?
        char c1 = 'a';
        char c2 = '中';
        System.out.println(c1);
        System.out.println((int)c1);
        System.out.println(c2);
        System.out.println((int)c2);

        System.out.println("=================");

        // 所有的字符本质还是数字
        // 编码 Unicode 2 字节 0 - 65536 Excel 2 16 - 65536
        // 转义字符
        // \t 制表符
        // \n 换行
        System.out.println("Hello\njava");

        System.out.println("=================");

        String sa = new String("Hello java");
        String sb = new String("hello java");
        System.out.println(sa == sb);
        //对象, 从内存分析

        // 布尔值扩展
        boolean flag = true;
        if (flag == true) {
            System.out.printf("true");
        }
    }

}
