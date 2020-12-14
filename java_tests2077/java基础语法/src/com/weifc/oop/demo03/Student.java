package com.weifc.oop.demo03;

public class Student extends Person{

    private String name = "glms";

//    public void test(String name) {
//        System.out.println(name);
//        System.out.println(this.name);
//        System.out.println(super.name);
//    }

    public Student() {
        // 隐藏代码: 调用了父类的无参构造函数

        super(); // 调用了父类的构造器, 必须要在子类构造器的第一行
        System.out.println("Student 无参执行了");
    }

    public void print() {
        System.out.println("Student");
    }

    public void test1() {
        print(); // 省略 this.print(); 和下面是一样的
        this.print();
        super.print();
    }

}
