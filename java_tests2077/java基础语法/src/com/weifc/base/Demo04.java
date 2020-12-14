package base;

public class Demo04 {
    public static void main(String[] args) {
        int i = 128;
        byte b = (byte)i; // 内存溢出

        // 强制转换

        System.out.println(i);
        System.out.println(b);

        /*
        注意点:
        1. 不能对布尔值进行转换
        2. 不能把对象类型转换为部相干的类型
        3. 在把高容量转换到低容量的时候, 强制转换
        4. 转换的时候可能存在内存溢出, 或者精度问题!
        5. byte -> short -> char -> int -> log -> float -> doubles
         */

        System.out.println("======");
        System.out.println((int)23.7);
        System.out.println((int)-45.89f);

        System.out.println("======");

    }
}
