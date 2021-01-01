package com.weifc.lesson_03;

import com.weifc.lesson_02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSelect {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();

            String sql = "SELECT * FROM `jdbcStudy`.`users` WHERE id = ?;";
            st = conn.prepareStatement(sql); // 预编译
            st.setInt(1, 4); //传递参数

            //执行
            rs  = st.executeQuery(); //执行

            if (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, null);
        }
    }
}
