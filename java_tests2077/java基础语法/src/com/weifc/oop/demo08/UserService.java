package com.weifc.oop.demo08;

// 抽象的思维~ Java
// interface 定义的关键字, 接口都需要实现类
public interface UserService {

    // 常量~ public static final
    int AGE = 99;

    // 接口中的所有定义其实都是抽象的 public abstract
    void add(String name);
    void delete(String name);
    void update(String name);
    void query(String name);

}

/*
作业:

1. 约束
2. 定义一些方法, 让不同的人实现 10 ---> 1
3. public abstract
4. public static final
5. 接口不能被实例化~ , 接口中没有构造方法
6. implements 可以实现多个接口
7. 必须要重写接口中的方法~

 */
