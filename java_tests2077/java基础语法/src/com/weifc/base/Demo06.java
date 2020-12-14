package base;

public class Demo06 {

    // 类变量 static
    static double salary = 2500;

    // 属性:变量

    // 示例变量: 从属于对象; 如果不自行初始化, 这个类型的默认值 0 0.0
    // 布尔值: 默认是 false
    // 除了基本类型, 其余的默认值都是null;
    String name;
    int age;

    // main 方法
    public static void main(String[] args) {
        // 局部变量; 必须声明和初始化值
        int i = 10;

        System.out.println(i);

        // 变量类型 变量名字 = new Demo06();
        Demo06 demo06 = new Demo06();
        System.out.println(demo06.age);
        System.out.println(demo06.name);

        // 类变量 static
        System.out.println(salary);

        // 其他方法


    }

}
