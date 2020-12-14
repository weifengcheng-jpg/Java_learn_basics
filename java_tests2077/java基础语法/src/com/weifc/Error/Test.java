package com.weifc.Error;

public class Test {
    public static void main(String[] args) {
        int a = 1;
        int b = 0;

        // 假设要铺获多个异常, 从小到大范围

        try { // try 监控区域
            System.out.println(a/b);
        } catch (Error e) { // catch 捕获异常
            System.out.println("Error");
        } catch (Exception e) {
            System.out.println("Exception");
        } catch (Throwable t) {
            System.out.println("Throwable");
        } finally { // 处理善后工作
            System.out.println("finally");
        }

        // 总结: 从范围少到范围大 catch
        // finally 可以不用finally , 假设IO, 资源, 关闭
    }

     
}
