package com.weifc.lesson01;

import java.net.InetAddress;
import java.net.UnknownHostException;

// 测试IP
public class TestInstAddress {
    public static void main(String[] args) {
        try {
            // 查询本机地址
            InetAddress inetAddress_1 = InetAddress.getByName("127.0.0.1");
            System.out.println(inetAddress_1);

            InetAddress inetAddress_3 = InetAddress.getByName("localhost");
            System.out.println(inetAddress_3);

            InetAddress inetAddress_4 = InetAddress.getLocalHost();
            System.out.println(inetAddress_4);

            // 查询网址ip地址
            InetAddress inetAddress_2 = InetAddress.getByName("www.baidu.com");
            System.out.println(inetAddress_2);

            System.out.println("常用方法");

            // 常用方法
            // System.out.println(inetAddress_2.getAddress());
            System.out.println(inetAddress_2.getCanonicalHostName()); // 规范的名字
            System.out.println(inetAddress_2.getHostAddress()); // ip
            System.out.println(inetAddress_2.getHostName()); // 域名, 或者自己电脑的名字
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
