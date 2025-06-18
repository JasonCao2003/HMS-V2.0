package com.xupt.hms.controller;

import com.alibaba.fastjson.JSON;
import com.xupt.hms.pojo.Users;
import com.xupt.hms.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Controller
public class LoginFaceController {

    private String username;

    @Resource
    private UsersService usersService;

    @RequestMapping("/gofaceidenty")
    public String gofaceidenty() {
        return "Utils/faceidenty";
    }

    //    private static final String SERVER_ADDRESS = "192.168.1.105";
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    private boolean stopSending = false;
    private final ReentrantLock lock = new ReentrantLock();
    private Socket socket;

    private void createSocketConnection() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            // 如果连接失败,可以考虑重试或抛出异常
        }
    }

    @RequestMapping("/userFaceLogin")
    public ResponseEntity<Map<String, Object>> userFaceLogin(@RequestParam("imageData") String imageData,
                                                             HttpSession session, Model model, HttpServletResponse resp) throws IOException {
        lock.lock();
        Map<String, Object> responseMap = new HashMap<>();
        try {
            if (socket == null || socket.isClosed()) {
                createSocketConnection();
            }
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            if (!stopSending) {
                byte[] imageDataBytes = imageData.getBytes();
                dos.writeInt(imageDataBytes.length);
                dos.flush();
                dos.write(imageDataBytes);
                dos.flush();

                DataInputStream in = new DataInputStream(socket.getInputStream());
                byte[] responseData = new byte[1024];
                int bytesRead = in.read(responseData);
                String response = new String(responseData, 0, bytesRead);
                if (!response.contains("waiting")) {
                    String[] parts = response.split("\\s+");
                    username = parts[0];
                    stopSending = true;
                }
            } else {
                dos.writeInt(0);
                dos.flush();
                Users user = usersService.userLogin(username, "face");
                session.setAttribute("user", user);
                model.addAttribute("myuserinfo", user);
                username = null;
                stopSending = false;
                socket.close();
                responseMap.put("redirect", "/userhome"); // 设置重定向路径
                return ResponseEntity.ok(responseMap); // 返回包含重定向信息的响应
            }
        } finally {
            lock.unlock();
        }
        responseMap.put("status", "waiting");
        return ResponseEntity.ok(responseMap); // 返回等待状态
    }


}
