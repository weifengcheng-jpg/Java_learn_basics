package com.weifc.lesson_03;

import com.weifc.lesson_02.utils.JdbcUtils;
import java.util.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestInsert {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();

            // 区别
            // 使用? 占位符替代参数
            String sql = "INSERT INTO users(`id`, `name`, `password`, `email`, `birthday`) VALUES(?,?,?,?,?);";
            st = conn.prepareStatement(sql); //预编译SQL, 先写SQL, 然后不执行

            //手动给参数赋值
            st.setInt(1,    5); //id
            st.setString(2, "weifc");
            st.setString(3, "123456");
            st.setString(4, "2418060011@qq.com");
            // 注意点: sql.Date 数据库
            //        util.Date Java   new Date().getTime() 获得时间戳
            st.setDate(5, new java.sql.Date(new Date().getTime()));

            //执行
            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("插入成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, null);
        }

    }
}
