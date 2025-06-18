package com.xupt.hms;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageClient {
    public static void main(String[] args) {
        String hostname = "192.168.112.83";
        int port = 59988;
        String imagePath = "D:/Code/HMS/Photo/person/caozhexuan.png"; // 替换为你想发送的图片路径

        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected to server");

            // 读取图片文件
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

            // 将图片转换为Base64编码
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // 准备发送的数据
            String imageData = "data:image/jpeg;base64," + base64Image;
            byte[] imageDataBytes = imageData.getBytes();

            // 发送图片大小
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.writeInt(imageDataBytes.length);
            System.out.println(imageDataBytes.length);
            dos.flush();

            // 发送图片数据
            dos.write(imageDataBytes);
            dos.flush();

            // 接收响应
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            System.out.println("Server response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}