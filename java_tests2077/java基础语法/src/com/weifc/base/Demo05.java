package base;

public class Demo05 {
    public static void main(String[] args) {
        // 操作比较大的数的时候, 注意溢出问题
        // JDK7 新特性, 数字之间可以用下划线分割
        int money = 10_0000_0000;
        System.out.println(money);
    }
}
