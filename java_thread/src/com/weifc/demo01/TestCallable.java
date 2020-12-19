package com.weifc.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;
import java.net.URL;

//线程创建方式三: 实现callable 接口
/*

 */
public class TestCallable implements Callable<Boolean> {
    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    //下载图片线程的执行体
    @Override
    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名: " + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://img1.360buyimg.com/n6/jfs/t1/121459/34/10995/64019/5f45f849E44c6b9d1/63bc28e26312b31b.jpg", "i310100f.jpg");
        TestCallable t2 = new TestCallable("https://img1.360buyimg.com/n6/jfs/t1/121459/34/10995/64019/5f45f849E44c6b9d1/63bc28e26312b31b.jpg", "i3.jpg");
        TestCallable t3 = new TestCallable("https://img1.360buyimg.com/n6/jfs/t1/121459/34/10995/64019/5f45f849E44c6b9d1/63bc28e26312b31b.jpg", "i3_03.jpg");

        //创建执行服务:
        ExecutorService ser = Executors.newFixedThreadPool(3);

        //提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        //获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        //关闭服务
        ser.shutdownNow();
    }
}

//下载器
class WebDownloader {
    // 下载方法
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Io异常, downloader 方法出现问题");
        }
    }
}

