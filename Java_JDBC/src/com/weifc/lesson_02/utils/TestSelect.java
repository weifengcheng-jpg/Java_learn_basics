package com.weifc.lesson_02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            //SQL
            String sql = "SELECT * FROM `jdbcStudy`.`users` WHERE id = 1;";
            rs = st.executeQuery(sql); //查询完毕会返回一个结果集
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
