package com.weifc.lesson_04;

import com.weifc.lesson_02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction1 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            //关闭数据库的自动提交, 自动会开启事务
            conn.setAutoCommit(false); //开启事务

            String sql1 = "UPDATE account SET money = money - 100 WHERE NAME = 'A';";
            st = conn.prepareStatement(sql1); //预编译
            st.executeUpdate();

            String sql2 = "UPDATE account SET money = money + 100 WHERE NAME = 'B';";
            st = conn.prepareStatement(sql2); //预编译
            st.executeUpdate();

            //业务完毕, 提交事务
            conn.commit();
            System.out.println("提交事务成功!");

        } catch (SQLException e) {
            try {
                conn.rollback(); //如果失败回滚事务
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
