//package com.weifc.annotation;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
////自定义注解
//public class Test03 {
//    //注解可以显示赋值, 如果没有默认值, 我们就必须给注解赋值
//    @MyAnnotation2(age = 18, name = "wei")
//    public void test() {}
//
//    @MyAnnotation3("wei")
//    public void test2() {
//
//    }
//}
//
//@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE.METHOD})
//@Retention(RetentionPolicy.RUNTIME)
//@interface MyAnnotation2{
//    //注解的参数 : 参数类型 + 参数名();
//    String name() default "";
//    int age() default 0; // 这并不是方法
//    int id() default -1; //如果默认值为-1, 代表不存在, indexof ,如果找不到就返回-1
//
//    String[] schools() default {"km", "sz"};
//}
//
//@Target({ElementType.TYPE, ElementType.METHOD})
//@Retention(RetentionPolicy.RUNTIME)
//@interface MyAnnotation3{
//    String value();
//}
