```shell
         __                                     __
  ____  / /_     ____ ___  __  __   ____  _____/ /_
 / __ \/ __ \   / __ `__ \/ / / /  /_  / / ___/ __ \
/ /_/ / / / /  / / / / / / /_/ /    / /_(__  ) / / /
\____/_/ /_/  /_/ /_/ /_/\__, /    /___/____/_/ /_/
                        /____/

```

### 4.8 Select 小结

```sql
select 去重要查询的字段from表 (注意:表和字段可以取别名)
xxx join要连接的表`on`等值判断
where (具体的值，子查询语句)
Group By (通过哪个字段来分组)
Having (过滤分组后的信息，条件和where是一样的，位置不同)
Order By.. (通过哪个字段排序) [升序/降序]
Limit startIndex, pagesize

```

业务层面:

查询: 跨表, 跨数据库 ...

## 5.MySQL 函数

### 5.3 数据库级别的MD5加密(扩展)

什么是MD5?

主要增强算法复杂度和不可逆性.

MD5 不可逆, 具体的值的 MD5 是一样的

MD5 破解网站的原理, 背后有一个字典, MD5 加密后的值 加密的前值

```sql
-- ===================== 测试MD5 加密 =====================
CREATE TABLE `testmd5`(
	`id` INT(4) NOT NULL,
	`name` VARCHAR(20) NOT NULL,
	`pwd` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

-- 明文密码
INSERT INTO testmd5 VALUES(1, 'weifc', 'wei12345'), (2, 'weimk', 'weimk12345'), (3, 'weirq', 'weirq12345')

-- 加密
UPDATE testmd5 SET pwd = MD5(pwd) WHERE id = 1

-- 加密全部的密码
UPDATE testmd5 SET pwd = MD5(pwd) -- 加密全部的密码

-- 插入的时候加密
INSERT INTO testmd5 VALUES(4, 'xiaohong', MD5('xiaohong12345'))

-- 如何校验: 将用户传递进来的密码, 进行 md5 加密, 然后对比加密后的值
SELECT * FROM testmd5 WHERE `name` = 'xiaohong' AND pwd=MD5('xiaohong12345')

```
查看结果
```sql
mysql> SELECT * FROM `school_test04`.`testmd5` LIMIT 0, 1000;
+----+----------+----------------------------------+
| id | name     | pwd                              |
+----+----------+----------------------------------+
|  1 | weifc    | fbb1a6247aecd7d4a6811282fcf32c38 |
|  2 | weimk    | aa9c337a886b57c4fdf011dc77788408 |
|  3 | weirq    | d4b25ebbbfda341410581d99656d78c5 |
|  4 | xiaohong | 43d94447f02fe4f6890339dd57798fc0 |
+----+----------+----------------------------------+
4 rows in set (0.00 sec)

```

## 6 事物
### 6.1 什么是事物
==要么成功, 要么失败==
```xml
-----
1. SQL 执行 A 给   B 转账 A 1000  --> 收200 B(200)
2. SQL 执行 B 收到 A 的钱 A 800   --> B(400)
-----

```

将一组SQL放到一个批次中去执行~

> 事务原则: ACID原则 原子性, 一致性, 隔离性, 持久性 (脏读, 幻读...)

[参考链接](https://www.baidu.com)

**原子性(Atomicity)**

要么都成功, 要么失败

**一致性(Consistency)**

事务前后的数据完整性要保证一致, 1000

**持久性(Durability -- 事务提交**

事务一旦提交则不可逆, 被持久化到数据库中!

**隔离性(Isolation)**

事务的隔离性是多个用户并发访问数据库时, 数据库为每一个用户开启的事务, 不能被其他事务的操作数据所干扰, 事务之间要相互隔离

==隔离所导致的一些问题==

**脏读:**

指一个事务读取了另外一个事务未提交的数据

**不可重复性:**

在一个事务内读取表中的某一行数据, 多次读取结果不同, (这个不一定是错误, 只是某些场合不对)

**虚读:**

是指在一个事务读取到了别的事务插入的数据, 导致前后读取不一致.


> 事务

```sql
-- ======================= 事务 =======================
-- mysql 是默认开启事务自动提交的
SET autocommit = 0 /* 关闭 */
SET autocommit = 1 /* 开启(默认的) */

-- 手动处理事务

-- 事务开启
START TRANSACTION -- 标记一个事务的开始, 从这个之后的 sql 都在同一个事务内

INSERT xx
INSERT xx

-- 提交: 持久化
COMMIT
-- 回滚: 回到原来的样子(失败!)
ROLLBACK

-- 事务结束
SET autocommit = 1 -- 开启自动提交

SAVEPOINT 保存点名 -- 设置一个事务的保存点
ROLLBACK TO SAVEPOINT 保存点名 -- 回滚到保存点
RELEASE SAVEPOINT 保存点名 -- 撤销保存点

```

> 模拟场景

**Demo_模拟场景**

```sql
-- ======================= 事务 =======================
-- mysql 是默认开启事务自动提交的
SET autocommit = 0 /* 关闭 */
SET autocommit = 1 /* 开启(默认的) */

-- 手动处理事务

-- 事务开启
START TRANSACTION -- 标记一个事务的开始, 从这个之后的 sql 都在同一个事务内

INSERT xx
INSERT xx

-- 提交: 持久化
COMMIT
-- 回滚: 回到原来的样子(失败!)
ROLLBACK

-- 事务结束
SET autocommit = 1 -- 开启自动提交

SAVEPOINT 保存点名 -- 设置一个事务的保存点
ROLLBACK TO SAVEPOINT 保存点名 -- 回滚到保存点
RELEASE SAVEPOINT 保存点名 -- 撤销保存点

-- 转账
CREATE DATABASE shop CHARACTER SET utf8 COLLATE utf8_general_ci -- 新建一个库
USE shop -- 选择库

CREATE TABLE `account` (
	`id` INT(3) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(30) NOT NULL,
	`money` DECIMAL(9, 2) NOT NULL,
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO account (`name`, `money`)
VALUES('A', 2000.00), ('B', 10000.00)

-- 模拟转账: 事务
SET autocommit = 0; -- 关闭自动提交
START TRANSACTION -- 开启一个事务 (一组事务)
UPDATE account SET money = money - 500 WHERE `name` = 'A' -- A减500
UPDATE account SET money = money + 500 WHERE `name` = 'B' -- B减500

COMMIT; -- 提交事务, 就被持久化了!
ROLLBACK; -- 回滚

SET autocommit = 1; -- 恢复默认值

```

## 7. 索引

> MySQL 官方对索引的定义为: 索引(Index) 是帮助MySQL 高效获取数据的数据结构. 0.5s 0.00001S. 提取句子主干, 就可以得到索引的本质: 索引是数据结构.

### 7.1 索引的分类

> 在一个表中, 主键索引只能有一个, 唯一索引可以有多个

- 主键索引 (PRIMARY KEY)
    - 唯一的标识, 主键不可重复, 只能有一个列作为主键
- 唯一索引 (UNIQUE KEY)
    - 避免重复的列出现, 唯一索引可以重复, 多个列都可以标识位唯一索引
- 常规索引 (KEY/INDEX)
    - 默认的, index. key 关键字来设置
- 全文索引 (FullText)
    - 在特定的数据库引擎才有, MylSAM
    - 快速定位数据

基础语法
```sql
-- 索引的使用
-- 1. 在创建表的时候给字段增加索引
-- 2. 创建完毕后, 增加索引

-- 显示所有的索引信息
SHOW INDEX FROM student

-- 增加一个全文索引 (索引名) 列名
ALTER TABLE school_test03.`student` ADD FULLTEXT INDEX `studentName`(`studentName`)

-- EXPLAIN 分析sql执行的状况
EXPLAIN SELECT * FROM student; -- 非全文索引

SELECT * FROM student WHERE MATCH(studentName) AGAINST('wei');

```

### 7.2 测试索引

```sql
CREATE TABLE `app_user` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) DEFAULT '' COMMENT '用户昵称',
	`email` VARCHAR(50) NOT NULL COMMENT '用户邮箱',
	`phone` VARCHAR(20) DEFAULT '' COMMENT '手机号',
	`gender` TINYINT(4) UNSIGNED DEFAULT '0' COMMENT '性别(0:男: 1:女)',
	`password` VARCHAR(100) NOT NULL COMMENT '密码',
	`age` TINYINT(4) DEFAULT '0' COMMENT '年龄',
	`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT = 'app用户表'

```

Demo_不重要, 主要用来测试索引

```sql
-- 插入100w 条数据
DELIMITER $$ -- 写函数之前必须要写, 标志
CREATE FUNCTION mock_data()
RETURNS INT
BEGIN
	DECLARE num INT DEFAULT 1000000;
	DECLARE i INT DEFAULT 0;
	WHILE i < num DO
		INSERT INTO app_user(`name`, `email`, `phone`, `gender`, `password`, `age`)
		VALUES(CONCAT('用户', i), '24736743@qq.com', CONCAT('18', FLOOR(RAND()*((999999999-100000000) + 100000000))), FLOOR(RAND()*2), UUID(), FLOOR(RAND()*100));
		SET i = i + 1;
	END WHILE;
	RETURN i;
END;

SELECT  mock_data();

-- 上面的数据只是用来插数据的, 但是我卡在哪里了, 插入失败, 那算了, 不是很重要, 主要用来测试索引的查找速度

-- id __ 表名 _ 字段名
-- CREATE INDEX 索引名 ON 表(字段)
CREATE INDEX id_app_user_name ON app_user(`name`);

SELECT * FROM app_user WHERE `name` = '用户9999'; -- 0.001 sec
SELECT * FROM app_user WHERE `name` = '用户9999'; 

```

运行不行

```shell
1 queries executed, 0 success, 1 errors, 0 warnings

查询：create function mock_data() returns int begin declare num int default 1000000; declare i int default 0; while i < num do INSERT ...

错误代码： 1418
This function has none of DETERMINISTIC, NO SQL, or READS SQL DATA in its declaration and binary logging is enabled (you *might* want to use the less safe log_bin_trust_function_creators variable)

执行耗时   : 0 sec
传送时间   : 0 sec
总耗时      : 0.142 sec

```

总之, 索引是检索速度, 增加几十倍速度

索引在小数据量的时候, 用处不大, 但是在大数据的时候, 区别十分明显~

### 7.3 索引原则
- 索引不是越多越好
- 不要对经常变动数据加索引
- 小数据量的表不需要索引
- 索引一般加在常用来查询字段上

> 索引的数据结构

Hash 类型的索引
Btree: InnoDB 的默认数据结构~

需要阅读: [点击我](http://blog.codinglabs.org/articles/theory-of-mysql-index.html)

### 8. 数据库备份
为什么要备份:
- 保证重要的数据不丢失
- 数据转移

MySQL数据库备份的方式

- 直接拷贝物理文件
- 在Sqlyog这种可视化工具中手动导出
- 在想要导出的表或者库中，右键，选择备份或导出
- 使用命令行导出mysqldump 命令行使用
- 使用命令行导出 mysqldump 命令行使用
```shell
➜  / mysqldump -uroot -p school > /home/working/mysql.sql
Enter password:
➜  /
➜

```
- 使用命令行导入
```shell
# 导入
# 登录的情况下, 切换到指定的数据库
# source 备份文件
mysql> source /home/working/mysql.sql
Query OK, 0 rows affected (0.00 sec)

Query OK, 0 rows affected (0.00 sec)


mysql -u用户名 -p密码 库名< 备份文件

```

假设你要备份数据库, 防止数据丢失.
把数据库给朋友! sql 文件

语句: ysqldump -uroot -p 库名 > /home/working/mysql.sql

(后面就是路径啦!(sqkl/sql))

## 9. 权限管理
### 9.1 为什么需要设计 
==当数据库比较复杂的时候, 我们就需要设计了==

**糟糕的数据库设计:**
- 数据冗余, 浪费空间
- 数据库插入和删除都会麻烦, 异常[屏蔽使用物理外键]
- 程序的性能差

良好的数据库设计:
- 节约内存空间
- 保存数据库的完整性
- 方便我们开发系统

软件开发中, 关于数据库的设计
- 分析需求: 分析业务和需要处理的数据库的需求
- 概要设计: 设计关系图 E-R 图

设计数据库的步骤: (个人博客)
- 收集信息, 分析需求
    - 用户表(用户登录注销, 用户的人信息, 写博客, 创建分类)
    - 分类表(文章分类, 谁创建的)
    - 文件表(文章的信息)
    - 友链表(友链信息)
    - 自定义表(系统信息, 某个关键的字, 或者一些主字段) key : value
    - 说说表(发表心情.. id)
- 标识实体(把需求落地)
- 标识实体之间的关系
    - 写博客: user --> blog
    - 创建分类: user --> category
    - 关注: user --> user
    - 友链: links
    - 评论: user-user-blog

### 9.3 三大范式
**为什么需要数据规范化**
- 信息重复
- 更新异常
- 插入异常
    - 无法正常显示信息
- 删除异常
    - 丢失有效的信息

> 三大范式

[点击我阅读](https://www.cnblogs.com/wsg25/p/9615100.html)

**第一范式(1NF)**

原子性: 保证每一列不可再分

**第二范式(2NF)**

前提: 满足第一范式

每张表只描述一件事情

**第三范式(3NF)**

前提: 满足第一范式 和 第二范式
第三范式需要确保数据表中的每一列数据都和主键直接相关, 而不能间接相关.

(规范数据库的设计)

**`规范性` 和 `性能` 问题**

关联查询的表不等超过三张表
- 考虑商业化的需求和目标, (成本, 用户体验!) 数据库的性能更加重要
- 在规范性能的问题的时候, 需要适当的考虑一下 规范性!
- 故意给某些表增加一些冗余的字段. (从多表查询中变为单表查询)
- 故意增加一些计算列 (从大数据量降低为小数据量的查询: 索引)

