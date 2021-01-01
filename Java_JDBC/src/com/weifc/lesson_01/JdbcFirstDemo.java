package com.weifc.lesson_01;

import java.sql.*;

//我的第一个JDBC程序
public class JdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. 加载驱动
        // DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver"); //固定写法, 加载驱动

        //2. 用户信息和url
        // useUnicode=true&characterEncoding=utf8&useSSL=true;
        String url = "jdbc:mysql://47.116.77.166:3306/jdbcStudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "wei12345";

        //3. 连接成功, 数据库对象 connection 代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        connection.rollback();
        connection.commit();
        connection.setAutoCommit(true);

        //4. 执行SQL的对象 statement 执行sql的对象
        Statement statement = connection.createStatement();


        //5. 执行SQL的对象去执行 SQL, 可能存在结果, 查看返回结果
        String sql = "SELECT * FROM `users`";

        ResultSet resultSet = statement.executeQuery(sql); //返回的结果集, 结果集总封装了我们全部的查询出来的结果



        while (resultSet.next()) {
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("name"));
            System.out.println("pwd=" + resultSet.getObject("PASSWORD"));
            System.out.println("email=" + resultSet.getObject("email"));
            System.out.println("birthday=" + resultSet.getObject("birthday"));
            System.out.println("==============================================");
        }

        //6. 释放连接
        resultSet.close();
        statement.close();
        connection.close();

    }
}
