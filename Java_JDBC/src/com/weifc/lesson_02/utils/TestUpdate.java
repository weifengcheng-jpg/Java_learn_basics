package com.weifc.lesson_02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection(); //获得数据库连接
            st = conn.createStatement(); //获得SQL的执行对象
            String sql = "UPDATE `jdbcStudy`.`users` SET `name` = 'weifc_update' WHERE `id` = '4'; ";
            int i = st.executeUpdate(sql);
            if (i>0) {
                System.out.println("更新成功!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            JdbcUtils.release(conn, st, rs);
        }
    }
}
