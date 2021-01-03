# XML

## 目录

- **认识XML**
- 定义
- 编辑
- 作用

- **验证XML**
- DTD
- XSD
- **处理XML**
- XML处理技术
- 使用DOM解析XML
- 使用DOM4解析XML

## 认识XML
- **什么是XML**
- 可扩展标记语言(Extensible Markup Language)
- 使用简单的标记来描述数据
```xml
<?xml version="1.0" encoding="UTF-8"?>
<books>
<!--图书信息-->
    <books>
        <author>weifc</author>
        <title>C#</title>
        <description>包括C#框架和网络编程等</description>
    </books>

</books>

```

## 1.1 xml简介

a) xml， eXtensible Markup Language,可扩展标记语言.是一种标记语言.

b) xml是一种非常灵活的语言，没有固定的标签,所有的标签都可以自定义.

c)通常，xml被用于信息的记录和传递. 因此, XML 经常被用于充当配置文件

## 1.2 格式良好的XML
a) 声明信息, 用于描述, 版本和编码方式
```xml
<?xml version="1.0" encoding="UTF-8"?>

```
b) XML  有仅有一个根元素
```xml
<books>

</books>

```
books 是根元素

c) xml 是大小写敏感的

d) 标签是成对的，而且要正确嵌套。

XML_练习
```xml
<?xml version="1.0" encoding="UTF-8"?>
<student>
    <ID = "001">
    <name>王同</name>
    <course>java</course>
    <performance>89</performance>
    </ID>
    <ID = "002">
    <name>李四</name>
    <course>SQL</course>
    <performance>83</performance>
    </ID>
    
</student>

```

# 2.DTD

## 2.1 DTD 简介
a) DTD, Document Type Definition, 文档类型定义

b) DTD 用于约束XML的文档格式, 保证xml是一个有效的XML.

c) DTD 可以分为两种, 内部DTD, 外部 DTD

[点击, 更加了解我](https://www.w3school.com.cn/dtd/dtd_attributes.asp)

## 2.2 使用DTD
a) 内部DTD的定义, 语法如下:
```xml
<!DOCTYPE 根元素 [元素声明]>

```
b) 元素声明语法:
```xml
<!ELEMENT 元素名 (子元素[, 子元素 ...])>

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 声明内部DTD -->
<!DOCTYPE scores[
    <!ELEMENT scores (student*)>
]>
<students>

    <student id= "001">
    <name>王同</name>
    <course>java</course>
    <student>89</student>
    </ID>
    <student id= "002">
    <name>李四</name>
    <course>SQL</course>
    <student>83</student>
    </ID>

</students>
```
[点击, 更加了解我](https://www.w3school.com.cn/dtd/dtd_attributes.asp)

c) 数量词

==+: 表示出现1次或多次, 至少多次==

==?: 表示出现0次或1次==

==*: 表示出现任意次==

### 2.2.1 内部DTD的定义
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 声明内部DTD -->
<!DOCTYPE scores[
    <!ELEMENT scores (student+)>
]>
<students>

    <student id= "001">
    <name>王同</name>
    <course>java</course>
    <student>89</student>
    </ID>
    <student id= "002">
    <name>李四</name>
    <course>SQL</course>
    <student>83</student>
    </ID>

</students>
```

[点击, 更加了解我](https://www.w3school.com.cn/dtd/dtd_attributes.asp)

d) 声明属性

属性声明使用下列语法：

```xml
<!ATTLIST 元素名称 属性名称 属性类型 默认值>

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 声明内部DTD -->
<!DOCTYPE scores [
    <!ELEMENT scores (student+)>
    <!ELEMENT student (name, course, scores)>
    <!ATTLIST student id CDATA #REQUIRED>
]>
<scoress>

    <student id= "001">
        <name>王同</name>
        <course>java</course>
        <scores>89</scores>
    </student>

    <student id= "002">
        <name>李四</name>
        <course>SQL</course>
        <scores>83</scores>
    </student>

</scoress>

```

[点击, 更加了解我](https://www.w3school.com.cn/dtd/dtd_attributes.asp)

d) 属性声明语法:
> 属性类型: CDATA, 表示字符数据(character data)

> 默认值:
- #REQUIRED, 表示必须出现
- #IMPLIED, 表示不是必须的

```xml
<!ATTLIST 元素名称 属性名称 属性类型 默认值>

```

e) 带DTD的完整xml代码:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 声明内部DTD -->
<!DOCTYPE scores [
    <!ELEMENT scores (student+)>
    <!ELEMENT student (name, course, score)>
    <!ATTLIST student id CDATA #REQUIRED>
    <!ELEMENT name (#PCDATA)>
    <!ELEMENT course (#PCDATA)>
    <!ELEMENT score (#PCDATA)>
]>
<scores>
    <student id= "001">
        <name>王同</name>
        <course>java</course>
        <score>89</score>
    </student>
    <student id= "002">
        <name>李四</name>
        <course>SQL</course>
        <score>83</score>
    </student>
</scores>

```

### 2.2.2 外部DTD的定义

a) 创建一个独立的xxx.dtd文件

```dtd
<?xml version="1.0" encoding="UTF-8"?>
<!ELEMENT scores (student+)>
<!ELEMENT student (name, course, scores)>
<!ATTLIST student id CDATA #REQUIRED>
<!ELEMENT name (#PCDATA)>
<!ELEMENT course (#PCDATA)>
<!ELEMENT scores (#PCDATA)>

```

b) 在 xml 中引入外部DTD文件
```xml
<!-- 引入外部DTD文件 -->
<!DOCTYPE scores SYSTEM "./scores.dtd">
```

==空格也很重要==

## 3XML 的解析

对 xml 文件进行操作, 包括创建 xml, 对 xml 文件进行增删改查操作.

### 3.1 常见的XML 解析技术

- XML 常用解析技术

解析技术 | 特点
---|---
DOM | 基于XML树结构, 比较耗资源
SAX | 基于事件 消耗资源小. 使用与数据量较大的XML
JDOM | 开放源码, 比DOM更快. JDOM仅使用具体类而不使用接口
DOM4J | 开放源码 非常优秀的Java XML API, 性能优异, 功能强大使用接口而不是实现类


#### 3.1.1 DOM解析
是官方提供的解析方式, 基于 xml 树解析的 (原生)

#### 3.1.2 SAX 解析
是民间的解析方式, 基于事件的解析 (原生)->也就是要自己写很多代码, 比较麻烦, 效率低

#### 3.1.3 JDOM 解析
第三方提供, 开源免费的解析方式, 比DOM解析快

#### 3.1.4 DOM4J
第三方提供, 开源免费, 是 JDOM 升级版

**DOM4J**

dom4j是一个Java的XML API，是jdom的升级品，用来读写XML文件的。dom4j是一个十分优秀的JavaXML API，具有性能优异、功能强大和极其易使用的特点，它的性能超过sun公司官方的dom技术，同时它也是一个开放源代码的软件，可以在SourceForge上找到它。在IBM developerWorks上面还可以找到一篇文章，对主流的Java XML API进行的性能、功能和易用性的评测，所以可以知道dom4j无论在哪个方面都是非常出色的。如今可以看到越来越多的Java软件都在使用dom4j来读写XML，特别值得一提的是连Sun的JAXM也在用dom4j。这已经是必须使用的jar包， Hibernate也用它来读写配置文件。

(来自百度百科)

### 3.2 DOM4J 解析 XML

解析 XML 的入口, 是需要先拿到一个 Document 对象

#### 3.2.1 

**p1 01_认识xml**

**p2 02_使用xml**

**p3 03_dom4j_读取xml**

**p4 04_demo4j_生成xml**

[点击我, 看视频](https://www.bilibili.com/video/BV1tW411o7Fd?p=1)

