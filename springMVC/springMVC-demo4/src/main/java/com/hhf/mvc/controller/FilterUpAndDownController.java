package com.hhf.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class FilterUpAndDownController {
    @RequestMapping("/testDown")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("/static/img/11.png");
        //创建输入流
        InputStream is = new FileInputStream(realPath);
        //创建字节数组 available()]获取输入流所有的字节
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字 attachment 附件形式
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }
    @RequestMapping("/testUp")
    public String testUp(MultipartFile photo , HttpSession session) throws IOException {

        String filename = photo.getOriginalFilename();
        //获取后缀,并用时间戳作为文件名字
        String suffixName = filename.substring(filename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        filename = uuid+suffixName;
        //获取服务器中文件的真实路径
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("photo");
        File file = new File(realPath);
        //photo文件夹可能不存在做出判断不存在要创建
        if (!file.exists()){
            file.mkdir();
        }
        String finalPath = realPath+File.separator+filename;
        photo.transferTo(new File(finalPath));
        return "success";
    }
}
