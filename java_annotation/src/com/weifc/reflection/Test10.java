package com.weifc.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//动态的创建对象, 通过反射
public class Test10 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //获得Class对象
        Class c1 = Class.forName("com.weifc.reflection.User");

        //构造一个对象
        //User user = (User)c1.newInstance(); //本质上是调用类的无参构造器, 如果没有无参构造就会报错
        //System.out.println(user);

        //通过构造器创建对象
        Constructor constructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        User user2 = (User)constructor.newInstance("wei", 001, 18);
        System.out.println(user2);

        //通过反射调用普通方法
        User user3 = (User)c1.newInstance();
        //通过反射获取一个方法
        Method setName = c1.getDeclaredMethod("setName", String.class);

        //invoke : 激活的意思
        //(对象, "方法的值")
        setName.invoke(user3, "格罗");
        System.out.println(user3.getName());
        System.out.println(user3);

        //通过反射操作属性
        System.out.println("===================================");
        User user4 = (User)c1.newInstance();
        Field name = c1.getDeclaredField("name"); //private

        name.setAccessible(true); //true 关闭调
        name.set(user4, "格罗2");
        System.out.println(user4.getName());
        System.out.println(user4);
    }
}
