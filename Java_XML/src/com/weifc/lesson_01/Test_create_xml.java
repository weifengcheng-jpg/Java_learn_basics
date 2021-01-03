package com.weifc.lesson_01;

import org.dom4j.DocumentHelper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.sql.rowset.spi.XmlWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Test_create_xml {
    public static void main(String[] args) throws IOException {
        // [1] 通过 DocumentHelper 生成一个Document对象
        Document doc = DocumentHelper.createDocument();
        // doc.addElement("books").addElement("book").addAttribute("id", "b01"); //可读性差
        // [2] 添加根元素
        Element root = doc.addElement("books");
        // [3] 为根元素添加子元素
        Element book = root.addElement("book");
        // [4] 为book元素添加属性,
        book.addAttribute("id", "b01");
        // [5] 为book 添加子元素
        Element name = book.addElement("name");
        Element author = book.addElement("author");
        Element price = book.addElement("price");
        // [6] 为子元素添加文本信息
        name.addText("Thinking in Java");
        author.addText("小明");
        price.addText("88");
//        // [7] 将doc输出到XML文件中即可
//        Writer writer = new FileWriter(new File("src/com/weifc/lesson_01/book.xml"));
//        doc.write(writer);
//        // [8] 关闭资源
//        writer.close();

        // [7] 生称成格式良好的xml
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileWriter(new File("src/com/weifc/lesson_01/book.xml")), format);
        writer.write(doc);
        // [8] 关闭资源
        writer.close();
    }
}
