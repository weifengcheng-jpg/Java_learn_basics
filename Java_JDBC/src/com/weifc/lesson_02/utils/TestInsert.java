package com.weifc.lesson_02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection(); //获得数据库连接
            st = conn.createStatement(); //获得SQL的执行对象
            String sql = "INSERT INTO users(`id`, `name`, `password`, `email`, `birthday`)" +
                    "VALUES(4, 'weifc', '12345', '2418060010@qq.com', '2001-02-03');";
            int i = st.executeUpdate(sql);
            if (i>0) {
                System.out.println("插入成功!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            JdbcUtils.release(conn, st, rs);
        }
    }
}
