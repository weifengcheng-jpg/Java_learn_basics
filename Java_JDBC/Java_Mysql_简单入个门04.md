```shell
         __                                     __
  ____  / /_     ____ ___  __  __   ____  _____/ /_
 / __ \/ __ \   / __ `__ \/ / / /  /_  / / ___/ __ \
/ /_/ / / / /  / / / / / / /_/ /    / /_(__  ) / / /
\____/_/ /_/  /_/ /_/ /_/\__, /    /___/____/_/ /_/
                        /____/

```
## 10.JDBC (重点)

驱动: 声卡, 显卡, 数据库

我们的程序会通过数据库驱动, 和数据库打交道!
```shell
-------------------------
|       应用程序        |
-------------------------
     |             |
    \ /           \ /
----------  -------------
|mysql驱动| | Oracle 驱动|
----------  -------------
     |           |
    \ /         \ /
--------     --------
|数据库|     | 数据库|
--------     --------
    
```
没有什么是加一层解决不了的
```shell
-------------------------
|       应用程序        |
-------------------------
     |             |
    \ /           \ /
---------------------------
|           JDBC          |
---------------------------
     |             |
    \ /           \ /
----------  -------------
|mysql驱动| | Oracle 驱动|
----------  -------------
     |           |
    \ /         \ /
--------     --------
|数据库|     | 数据库|
--------     --------
    
```
统一接口, 方便开发人员

### 10.2 JDBC 

SUN 公司为了简化开发人员的(对数据库的统一)操作, 提供了一个(Java操作数据库的)规范, 俗称 JDBC

这些规范的实现由具体的厂商去做

Java.sql

Javax.sql

点击我下载:[mysql-connector-java-5.1.48.jar](https://mvnrepository.com/artifact/mysql/mysql-connector-java/5.1.48)

### 10.3 第一个JDBC 程序

> 创建测试数据库

```sql
CREATE DATABASE jdbcStudy CHARACTER SET utf8 COLLATE utf8_general_ci;

USE jdbcStudy;

CREATE TABLE `users`(
	`id` INT PRIMARY KEY,
	NAME VARCHAR(40),
	PASSWORD VARCHAR(40),
	email VARCHAR(60),
	birthday DATE
);

INSERT INTO users(id, NAME, PASSWORD, email, birthday)
VALUES(1, 'zhansan', '123456', 'zs@sina.com', '1980-12-04'),
(2, 'lisi', '123456', 'lisi@qq.com', '1981-12-04'),
(3, 'wangwu', '123456', 'wangwu@qq.com', '2001-02-03');

```
查看
```shell
mysql> /*[0:04:56][80 ms]*/ SELECT * FROM `jdbcStudy`.`users` LIMIT 0, 1000;
+----+---------+----------+---------------+------------+
| id | name    | password | email         | birthday   |
+----+---------+----------+---------------+------------+
|  1 | zhansan | 123456   | zs@sina.com   | 1980-12-04 |
|  2 | lisi    | 123456   | lisi@qq.com   | 1981-12-04 |
|  3 | wangwu  | 123456   | wangwu@qq.com | 2001-02-03 |
+----+---------+----------+---------------+------------+
3 rows in set (0.00 sec)

```


**新建一个普通项目**

**导入jar包(mysql-connector-java-5.1.48.jar)**

3. 编写测试代码
Demo_001
```java
package com.weifc.lesson_01;

import java.sql.*;

//我的第一个JDBC程序
public class JdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. 加载驱动
        Class.forName("com.mysql.jdbc.Driver"); //固定写法, 加载驱动

        //2. 用户信息和url
        // useUnicode=true&characterEncoding=utf8&useSSL=true;
        String url = "jdbc:mysql://47.116.77.166:3306/jdbcStudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "wei12345";

        //3. 连接成功, 数据库对象 connection 代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

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
        }

        //6. 释放连接
        resultSet.close();
        statement.close();
        connection.close();

    }
}

```
运行结果
```shell
id=1
name=zhansan
pwd=123456
email=zs@sina.com
birthday=1980-12-04
id=2
name=lisi
pwd=123456
email=lisi@qq.com
birthday=1981-12-04
id=3
name=wangwu
pwd=123456
email=wangwu@qq.com
birthday=2001-02-03

Process finished with exit code 0

```

步骤总结:
1. 加载驱动
2. 连接数据库 DriverManager
3. 获取执行sql的对象 Stame
4. 获得返回的结果集()
5. 释放连接

> DriverManager

```java
// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
Class.forName("com.mysql.jdbc.Driver"); //固定写法, 加载驱动

Connection connection = DriverManager.getConnection(url, username, password);
// connection 代表数据库
// 数据库设置自动提交
// 事务提交
// 事务回滚
connection.rollback();
connection.commit();
connection.setAutoCommit(true);

```

> URL

```java
//2. 用户信息和url
// useUnicode=true&characterEncoding=utf8&useSSL=true;
String url = "jdbc:mysql://47.116.77.166:3306/jdbcStudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
String username = "root";
String password = "wei12345";

// mysql -- 3306
//协议 ://主机地址:端口号/数据库名?参数1&参数2&参数3
// oralce -- 1521
// jdbc:oracle:thin:@localhost:1521:sid

```

> Statement 执行SQL 的对象 prepareStatement 执行SQL对象

```java
String sql = "SELECT * FROM `users`"; //编写SQL

statement.executeQuery(); // 查询操作返回 ResultSet
statement.execute(); // 执行任何SQL
statement.executeUpdate(); //更新, 插入, 删除. 都是用这个, 返回一个受影响的行数
        
```

> ResultSet 查询的结果集: 封装了了所有的查询结果

获得指定的数据类型
```java
resultSet.getObject(); //不知道列类型的情况下使用
resultSet.getString(); //如果知道列的类型就使用
resultSet.getInt();
resultSet.getFloat();
resultSet.getDate();
...
 
```
遍历, 指针
```java
// [1, 2, 3, 4, 5, 6, 7]
resultSet.beforeFirst(); //移动到最前面
resultSet.afterLast(); //移动到最后面
resultSet.next(); //移动到下一个数据
resultSet.previous(); //移动到前一行
resultSet.absolute(x); //移动到(x)指定行

```

> 释放资源

```java
//6. 释放连接
resultSet.close();
statement.close();
connection.close();

```

### 10.4 statement 对象
==Jdbc中的statement对象用于向数据库发送SQL语句,想完成对数据库的增删改查,只需要通过这 个对象向数据库发送增删改查语句即可。==
- Statement对象的executeUpdate方法，用于向数据库发送增、删、改的sq|语句，executeUpdate执行完后, 将会返回一个整数(即增删改语句导致了数据库几行数据发生了变化)。
- Statement.executeQuery方法用于向数据库发送查询语句，executeQuery方法返回代表查询结果的ResultSet对象。

> CRUD 操作-create

使用 executeUpdate(String sql) 方法完成数据添加操作, 示例操作:
```java
Statement st = conn. createStatement() ;
string sq1 = "insert into user... valuess....) ";
int num = st. executeUpdate (sq1);
if (num>0){
    System. out. print1n("插入成功! ! ! ");
}

```

> CRUD操作-delete

使用 executeUpdate(String sql)方法完成数据删除操作, 示例操作:
```java
Statement st = conn.createstatement();
String sq1 = "delete from user where id=1";
int num = st.executeUpdate(sq1);
if (num>0){
    System.out.println(“删除成功! ! ! ");
}

```

> CRUD操作-update

使用executeUpdate(String sql)方法完成数据修改操作, 示例操作:
```java
Statement st = conn.createstatement() ;
String sq1 = "update user set name='' where name=''";
int num = st.executeUpdate(sql);
if (num>0){
    System. out. print1n(“修改成功! ! ! ");
}

```

> CRUD操作-read

使用executeQuery(String sql) 方法完成数据查询操作, 示例操作:
```java
Statement st = conn.createstatement();
String sq1 = "select * from user where id=1";
ResultSet rs = st.executeQuery(sq1);
while(rs.next(){
//根据获取列的数据类型，分别调用rs的相应方法映射到java对象中
}

```

> 代码实现

1. 提取工具类

db.properties
```properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://47.116.77.166:3306/jdbcStudy?useUnicode=true&characterEncoding=utf8&useSSL=true
username=root
password=wei12345

```

TestUtils.java
```java
package com.weifc.lesson_02.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        try {
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            //驱动只有加载一次
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    //释放连接资源
    public static void release(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

```



2. 编写增删改方法, 



Demo_增01
```java
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

```

Demo_删除02
```java
package com.weifc.lesson_02;

import com.weifc.lesson_02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDelete {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection(); //获得数据库连接
            st = conn.createStatement(); //获得SQL的执行对象
            String sql = "DELETE FROM `jdbcStudy`.`users` WHERE `id` = '4'; ";
            int i = st.executeUpdate(sql);
            if (i>0) {
                System.out.println("删除成功!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            JdbcUtils.release(conn, st, rs);
        }
    }
}

```
Demo_改03
```java
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

```

Demo_查04
```java
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

```

> SQL 注入的问题

==sql 存在漏洞, 会被攻击导致数据泄漏, SQL会被拼接 or==

Demo_SQL注入
```java
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

```
运行结果
```shell
zhansan
123456
====================================================
lisi
123456
====================================================
wangwu
123456
====================================================
weifc_update
12345
====================================================

Process finished with exit code 0

```

总结: Statement 不安全

### 10.4 PreparedStatement 对象
PreparedStatement 可以防止SQL注入. 效率更好!

**1. 增**

Demot_增01
```java
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

```
**2. 删**

Demo_删02
```java
package com.weifc.lesson_03;

import com.weifc.lesson_02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class TestDelete {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();

            // 区别
            // 使用? 占位符替代参数
            String sql = "DELETE FROM `jdbcStudy`.`users` WHERE `id` = ?; ";
            st = conn.prepareStatement(sql); //预编译SQL, 先写SQL, 然后不执行

            //手动给参数赋值
            st.setInt(1, 5);

            //执行
            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("删除成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, null);
        }
    }
}

```

**3. 改**
Demo_改03
```java
package com.weifc.lesson_03;

import com.weifc.lesson_02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();

            // 区别
            // 使用? 占位符替代参数
            String sql = "UPDATE `jdbcStudy`.`users` SET `name` = ? WHERE `id` = ?; ";
            st = conn.prepareStatement(sql); //预编译SQL, 先写SQL, 然后不执行

            //手动给参数赋值
            st.setString(1, "weifc_update");
            st.setInt(2, 4);

            //执行
            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("更新成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, null);
        }
    }
}

```

**4. 查**
Demo_查04
```java
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

```

**5.防止SQL注入**
Demo_防止SQL注入06
```java
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

```

### 10.7 使用IDEA连接数据库

连接成功后, 可以选择数据库

双击

提交

刷新

### 10.8 事务
==要么都成功, 要么都失败==

> ACID 原则

**原子性:** 要么全部完成, 要么都不完成

一致性: 总数不变

**隔离性: 多个进程互补干扰**

脏读: 一个事务读取了另一个没有提交的事务

不可重复读取: 在同一事务内, 重复读取表中的数据, 表数据发生变化

虚读(幻读) : 在一个事务内, 读取到了别人插入的数据, 导致前后读出来的结果不一致


> 代码实现

1. 开启事务 `conn.setAutoCommit(false); //开启事务`
2. 一组业务执行完毕, 提交事务
3. 可以在 catch 语句显示的定义回滚语句, 但失败就会默认回滚事务

Demo_成功
```java
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

```
Demo_if 失败
```java
package com.weifc.lesson_04;

import com.weifc.lesson_02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction2 {
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

            int x = 1/0; //报错

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
            //如果失败, 则默认回滚
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}

````

### 10.9. 数据库连接池

数据库连接--执行完毕--释放
连接 -- 释放 十分浪费系统资源

**池化技术: 准备一些预先的资源, 过来就连接预先准备好的** 

------ 开门 -- 服务 --- 关门 (非池化)

------ 开门 -- 业务员: 等待 -- 服务 -- (池化)

常用连接数 `100` 个

最小连接数: `10`

最大连接数: `100` 业务最高承载上限

排队等待, 

等待超时: 100ms

==编写连接池, 实现一个接口 `DataSource`==

> 开源数据源实现

> 开源数据, 导jar包拿来即用

DBCP    

C3P0

Druid: 阿里巴巴

使用了这些数据库连接之后, 我们在项目开发中就不需要编写连接数据库的代码了!

> DBCP

需要用到 `jar` 包

commons-dbcp-1.4.jar

commons-pool-1.6.jar


> C3P0

需要的jar包

c3p0-0.9.55 

mchange-commons-java-0.2.19

> 总结:

==编写连接池, 实现一个接口 `DataSource`==

无论使用什么数据源, 本质还是一样的`DataSource`接口是不变的, 方法就不会变

还有一个 Druid

Apache

[点击我](http://apache.org/index.html#projects-list)

## 完