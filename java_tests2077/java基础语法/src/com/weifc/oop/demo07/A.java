package com.weifc.oop.demo07;

// 抽象类的所有方法, 继承了它的子类, 都必须要实现它的方法~ 除非~
public class A extends Action{
    @Override
    public void doSomething() {
        System.out.println("A_doSomething");
    }
}
