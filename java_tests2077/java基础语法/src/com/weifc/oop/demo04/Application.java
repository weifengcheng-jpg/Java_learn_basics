package com.weifc.oop.demo04;

public class Application {
    public static void main(String[] args) {
        // 一个对象的实际类型是确定的
        // new Student();
        // new Person();

        // 可以指向的引用类型就不确定了: 父类的引用指向了子类
        Student s1 = new Student(); // Student 能调用的方法都是自己的或者继承父类的
        Person s2 = new Student(); // Person 父类型, 可以指向子类, 但是不能调用子类独有的方法
        Object s3 = new Student();

        // 对象能执行哪些方法, 主要看对象左边的类型, 和右边关系不大!
        s2.run(); //子类重写了父类的方法, 执行子类的方法
        s1.run();
        // s2.eat(); 不行
        // ((Student) s2).eat(); //高转低, 强制类型转换
    }
}
