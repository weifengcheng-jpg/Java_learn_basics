package com.weifc.lesson_02;

import com.weifc.lesson_02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL注入 {
    public static void main(String[] args) {
        // login("zhansan", "123456");
        login(" 'or' 1=1", " 'or'1=1"); //技巧
    }

    //登录业务
    public static void login(String username, String password) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            // SELECT * FROM users WHERE `name` = 'zhansan' AND `password` = '123456';
            // SELECT * FROM users WHERE `name` = '' or '1=1' AND `password` = '' or '1=1';
            //SQL
            String sql = "SELECT * FROM users WHERE `name` = '"+username+"' AND `password` = '"+password+"';";
            rs = st.executeQuery(sql); //查询完毕返回一个结果集
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("password"));
                System.out.println("====================================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

}
