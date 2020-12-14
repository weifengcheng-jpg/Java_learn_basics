package com.weifc.oop.demo02;

public class Application {
    public static void main(String[] args) {
        Student weifc = new Student();
        weifc.m_name = "waifc";
        System.out.println(weifc.m_name);

        weifc.setAge(19);
        System.out.println(weifc.getAge());
    }
}
