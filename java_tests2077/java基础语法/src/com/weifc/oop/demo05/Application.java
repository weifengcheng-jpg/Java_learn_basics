package com.weifc.oop.demo05;

public class Application {
    public static void main(String[] args) {
        // 类型之间的转化: 基本类型转换 高 低
        // 类型之间的转化 : 父 子

        // 高                    低
        Person student_01 = new Student();

        //student_01 将这个对象转换为 Student 类型, 我们就可以使用 Student 类型方法了
        // 高转低, 要强转
        // Student student = (Student)student_01; 方法一
        // student.go();

        ((Student)student_01).go(); // 方法二

        // 低 转 高
        // 子类转换父类, 可能丢失字节的本来的一些方法
        Student student = new Student();
        student.go();
        Person person = student; // 低转高, 不需要强转

        /*
        1. 父类引用指向子类的对象
        2. 把子类转换为父类, 向上转型;
        3. 把父类转换为子类, 向下转型: 强制转换
        4. 方便方法的调用, 不用new 一个对象, 减少重复的代码!

        抽象: 封装 继承 多态
        抽象类, 接口
         */

    }
}

//        // Object > String
//        // Object > Person > Teacher
//        // Object > Person > Student
//        Object object = new Student();
//
//        System.out.println(object instanceof Student); // true
//        System.out.println(object instanceof Person); // true
//        System.out.println(object instanceof Object); // true
//        System.out.println(object instanceof Teacher); // false
//        System.out.println(object instanceof String); // false
//
//        System.out.println("-------------------------");
//
//        Person person = new Student();
//        System.out.println(person instanceof Student); // true
//        System.out.println(person instanceof Person); // true
//        System.out.println(person instanceof Object); // true
//        System.out.println(person instanceof Teacher); // false
//        // System.out.println(person instanceof String); // 编译报错! 因为一点关系都没有
//
//        System.out.println("-------------------------");
//        Student student = new Student();
//        System.out.println(student instanceof Student); // true
//        System.out.println(student instanceof Person); // true
//        System.out.println(student instanceof Object); // true
//        // System.out.println(student instanceof Teacher); // 编译报错! 因为一点关系都没有
//        // System.out.println(student instanceof String); // 编译报错! 因为一点关系都没有
//
//        //总结
//        // System.out.println(X instanceof Y); // 能不能编译通过! 看它们有没有父子关系