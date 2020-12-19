package com.weifc.lambda;

public class TestLamdba2 {
//    static class Love implements ILove { 静态类
//        @Override
//        public void love(int a) {
//            System.out.println("i love you-->"+a);
//        }
//    }

    public static void main(String[] args) {
        ILove love = null;

//        class Love implements ILove { //局部内部类
//            @Override
//            public void love(int a) {
//                System.out.println("i love you-->"+a);
//            }
//        }

//        ILove love = new ILove() { //匿名内部类
//            public void love(int a) {
//                System.out.println("i love you-->"+a);
//            }
//        };

        //1. lambda 表达简化
//        ILove love = (int a)->{ //再简化v1 lambda 表示简化
//            System.out.println("i love you-->"+a);
//        };
//
//        //简化1. 参数类型
//        love = (a)->{
//            System.out.println("i love you-->"+a);
//        };

        //简化2. 简化括号
//        love = a->{
//            System.out.println("i love you-->"+a);
//        };

        //简化3. 去掉花括号
        love = a-> System.out.println("i love you-->"+a);

        //总结:
            //lambda 表达式只能有一行代码的情况下才能简化成为一行, 如何有多行, 那么就用代码块包裹.
            //前提是接口为函数式接口
        //多个参数也可以去掉参数类型, 要去掉就都要去掉

        love.love(2077);
    }
}

interface ILove {
    void love(int a);
}

// class Love implements ILove {
//    @Override
//    public void love(int a) {
//        System.out.println("i love you-->"+a);
//    }
//}
