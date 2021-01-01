```shell
         __                                     __
  ____  / /_     ____ ___  __  __   ____  _____/ /_
 / __ \/ __ \   / __ `__ \/ / / /  /_  / / ___/ __ \
/ /_/ / / / /  / / / / / / /_/ /    / /_(__  ) / / /
\____/_/ /_/  /_/ /_/ /_/\__, /    /___/____/_/ /_/
                        /____/

```
## 1. 初始MySQL
```xml
略
```
## 1.2 什么是数据库
```xml
略
```

## 1.3 数据库分类
**关系型数据库**:(SQL)
- MySQL, Oracle, Sql Server, DB2, SQLite
- 通过表和表之间, 行和列之间的关系进行数据的存储, 学员信息表, 考勤表, ...

**非关系型数据库:** (NoSQL) Not Only
- Redis, MongDB
- 非关系型数据库, 对象存储, 通过对象的自身的属性来决定.

## DBMS(数据库管理系统)
- 数据库的管理软件, 科学有效的管理我们的数据. 维护和获取数据;
- MySQL , 数据库管理系统!
 
## 1.4 MySQL 简介

## 1.5 MySQL 安装

## 1.6 安装SQLyog
1. 无脑安装
2. 注册
3. 打开连接数据库
```shell
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| school             |
| test               |
+--------------------+
5 rows in set (0.00 sec)

```
4. 新建一个数据库
```xml
使用sqlyog, 可视化. 这样开发效率才快
```
5. 新建一张表 student
6. 查看表
7. 自己尝试添加多条记录

## 1.7 连接数据库
**命令行连接**
```sql
1. mysql -uroot -p'密码' --连接数据库, mysql -uroot 

2. update mysq1.user set authentication_ _string=password(' 123456') where user=' root' and Host = 'locaThost'; --修改用户密码

3. flush privileges; -- 刷新权限

4. show databases; -- 查看所有的数据库

5. use school -- 切换

6. show tables; -- 查看数据库中所有的表

7. describe student; --显示数据库中所有的表的信息

8. create database xxxx; --创建数据库xxxx

9. exit; --退出连接

-- 单行注释(SQL 的本来的注释) #(也行)
/* (sql的多行注释)


*/
```
**数据库xxx语言** CRUD 增删改查! CV() API()

DDL 定义

DML 操作

DQL 查询

DCL 控制

## 2. 操作数据库 (能看懂就行)
操作数据库 > 操作数据库中的表 > 操作数据库中表的数据

==mysql不区分大小写==
### 2.1 
1. 创建数据库
```sql
CREATE DATABASE [IF NOT EXISTS] school
```
2. 删除数据库
```sql
DROP DATABASE [IF EXISTS] GPU_test;
```
3. 使用数据库
```sql
-- tab键的.上面,如果你的表名或者字段名是一一个特殊字符，就需要带``
USE `school`
```

4. 查看数据库
```sql
SHOW DATABASES; --查看所有的数据库
```

**对比SQLyog可视化操作**

==学习思路:==
- 对比SQLyog 可视化查看历史记录
- 固定的语法或者关键字必须强行记住!

## 2.2 数据库的列类型

> 数值
```xml
- tinyint 十分小的数据 1个字节
- smallint 较小的数据  2个字节
- mediumint 中等的数据 3个字节
- int                  4个字节(常用)
- big  较大的数据      8个字节
- float 浮点数         4byte
- double 浮点数        8byte (精度问题!)
- decimal 字符串形式的浮点数 金融计算的时候, 一般是使用decimal

```

> 字符串
```xml
- char 字符串固定大小的 0 ~ 255
- varchar 可变字符串 0 ~ 65535 常用的 String
- tinytext 微型文本2^8 - 1 
- test 文本串 2 ^ 16 - 1 保存大文本

```

> 时间日期
```xml
java.util.Date

- date YYYY-MM-DD 日期
- time HH: mm: ss 时间格式
- datetime YYY-MMM-DD HH: mm: ss 最常用的
- timestamp 时间戳, 1970.1.1 到现在的毫秒数! 也较为常用!
- year 年份表示

```

> null 
```xml
- 没有值, 未知
- 注意, 不要使用NULL进行运算, 结果为NULL

```

## 2.3 数据库的字段属性(重点)
==Unsigned:==
- 无符号的整数
- 声明了该列不能声明为负数

==zerofill:==
- 0填充的
- 不足的位数, 使用0来填充, int(3), 5 -- 005

==自增:==
- 通常理解为自增, 自动在上一条记录的基础上 +1 (默认)
- 通常用来设计唯一的主键~ index, 必须是`整数类型`
- 可以自定义设计主键自增的起始值和步长

==非空 NULL not null==
- 假设设置为 not null, 如果不给它赋值, 就会报错!
- NUll, 如果不填值, 默认是null!

==默认:==
- 设置默认的值!
- sex, 默认值为男, 如果不指定该列的值, 则会有默认的值!

扩展: 听听就好
```sql
/*
每一表, 都必须存在以下五个字段! 未来做项目用的, 表示一个记录存在意义!
*/

id --主键
'version' --乐观锁
is_delete --伪删除
gmt_create --创建事件
gmt_update --修改时间

```

## 2.4 创建数据库表
Demo
```sql
-- 目标 : 创建一个school数据库'
-- 创建学生表(列, 字段) 使用SQL 创建
-- 学好int 登录密码varchar(20) 名字, 性别varchar(2), 出生日期(datatime), 家庭地址, email

-- 注意点, 使用英文(), 表的名称 和 字段 尽量使用 ``括起来
-- AUTO INCREMENT 自增
-- 字符串 单引号括起来!
-- 所有的语句后面加, (英文的) 最后一个不用加
-- PRIMARY KEY 主键, 一般一个表只有一个唯一的主键!
CREATE TABLE IF NOT EXISTS `student` (
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
	`name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
	`pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
	`sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
	`birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
	`address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
	`email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8


```
运行结果
```xml
1 queries executed, 1 success, 0 errors, 0 warnings

查询：create table if not exists `student` ( `id` int(4) NOT NULL auto_increment comment '学号', `name` varchar(30) not null default...

共 0 行受到影响

执行耗时   : 0.226 sec
传送时间   : 1.038 sec
总耗时      : 1.264 sec
```

格式
```sql
CREATE TABLE [IF NOT EXISTS] `表名` (
	`字段名` 列类型 [属性] [索引] [注释],
	`字段名` 列类型 [属性] [索引] [注释],
	...
	`字段名` 列类型 [属性] [索引] [注释]
)[表类型][字符集设置][注释]

```

常用命令
```sql
CREATE TABLE IF NOT EXISTS `student` (
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
	`name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
	`pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
	`sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
	`birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
	`address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
	`email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

SHOW CREATE DATABASE school_tests02 -- 查看创建数据库的语句
SHOW CREATE TABLE student -- 查看student数据表的定义语句
DESC student -- 显示表的结构

```

## 2.5 数据表的类型

```sql
-- 关于数据库引擎
/*
INNODB 默认使用~
MYISAM 早些年使用的

*/

```
 \  | MYISAM | INNODB
--- | --- | ---
事务支持 | 不支持 | 支持
数据行锁定 | 不支持 | 支持
外键约束 | 不支持 | 支持
全文索引 | 支持 | 不支持
表空间的大小 | 较小 | 较大, 约为2倍

常规的使用操作:
- MYISAM 节约空间, 速度快
- INNODB 安全性高, 实务的处理, 多表多用户操作

> 在物理空间
所有的数据库文件都存在data目录下, 一个文件夹就对应一个数据库
本质还是文件的存储!
MySQL 引擎在物理文件上的区别
- InnoDB 在数据库表中只有一个*.frm文件, 以及上级目录
- MYISAM 对应文件

MySQL 引擎在物理文件上的区别
- InnoDB 在数据库表中只有一个 *.frm 文件, 以及上级目录下的 ibdata1 文件
- MYISAM 对应文件
    - *.frm 表结构的定义文件
    - *.MYD 数据文件(data)
    - *.MYI 索引文件(index)

> 设置数据库表的字符集编码

```xml
CHARSET=utf8

```
不设置的话, 会是mysql默认的字符集编码~(不支持中文!)
MySQL 的默认编码是 Latin1, 不支持中文

在my.ini中配置默认的编码
```ini
character-set-server=utf8

```

常用操作
```sql
SHOW CREATE DATABASE school -- 查看创建数据库的语句
SHOW CREATE TABLE student -- 查看student数据库表的定义语句
DESC student -- 显示表的结构

```

## 2.6 修改删除表
> 修改
```sql
-- 修改表名 ALTER TABLE 旧表名 RENAME AS 新表名
ALTER TABLE teacher RENAME AS teacher1
-- 增加表的字段 ALTER TABLE 表名 ADD 字段名 列属性
ALTER TABLE teacher1 ADD age INT(11)

-- 修改表的字段 (重命名, 修改约束)
-- ALTE TABLE 表名 MODIEF 字段名 列属性[]
ALTER TABLE teacher1 MODIFY age VARCHAR(11) -- 修改约束
-- ALTER TABLE 表名 CHANGE 旧名字 新名字 列属性[]
ALTER TABLE teacher1 CHANGE age age1 INT(11) -- 字段重命名
-- 最终结论: change 用来字段重命名, 不能修改字段类型和约束;
-- modify 不用字段重命名, 只能修改字段类型和约束;

--  删除表的字段 ALTER TABLE 表名 DROP 字段名
ALTER TABLE teacher1 DROP age1

```

> 删除
```sql
-- 删除表
DROP TABLE IF EXISTS teacher1

```
==所有的创建和删除操作尽量加上判断, 以免避免报错~==

注意点:
- `` 字段名, 使用这个包裹
- 注释 -- /**/
- sql 关键字大小写不明感 尽量小写
- 所有的字符英文

## 3 MySQL 数据管理
### 3.1 外键 (了解即可)
> 方式一, 在创建表的时候, 增加约束 (麻烦, 比较复杂)
```sql
CREATE TABLE `grade` (
	`gradeid` INT(10) NOT NULL AUTO_INCREMENT COMMENT '年级id',
	`gradename` VARCHAR(50) NOT NULL COMMENT '年级名称',
	PRIMARY KEY (`gradeid`)
) ENGINE = INNODB DEFAULT CHARSET=utf8

-- 学生表的 gradeid 字段 要去引用年级表的 gradeid
-- 定义外键key
-- 给这个外键添加约束 (执行引用)
CREATE TABLE IF NOT EXISTS `student` (
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
	`name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
	`pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
	`sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
	`birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
	`gradeid` INT(10) NOT NULL COMMENT '学生的年级',
	`address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
	`email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY(`id`),
	KEY `FK_gradeid` (`gradeid`),
	CONSTRAINT `FK_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade`(`gradeid`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

```
删除有外键关系的表的时候, 必须要先删除引用别人的表(从表), 再删除被引用的表 (主表)

> 方式二: 创建表成功后, 添加外键约束

Demo02
```sql
CREATE TABLE IF NOT EXISTS `student` (
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
	`name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
	`pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
	`sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
	`birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
	`gradeid` INT(10) NOT NULL COMMENT '学生的年级',
	`address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
	`email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

-- 创建表的时候没有外键关系
ALTER TABLE `student`
ADD CONSTRAINT `FK_gradeid` FOREIGN KEY(`gradeid`) REFERENCES `grade`(`gradeid`)

-- ALTER TABLE `表` ADD CONSTRAINT `约束名` FOREIGN KEY(`作为外键的列`) REFERENCES `那个表`(`那个字段`)

```
==不用记, 实际不是这么用==

以上的操作都是物理外键, 数据库级别的外键, 我们不建议使用!( 避免数据库过多造成困扰, 这里了解即可)

==最佳实践==

- 数据库就是单纯的表, 只用来存数据, 只有行(数据) 和 列 (字段)
- 我们想要使用多张表的数据, 想使用外键(程序去实现)


### 3.2 DML 语言(全部记住)
**数据库意义**: 数据存储, 数据管理
DML 语言: 数据操作语言
- Insert
- update
- delete



### 3.3 添加
> insert
```sql
-- 一般写插入语句, 我们一定要数据和字段一一对应!

-- 插入多个字段
INSERT INTO `grade`(`gradename`) 
VALUES('大二'), ('大一')

INSERT INTO `student`(`name`) VALUES ('张三')

INSERT INTO `student`(`name`, `pwd`, `sex`) VALUES ('李四', 'aaaaa', '男')

INSERT INTO `student`(`name`, `pwd`, `sex`) 
VALUES ('李四', 'aaaaaa', '男'), ('王五', 'ddddd', '男')

```
查看
```sql
mysql> SELECT * FROM `school_tests02`.`student` LIMIT 0, 1000;
+----+--------+--------+--------+----------+---------+---------+-------+
| id | name   | pwd    | sex    | birthday | gradeid | address | email |
+----+--------+--------+--------+----------+---------+---------+-------+
|  1 | 张三   | 123456 | 未知   | NULL     |       0 | NULL    | NULL  |
|  2 | 李四   | aaaaa  | 男     | NULL     |       0 | NULL    | NULL  |
|  3 | 李四   | aaaaaa | 男     | NULL     |       0 | NULL    | NULL  |
|  4 | 王五   | ddddd  | 男     | NULL     |       0 | NULL    | NULL  |
+----+--------+--------+--------+----------+---------+---------+-------+
4 rows in set (0.00 sec)

```
==语法: insert into 表名([字段名1, 字段名2, 字段3])values('值1'),('值2'), ('3', ...)==

注意事项:
1. 字段和字段之间使用 英文逗号隔开
2. 字段是可以省略的, 但是后面的值必须要一一对应, 不能少
3. 可以同时插入多条数据, VALUES 后面的值, 需要使用, 隔开即可 `VALUES(), (), ...`

### 3.4 修改
> update 修改谁(条件) set 原来的值 = 新值
```sql
-- 修改学员名字
UPDATE `student` SET `name`='weifc' WHERE id = 1;

-- 不指定条件的情况下, 会改变所有表!
UPDATE `student` SET `name`='长江七号'; -- 不指定, 默认全部改
-- 语法:
-- UPDATE `表名` SET `name`='新的内容' WHERE [id(条件)] = 1;

-- 修改多个属性, 逗号隔开
UPDATE `student` SET `name`='狂神', `email`='2418060010@qq.com' WHERE id = 1;
-- 语法:
-- 略

-- 通过多个条件定位数据
UPDATE `student` SET `name`='长江七号' WHERE `name`='狂神44' AND sex='女';

```

条件: where子句 运算符 id 等于某个值, 大于某个值, 在某个区间内修改

==操作符==会返回布尔值

操作符 | 含义 | 范围 | 结果
---|--- | --- | --- |
= | 等于 | 5=6 | false
<>或者!= | 不等于 | 5<>6 | true
> |
< |
>= |
BETWEEN ... and ... | [2,5]在某个范围内 
AND | 我和你 && | 5>1 and 1>2 | false
OR | 我或你 | ... | ... 

语法: ==UPDATE `表名` SET `colnum_name`='value', [colnum_name = value, ...] WHERE [条件]==
注意: 
- colnum_name 是数据库的列, 尽量带上``
- 条件, 筛选的条件, 如果没有指定, 则会修改所有的列
- value, 是一个具体的值, 也可是一个具体的变量
- 设置变量也行
```sql
UPDATE `student` SET `birthday`=CURRENT_TIME WHERE `name`='张三' AND sex='未知'

mysql> SELECT * FROM `school_tests02`.`student` LIMIT 0, 1000;
+----+--------+--------+--------+---------------------+---------+---------+-------+
| id | name   | pwd    | sex    | birthday            | gradeid | address | email |
+----+--------+--------+--------+---------------------+---------+---------+-------+
|  1 | 张三   | 123456 | 未知   | 2020-12-24 23:35:05 |       0 | NULL    | NULL  |
|  2 | 李四   | aaaaa  | 男     | NULL                |       0 | NULL    | NULL  |
|  3 | 李四   | aaaaaa | 男     | NULL                |       0 | NULL    | NULL  |
|  4 | 王五   | ddddd  | 男     | NULL                |       0 | NULL    | NULL  |
+----+--------+--------+--------+---------------------+---------+---------+-------+
4 rows in set (0.00 sec)

```

