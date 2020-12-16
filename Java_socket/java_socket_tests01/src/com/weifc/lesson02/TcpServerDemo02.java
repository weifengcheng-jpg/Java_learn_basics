package com.weifc.lesson02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpServerDemo02 {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(9000);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();

        FileOutputStream fos = new FileOutputStream(new File("receive.jpg"));
        byte[] buffer = new byte[1024*1024*126];
        int len;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }



        OutputStream os = socket.getOutputStream();
        os.write("接收完毕".getBytes());

        fos.close();
        is.close();
        socket.close();
        serverSocket.close();
    }

}
