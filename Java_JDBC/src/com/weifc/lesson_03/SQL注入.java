package com.weifc.lesson_03;

import com.weifc.lesson_02.utils.JdbcUtils;

import java.sql.*;

public class SQL注入 {
    public static void main(String[] args) {
        //login("zhansan", "123456"); // 这是正常
        login(" '' or 1=1 ", "123456"); //技巧 这是注入
    }

    //登录业务
    public static void login(String username, String password) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            //PreparedStatement 防止SQL注入的本质, 把传递进来的参数当作字符
            //假设其中存在转义字符, 比如说 ' 会被直接转义
            String sql = "select * from users where `name` = ? and `password` = ?";

            st = conn.prepareStatement(sql); //预编译
            st.setString(1, username);
            st.setString(2, password);

            rs = st.executeQuery(); //查询完毕返回一个结果集
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
