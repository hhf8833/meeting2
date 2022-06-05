package com.hhf.seckilldemo.utils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhf.seckilldemo.pojo.User;
import com.hhf.seckilldemo.vo.RespBean;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {
    private  static void  createUser(int count) throws SQLException, IOException {
        List<User> userList= new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId(13000000000L+i);
            user.setNickname("user"+i);
            user.setSalt("1a2b3c4d");
            user.setPassword(MD5Util.inputPwdToDBPwd("123456",user.getSalt()));

            user.setLoginCount(1);
            user.setRegisterDate(new Date());
            userList.add(user);
        }
        System.out.println("创建用户");
        Connection connection = getConn();
        String sql = "insert into seckill.t_user(login_count, nickname, register_date, salt, password, id)values(?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            pstmt.setInt(1, user.getLoginCount());
            pstmt.setString(2, user.getNickname());
            pstmt.setTimestamp(3, new Timestamp(user.getRegisterDate().getTime()));
            pstmt.setString(4, user.getSalt());
            pstmt.setString(5, user.getPassword());
            pstmt.setLong(6, user.getId());
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        pstmt.close();
        connection.close();
        System.out.println("insert to db");
        //登录，生成token
        String urlString = "http://localhost:8080/login/doLogin";
        File file = new File("C:\\Users\\HP\\Desktop\\新建文件夹\\config.txt");
        if (file.exists()) {
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        file.createNewFile();
        raf.seek(0);
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            URL url = new URL(urlString);
            HttpURLConnection co = (HttpURLConnection) url.openConnection();
            co.setRequestMethod("POST");
            co.setDoOutput(true);
            OutputStream out = co.getOutputStream();
            String params = "mobile=" + user.getId() + "&password=" +
                    MD5Util.inputPwdToFirstPwd("123456");
            out.write(params.getBytes());
            out.flush();
            InputStream inputStream = co.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte buff[] = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buff)) >= 0) {
                bout.write(buff, 0, len);
            }
            inputStream.close();

            bout.close();
            String response = new String(bout.toByteArray());
            ObjectMapper mapper = new ObjectMapper();
            RespBean respBean = mapper.readValue(response, RespBean.class);
            String userTicket = ((String) respBean.getObject());
            System.out.println("create userTicket : " + user.getId());
            String row = user.getId() + "," + userTicket;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("write to file : " + user.getId());
        }
        raf.close();
        System.out.println("over");
    }

    private static Connection getConn() throws SQLException {
        String  url= "jdbc:mysql://localhost:3306/seckill?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";

        String username="root";
        String password = "0327";
        String driver = "com.mysql.cj.jdbc.Driver";
        return DriverManager.getConnection(url,username,password);
    }

    public static void main(String[] args) throws SQLException, IOException {
        createUser(5000);
    }
}
