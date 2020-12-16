package com.weifc.lesson01;


import java.net.InetSocketAddress;

public class TestInetSocketAddress {
    public static void main(String[] args) {
        InetSocketAddress socketAddress_01 = new InetSocketAddress("127.0.0.1", 8080);
        InetSocketAddress socketAddress_02 = new InetSocketAddress("127.0.0.1", 8080);
        System.out.println(socketAddress_01);
        System.out.println(socketAddress_02);

        System.out.println(socketAddress_01.getAddress());
        System.out.println(socketAddress_01.getHostName()); // hosts
        System.out.println(socketAddress_01.getPort()); // 端口
    }
}
