```shell
         __                                     __
  ____  / /_     ____ ___  __  __   ____  _____/ /_
 / __ \/ __ \   / __ `__ \/ / / /  /_  / / ___/ __ \
/ /_/ / / / /  / / / / / / /_/ /    / /_(__  ) / / /
\____/_/ /_/  /_/ /_/ /_/\__, /    /___/____/_/ /_/
                        /____/

```
## 3.5 删除

> delete 命令
语法: `delete from 表名 [where 条件]`

```sql
-- 删除数据 (避免这样写, 会全部删除)
DELETE FROM `student`;

-- 删除指定数据
DELETE FROM `student` WHERE id = 1;

```

> TRUNCATE 命令

作用: 完全清空一个数据库表, 表的结构和索引约束不会变!
```sql
-- 清空 student 表
TRUNCATE `student`

```

> delete 和 TRUNCATE 区别

- 相同点: 都能删除数据, 都不会删除表结构
- 不同:
    - TRUNCATE 重新设置自增列计数

```sql
-- 测试delete 和 TRUNCATE 区别
CREATE TABLE `test` (
	`id` INT(4) NOT NULL AUTO_INCREMENT,
	`coll` VARCHAR(20) NOT NULL,
	PRIMARY KEY(`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `test`(`coll`) VALUES('1'),('2'),('3')

DELETE FROM `test` -- 不会影响自增

TRUNCATE TABLE `test` -- 自增会归零

```
了解即可: `DELETE删除的问题`, 重启数据库, 现象
- InnoDB 自增列会重1开始 (存在内存中的, 掉电即失)
- MySAM 机继续从上一个增量开始 (存在文件中的, 不会丢失)

## 4. DQL查询数据(最重点)
### 4.1 DQL
(Data Query LANGUAGE: 数据查询语言)
- 所有的查询操作都用它 Select
- 简单的查询, 复杂的查询它都能做~
- `数据库中最核心的语言, 最重要的语言`
- 使用频率最高的语句

### 4.2 指定查询字段

```sql
CREATE DATABASE `school`

USE `school`

DROP TABLE IF EXISTS `grade`

CREATE TABLE `grade` (
	`GradeID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '年级编号',
	`GradeName` VARCHAR(50) NOT NULL COMMENT '年级名称',
	PRIMARY KEY (`GradeID`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT INTO `grade`(`GradeID`, `GradeName`) VALUES (1, '大一'), (2, '大二'), (3, '大三'), (4, '大四'), (5, '社会人');

DROP TABLE IF EXISTS `result`;

CREATE TABLE `result` (
	`StudentNo` INT(4) NOT NULL COMMENT '学号',
	`SubjectNo` INT(4) NOT NULL COMMENT '课程编号',
	`ExamDate` DATETIME NOT NULL COMMENT '考试日期',
	`StudentResult` INT(4) NOT NULL COMMENT '考试成绩',
	KEY `SubjectNo` (`SubjectNo`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

INSERT INTO `result` (`StudentNo`, `SubjectNo`, `ExamDate`, `StudentResult`) VALUES (1000, 1, '2013-11-11 16:00:00', 60), (1000, 1, '2013-11-11 16:00:00', 60);

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
	`StudentNo` INT(4) NOT NULL COMMENT '学号',
	`LoginPwd` VARCHAR(20) DEFAULT NULL,
	`StudentName` VARCHAR(20) DEFAULT NULL COMMENT '学生姓名',
	`Sex` TINYINT(1) DEFAULT NULL COMMENT '性别, 取值0或1',
	`GradeId` INT(11) DEFAULT NULL COMMENT '年级编号',
	`Phone` VARCHAR(50) NOT NULL COMMENT '联系电话, 允许为空, 即可选输入',
	`Address` VARCHAR(255) NOT NULL COMMENT '地址, 允许为空, 即可选输入',
	`BornDate` DATETIME DEFAULT NULL COMMENT '出生时间',
	`Email` VARCHAR(50) NOT NULL COMMENT '邮箱账号, 允许为空, 即可选输入',
	`IdentityCard` VARCHAR(18) DEFAULT NULL COMMENT '身份证号',
	PRIMARY KEY (`StudentNo`),
	UNIQUE KEY `IdentityCard` (`IdentityCard`),
	KEY `Email` (`Email`)
)ENGINE=MYISAM DEFAULT CHARSET=utf8;

INSERT INTO `student` (`StudentNo`, `LoginPwd`, `StudentName`, `Sex`, `GradeId`, `Phone`, `Address`, `BornDate`, `Email`, `IdentityCard`)
VALUES (1000, '10000', 'weifc', 1, 1000, '12345', '广东', '2020-01-01', '2418@qq.com', '2345');

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
	`SubjectNo` INT(11) NOT NULL AUTO_INCREMENT COMMENT '课程编号',
	`SubjectName` VARCHAR(50) DEFAULT NULL COMMENT '课程名称',
	`ClassHour` INT(4) DEFAULT NULL COMMENT '学时',
	`GradeID` INT(4) DEFAULT NULL COMMENT '年级编号',
	PRIMARY KEY (`SubjectNo`)	
)ENGINE=INNODB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

INSERT INTO `subject` (`SubjectNo`, `SubjectName`, `ClassHour`, `GradeID`) VALUES (1, '高数', 110, 1);

```
运行结果
```sql
mysql> SELECT * FROM `school_test03`.`grade` LIMIT 0, 1000;
+---------+-----------+
| GradeID | GradeName |
+---------+-----------+
|       1 | 大一      |
|       2 | 大二      |
|       3 | 大三      |
|       4 | 大四      |
|       5 | 社会人    |
+---------+-----------+
5 rows in set (0.00 sec)

mysql>
mysql> SELECT * FROM `school_test03`.`result` LIMIT 0, 1000;
+-----------+-----------+---------------------+---------------+
| StudentNo | SubjectNo | ExamDate            | StudentResult |
+-----------+-----------+---------------------+---------------+
|      1000 |         1 | 2013-11-11 16:00:00 |            60 |
|      1000 |         1 | 2013-11-11 16:00:00 |            60 |
+-----------+-----------+---------------------+---------------+
2 rows in set (0.00 sec)

mysql> SELECT * FROM `school_test03`.`student` LIMIT 0, 1000;
+-----------+----------+-------------+------+---------+-------+---------+---------------------+-------------+--------------+
| StudentNo | LoginPwd | StudentName | Sex  | GradeId | Phone | Address | BornDate            | Email       | IdentityCard |
+-----------+----------+-------------+------+---------+-------+---------+---------------------+-------------+--------------+
|      1000 | 10000    | weifc       |    1 |    1000 | 12345 | 广东    | 2020-01-01 00:00:00 | 2418@qq.com | 2345         |
+-----------+----------+-------------+------+---------+-------+---------+---------------------+-------------+--------------+
1 row in set (0.01 sec)

mysql> SELECT * FROM `school_test03`.`subject` LIMIT 0, 1000;
+-----------+-------------+-----------+---------+
| SubjectNo | SubjectName | ClassHour | GradeID |
+-----------+-------------+-----------+---------+
|         1 | 高数        |       110 |       1 |
+-----------+-------------+-----------+---------+
1 row in set (0.01 sec)

```

### 4.2 指定查询字段

```sql
-- 查询全部的学生 SELECT 字段 FROM 表
SELECT * FROM student

-- 查询指定字段
SELECT `StudentNo`, `StudentName` FROM student

-- 别名, 给结果起一个名字 AS 可以给字段起别名, 也可以给表起别名
SELECT `StudentNo` AS 学号, `StudentName` AS 学生姓名 FROM student AS s

-- 函数 Conca(a, b)
SELECT CONCAT('名字: ', StudentName) AS 新名字 FROM student

```
语法: `SELECT 字段, ... FROM 表`

> 有的时候, 列名字不是那么的见名知意. 我们起别名 AS 字段名 as 别名 表名 as 别名

> 去重 distinct

作用: 去除SELECT 查询出来的结果中重复的数据只显示一条
```sql
-- 查询一下有哪些同学参加了考试, 成绩
SELECT * FROM result -- 查询全部的考试成绩

SELECT `StudentNo` FROM result -- 查询有哪些同学参加了考试

SELECT DISTINCT `StudentNO` FROM result -- 发现重复数据, 去重

```

> 数据库的列()

```sql
SELECT VERSION() -- 查询系统版本
SELECT 100*3-1 AS 计算结果 -- 用来计算
SELECT @@auto_increment_increment -- 查询自增的步长(变量)

-- 学员考试成绩 + 1分查看
SELECT `StudentNo`, `StudentResult`+1 AS '提分后' FROM result

```

==数据库中的表达式: 文本值, 列, NULL, 函数, 计算表达式, 系统变量 ...==

select `表达式` from 表

## 4.3 where 条件子句 

作用: 检索数据中`符合条件`的值

收缩的条件由一个或者多个表达式组成! 结果 布尔值

> 逻辑运算符

运算符 | 语法 | 描述
---|--- | --- |
and && | ... | ...
ar  | ... | ...
Not ! | ... | ...

==尽量使用英文字母==

```sql
-- ====================== where ==========================

SELECT studentNo, `StudentResult` FROM result

-- 查询考试成绩在 95 ~ 100 分之间
SELECT StudentNo, `StudentResult` FROM result 
WHERE StudentResult>=50 AND StudentResult<=100

-- and &&
SELECT StudentNo, `StudentResult` FROM result 
WHERE StudentResult>=50 && StudentResult<=100

-- 模糊查询(区间)
SELECT studentNo, `StudentResult` FROM result
WHERE StudentResult BETWEEN 1 AND 50

-- 除了1000号学生之外的同学的成绩
SELECT studentNo, `StudentResult` FROM result
WHERE studentNo != 1000;

-- != not
SELECT studentNo, `StudentResult` FROM result
WHERE NOT studentNo = 1000

```

> 模糊查询: 比较运算符


运算符 | 语法 | 描述
---|--- | --- |
IS NULL | a is null | 如果操作符为null, 结果为真
IS NOT NULL | a is not null | 如果操作符为 not null, 结果为正
BETWEEN | a between b and c | 若a在b和c之间, 则结果为真
Like | a like b | SQL 匹配, 如果a匹配b, 则结果为真
IN | a in(a1, a2, a3, ...) | 假设a在a1, 或者a2... 其中的某一个值中, 结果为真

模糊查询
```sql
-- =================== 模糊查询 ===================
-- 查询姓w的同学
-- like结合 %(代表0到任意个字符) _(一个字符)
SELECT `StudentNo`, `StudentName` FROM `student`
WHERE StudentName LIKE 'w%'

-- 查询姓w的同学
-- 查询姓w的同学, 名字后面只有一个字的
SELECT `StudentNo`, `StudentName` FROM `student`
WHERE StudentName LIKE '刘_'

-- 查询姓w的同学, 名字后面只有二个字的
SELECT `StudentNo`, `StudentName` FROM `student`
WHERE StudentName LIKE '刘__'

-- 查询名字中间有嘉字的同学 %嘉%
SELECT `StudentNo`, `StudentName` FROM `student`
WHERE StudentName LIKE '%嘉%'

-- ====== in (% 具体的一个或者多个值) =========
-- 查询 1001, 1002, 1003号学员
SELECT `StudentNo`, `StudnetName` FROM `student`
WHERE StudentNo IN (1001, 1002, 1003);

-- 查询在北京的学生
SELECT `StudentNo`, `StudentName` FROM `student`
WHERE `Address` IN ('广东%');

-- ===== null   not null ========
-- 查询地址为空的学生 null ''
SELECT `StudentNo`, `StudentName` FROM `student`
WHERE address='' OR address IS NULL

-- 查询有出生日期的同学 不为空
SELECT `StudentNo`, `StudentName` FROM `student`
WHERE `BornDate` IS NULL

```

## 4.4 联表查询

> 联表查询

```sql
-- ======== 联表查询 join =============

-- 查询参加了考试的同学(学号, 姓名, 科目编号, 分数)
SELECT * FROM student
SELECT * FROM result

/*
1. 分析需求, 分析查询的字段来自那些表, (连接查询)
2. 确定使用哪种连接查询? 7种
确定交叉点(这两个表中哪个数据时相同的)
判断的条件 : 学生表中 studentNo = 成绩表 studentNo
*/

SELECT s.StudentNo, StudentName, SubjectNo, StudentResult
FROM student AS s
INNER JOIN result AS r
WHERE s.StudentNo = r.studentNO

-- Right Join
SELECT s.StudentNo, StudentName, SubjectNo, StudentResult
FROM student AS s
RIGHT JOIN result r
ON s.studentNO = r.studentNO

-- Left Join
SELECT s.StudentNo, StudentName, SubjectNo, StudentResult
FROM student AS s
LEFT JOIN result r
ON s.studentNO = r.studentNO

```


操作 | 描述
---|---
Inner join | 如果表中至少有一个匹配, 就返回行
left join | 会从左表中返回所有的值, 即使右表中没有匹配
right join | 会从右表中返回所有的值, 即使左表中没有匹配

-- 查询缺考的同学
```sql
-- 思考题(查询了参加考试的同学的信息: 学号, 学生姓名, 科目名, 分数)
/*
1. 分析需求, 分析查询的字段来自那些表, student, result, subject (连接查询)
2. 确定使用哪种连接查询? 7种
确定交叉点(这两个表中哪个数据时相同的)
判断的条件 : 学生表中 studentNo = 成绩表 studentNo
*/
SELECT s.studentNo, studentName, SubjectName, `StudentResult`
FROM student s
RIGHT JOIN result r
ON s.studentNo = r.studentNo
INNER JOIN `subject` sub
ON r.SubjectNo = sub.SubjectNo

-- 我要查询哪些数据 select ...
-- 从那几个表中查 From 表 xxx Join 连接的表 on 交叉条件
-- 假设存在一种多张表查询, 慢慢来, 先查询两张表然后再慢慢增加

-- From a left join b
-- From a right join b

```

> 自连接

Demo_自连接01
```sql
-- ===================== 自连接 =====================
CREATE TABLE `category` (
	`categoryid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主题id',
	`pid` INT(10) NOT NULL COMMENT '父id',
	`categoryName` VARCHAR(50) NOT NULL COMMENT '主题名字',
	PRIMARY KEY (`categoryid`)
	
)ENGINE=INNODB AUTO_INCREMENT = 9 DEFAULT CHARSET = utf8

INSERT INTO `category`(`categoryid`, `pid`, `categoryName`)
VALUES('2', '1', '信息技术'),
('3', '1', '软件开发'),
('4', '3', '数据库'),
('5', '1', '美术设计'),
('6', '3', 'web开发'),
('7', '5', 'ps技术'),
('8', '2', '办公信息');

```
**查看:**
```shell
mysql> SHOW FULL FIELDS FROM `school_test03`.`category`;
+--------------+------------------+-----------------+------+-----+---------+----------------+---------------------------------+--------------+
| Field        | Type             | Collation       | Null | Key | Default | Extra          | Privileges                      | Comment      |
+--------------+------------------+-----------------+------+-----+---------+----------------+---------------------------------+--------------+
| categoryid   | int(10) unsigned | NULL            | NO   | PRI | NULL    | auto_increment | select,insert,update,references | 主题id       |
| pid          | int(10)          | NULL            | NO   |     | NULL    |                | select,insert,update,references | 父id         |
| categoryName | varchar(50)      | utf8_general_ci | NO   |     | NULL    |                | select,insert,update,references | 主题名字     |
+--------------+------------------+-----------------+------+-----+---------+----------------+---------------------------------+--------------+
3 rows in set (0.00 sec)


mysql> SELECT * FROM `school_test03`.`category` LIMIT 0, 1000;
+------------+-----+--------------+
| categoryid | pid | categoryName |
+------------+-----+--------------+
|          2 |   1 | 信息技术     |
|          3 |   1 | 软件开发     |
|          4 |   3 | 数据库       |
|          5 |   1 | 美术设计     |
|          6 |   3 | web开发      |
|          7 |   5 | ps技术       |
|          8 |   2 | 办公信息     |
+------------+-----+--------------+
7 rows in set (0.00 sec)

```

> 自连接 (了解)

自己的表和自己的表连接, 核心: ==一张表拆成为两张一样的表即可==

> 父类

categoryid | categoryName
---|---
2 | 新技术
3 | 软件开发
5 | 美术设计

> 子类

pid | categoryid | categoryName
---|--- | --- |
3 | 4 | 数据库
2 | 8 | 办公信息
3 | 6 | web开发
5 | 7 | ps技术

> 操作: 查询父类对应子类关系


父类 | 子类
---|---
信息技术 | 办公信息
软件开发 | 数据库
软件开发 | web开发
美术设计 | ps技术

Demo_练习1
```sql
-- 查询父子信息: 把一张表看为两个一模一样 表
SELECT a.`categoryName` AS '父栏目', b.`categoryName` AS '子栏目'
FROM `category` AS a, `category` AS b
WHERE a.`categoryid` = b.`pid`

```

Demo_练习2
```sql
-- ===================== 自连接 =====================
CREATE TABLE `category` (
	`categoryid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主题id',
	`pid` INT(10) NOT NULL COMMENT '父id',
	`categoryName` VARCHAR(50) NOT NULL COMMENT '主题名字',
	PRIMARY KEY (`categoryid`)
	
)ENGINE=INNODB AUTO_INCREMENT = 9 DEFAULT CHARSET = utf8

INSERT INTO `category`(`categoryid`, `pid`, `categoryName`)
VALUES('2', '1', '信息技术'),
('3', '1', '软件开发'),
('4', '3', '数据库'),
('5', '1', '美术设计'),
('6', '3', 'web开发'),
('7', '5', 'ps技术'),
('8', '2', '办公信息');

-- 查询父子信息: 把一张表看为两个一模一样 表
SELECT a.`categoryName` AS '父栏目', b.`categoryName` AS '子栏目'
FROM `category` AS a, `category` AS b
WHERE a.`categoryid` = b.`pid`

-- 查询学员所属的年级 (学号, 学生的姓名, 年级名称)
SELECT studentNo, studentName, GradeName
FROM student s
INNER JOIN `grade` g
ON s.`GradeID` = g.`GradeId`

-- 查询科目所属的年级 (科目名称, 年级名称)
SELECT `SubjetName` `GradeName`
FROM `subject` sub
INNER JOIN `grade` g
ON sub.`GradeID` = g.`GradeIdID`;

-- 查询科目所属年级 (科目名称, 年级名称)
SELECT `subjectName`, `GradeName`
FROM `subject` sub
INNER JOIN `grade` g
ON sub.`GradeID` = g.`GradeID`

-- 查询了 高数 考试的同学的信息: 学号, 学生姓名, 科目名, 分数)
SELECT s.`StudentNo`, `StudentName`, `SubjectName`, `StudentResult`
FROM student s
INNER JOIN `result` r
ON s.`StudentNo` = r.`StudentNo`
INNER JOIN `subject` sub
ON r.`SubjectNo` = sub.`SubjectNo`
WHERE SubjectName = '高数'

```

### 4.5 分页和排序

> 排序

```sql
-- 排序 : 升序 ASC, 降序DESC
-- ORDER BY 通过那个字段排序, 怎么排
-- 查询的结果根据 成绩降序排序
SELECT s.`StudentNo`, `StudentName`, `SubjectName`, `StudentResult`
FROM student s
INNER JOIN `result` r
ON s.`StudentNo` = r.`StudentNo`
INNER JOIN `subject` sub
ON r.`SubjectNo` = sub.`SubjectNo`
WHERE SubjectName = '高数'
ORDER BY StudentResult DESC

```

> 分页

```sql
-- 100万
-- 为什么要分页?
-- 缓解数据库压力, 给人的体验更好, 瀑布流

-- 分页, 每页只显示五条数据
-- 语法: limit 起始值, 页面的大小
-- 网页应用: 当前, 总的页数
-- LIMIT 0,5 1 ~ 5
-- LIMIT 1,5 2 ~ 5
-- limit 6,6 
SELECT s.`StudentNo`, `StudentName`, `SubjectName`, `StudentResult`
FROM student s
INNER JOIN `result` r
ON s.`StudentNo` = r.`StudentNo`
INNER JOIN `subject` sub
ON r.`SubjectNo` = sub.`SubjectNo`
WHERE SubjectName = '高数'
ORDER BY StudentResult DESC
LIMIT 0, 5 -- 1 ~ 5

-- 第一页 limit 0,5 		(1-1) * 5       , 5
-- 第二页 limit 5,5		(2-1) * 5       , 5
-- 第三页 limit 10,5 		(3-1) * 5       , 5
-- 第N页 limit 0,5 		(n-1) * pageSize, pageSize
-- [pageSize: 页面大小]
-- [(n-1) * pageSize: 起始值]
-- [n : 当前页]
-- [数据总数/页面大小 = 总页数]

```
语法:  `limit(查询起始下标, pageSize)`

Demo_第一步(先扩大范围先)
```sql
-- 思考:
-- 查询 Java 第一学年 课程成绩排名前十的学生, 并且分数要大于80 的学生信息 (学号, 姓名, 课程名称, 分数)
SELECT s.`StudentNo`, `StudentName`, `SubjectName`, `StudentResult`
FROM `student` s
INNER JOIN `result` r
ON s.StudentNo = r.StudentNo
INNER JOIN `subject` sub
ON sub.`SubjectNo` = r.`SubjectNo`
ORDER BY StudentResult DESC

```

Demo_第二步(缩小范围)
```sql
-- 思考:
-- 查询 Java 第一学年 课程成绩排名前十的学生, 并且分数要大于80 的学生信息 (学号, 姓名, 课程名称, 分数)
SELECT s.`StudentNo`, `StudentName`, `SubjectName`, `StudentResult`
FROM `student` s
INNER JOIN `result` r
ON s.StudentNo = r.StudentNo
INNER JOIN `subject` sub
ON sub.`SubjectNo` = r.`SubjectNo`
WHERE SubjectName = '高数'
ORDER BY StudentResult DESC
LIMIT 0, 10

```

### 4.6 子查询

where(值是固定的, 这个值是计算出来的)

本质: `在where语句嵌套一个子查询语句`

```sql
-- 查询所有高数 的学生学号
SELECT `StudentNo`, `SubjectNo`, `StudentResult`
FROM `result`
WHERE `StudentNo` = (
	SELECT SubjectNo FROM `subject` 
	WHERE SubjectName = '高数'
)
ORDER BY StudentResult DESC

-- SELECT SubjectNo FROM `subject` WHERE SubjectName = '高数' 这行是嵌套在父类中的

```

一步一步来

Demo_第一步, 先扩大范围先
```sql
-- 分数不小于80分的学生的学号和姓名
SELECT s.`StudentNo`, `StudentName`
FROM student s
INNER JOIN result r
ON r.StudentNo = s.StudentNo

```

Demo_第二步, 再缩小范围
```sql
-- 分数不小于80分的学生的学号和姓名
SELECT s.`StudentNo`, `StudentName`
FROM student s
INNER JOIN result r
ON r.StudentNo = s.StudentNo
WHERE `StudentResult` <= 60

```

> 子查询

```sql
-- ====================== where ===================
-- 1. 查询数据库结构-1 的所有考试结果 (学号, 科目编号, 成绩), 降序排序
-- 方式一: 使用连接查询
SELECT `StudentNo`, r.`SubjectNo`, `StudentResult`
FROM `result` r
INNER JOIN `subject` sub
ON r.SubjectNo = sub.`SubjectNo`
WHERE SubjectName = '高数'
ORDER BY StudentResult DESC

-- 方式二: 使用子查询(由里及外)
-- 查询所有数据结构-1 的学生学号
SELECT `StudentNo`, `SubjectNo`, `StudentResult`
FROM `result`
WHERE `StudentNo` = (
	SELECT SubjectNo FROM `subject` 
	WHERE SubjectName = '高数'
)
ORDER BY StudentResult DESC

SELECT SubjectNo FROM `subject` WHERE SubjectName = '高数'

-- 分数不小于80分的学生的学号和姓名
SELECT s.`StudentNo`, `StudentName`
FROM student s
INNER JOIN result r
ON r.StudentNo = s.StudentNo
WHERE `StudentResult` <= 60

-- 在这个基础上增加一个科目, 高等数学-2
-- 查询 高等数学-2 的编号
SELECT s.`StudentNo`, `StudentName`
FROM student s
INNER JOIN result r
ON r.StudentNo = s.StudentNo
WHERE `StudentResult` <= 60 AND `SubjectNo` = (
	SELECT `SubjectNo` FROM `subject` WHERE `SubjectName` = '高数'
)

-- 再改造(由里及外)
SELECT StudentNo, StudentName FROM student WHERE StudentNo IN (
	SELECT StudentNo FROM result WHERE StudentResult <= 60 AND SubjectNo = (
		SELECT SubjectNo FROM `subject` WHERE `SubjectName` = '高数'
	)
)

```

### 4.7 分组和过滤
```sql
-- ================== 聚合函数 ==================
-- 都能够统计 表中的数据 (想查询一个表中有多少个记录, 就使用这个count())
SELECT COUNT(studentname) FROM student -- 2个数据 Count(字段), 会忽略所有的 null 值
SELECT COUNT(*) FROM student; -- Count(*), 不会忽略 null 值, 本质计算行数
SELECT COUNT(1) FROM result; -- Count(1), 不会忽略所有的 null 值 本质 计算行数

SELECT SUM(`StudentResult`) AS 总和 FROM result
SELECT AVG(`StudentResult`) AS 平均分 FROM result
SELECT MAX(`StudentResult`) AS 最高分 FROM result
SELECT MIN(`StudentResult`) AS 最低分 FROM result

-- 查询不同课程的平均分, 最高分, 最低分
-- 核心: (根据不同的课程分组)
SELECT subjectName, AVG(StudentResult) AS 平均分, MAX(StudentResult) AS 最高分, MIN(StudentResult) AS 最低分
FROM result r
INNER JOIN `subject` sub
ON r.`SubjectNO` = sub.`SubjectNo`
GROUP BY r.SubjectNo -- 通过什么字段来分组
HAVING 平均分>50

```

## 5.MySQL 函数

### 5.1 常用函数
[MySQL文档](https://dev.mysql.com/doc/refman/5.7/en/sql-function-reference.html)

```sql
-- ============== 常用函数 ==============

-- 数学运算
SELECT ABS(-8) -- 绝对值
SELECT CEILING(9.4) -- 向上取整
SELECT FLOOR(9.4) -- 向下取整
SELECT RAND() -- 返回一个 0 ~ 1 之间的随机数
SELECT SIGN() -- 判断一个数的符合 0 - 0 负数返回-1, 正数返回 1

-- 字符串函数
SELECT CHAR_LENGTH('你好!') -- 字符串长度
SELECT CONCAT('我', '爱', 'playGame') -- 拼接字符串
SELECT INSERT('我爱编程helloworld', 1, 2, '超级热爱') -- 查询, 从某个位置开始替换某个长度
SELECT LOWER('Weifc') -- 小写字母
SELECT UPPER('WeiFC') -- 大写字母
SELECT INSTR('weifc', 'h') -- 返回第一次出现的子串的索引
SELECT REPLACE('weifc说坚持就能成功', 4, 6) -- 返回指定的子字符串 (源字符串, 截取的位置, 截取的长度)
SELECT REVERSE('清晨我上马') -- 反转

-- 查询姓 周的同学, 名字 小明
SELECT REPLACE(studentname, '周', '小明') FROM student
WHERE studentname LIKE '周%'

-- 时间和日期函数 (记住)
SELECT CURRENT_DATE() -- 获取当前日期
SELECT CURDATE() -- 获取当前日期
SELECT NOW() -- 获取当前的时间
SELECT LOCALTIME() -- 本地时间
SELECT SYSDATE() -- 系统时间

SELECT YEAR(NOW())
SELECT MONTH(NOW())
SELECT DAY(NOW())
SELECT HOUR(NOW())
SELECT SECOND(NOW())

-- 系统
SELECT SYSTEM_USER()
SELECT USER()
SELECT VERSION()

```

### 5.2 聚合函数(常用)

函数名称 | 描述
---|---
Count() | 计数
SUM() | 求和
AVG() | 平均值
MAX() | 最大值
MIX() | 最小值

```sql
-- ================== 聚合函数 ==================
-- 都能够统计 表中的数据 (想查询一个表中有多少个记录, 就使用这个count())
SELECT COUNT(studentname) FROM student -- 2个数据 Count(字段), 会忽略所有的 null 值
SELECT COUNT(*) FROM student; -- Count(*), 不会忽略 null 值, 本质计算行数
SELECT COUNT(1) FROM result; -- Count(1), 不会忽略所有的 null 值 本质 计算行数

SELECT SUM(`StudentResult`) AS 总和 FROM result
SELECT AVG(`StudentResult`) AS 平均分 FROM result
SELECT MAX(`StudentResult`) AS 最高分 FROM result
SELECT MIN(`StudentResult`) AS 最低分 FROM result

```

### next