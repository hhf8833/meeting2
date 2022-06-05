package com.stylefeng.guns.rest.common.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "ftp")
public class FTPUtil {

    //地址、端口、用户名、密码
    private String hostName;
    private Integer port;
    private String userName;
    private String password;
    private FTPClient ftpClient = null;
    private String uploadPath;

    private void initFTPClient(){
        try {
           ftpClient = new FTPClient();
           ftpClient.setControlEncoding("utf-8");
           ftpClient.connect(hostName,port);
           ftpClient.login(userName,password);
        }catch (Exception e){
            log.error("初始化ftp失败",e);
        }
    }

    //输入一个路径，然后将路径内的文件转为字符串返回
    public String getFileStrByAddress(String fileAddress){
        BufferedReader bufferedReader = null;
        try{

            initFTPClient();

            bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            ftpClient.retrieveFileStream(fileAddress))
            );

            StringBuffer stringBuffer = new StringBuffer();
            while(true){
                String lineStr = bufferedReader.readLine();
                if(lineStr == null){
                    break;
                }
                stringBuffer.append(lineStr);
            }

            ftpClient.logout();
            return stringBuffer.toString();
        }catch (Exception e){
            log.error("获取文件信息失败",e);
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //上传文件
    public boolean uploadFile(String fileName, File file){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            //初始化ftp
            initFTPClient();
            //设置ftp参数
            ftpClient.setControlEncoding("utf-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //此方法仅用于服务器间的数据传输。
            ftpClient.enterLocalPassiveMode();

            String workingDirectory = ftpClient.printWorkingDirectory();
            log.info("ftp工作地址为：{}",workingDirectory);
            //更改ftp工作空间
            boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory(this.getUploadPath());
            if (!changeWorkingDirectory){
                boolean b = ftpClient.makeDirectory(this.getUploadPath());
                log.info("创建文件夹：{}",b);
                boolean changeWorkingDirectory2 = ftpClient.changeWorkingDirectory(this.getUploadPath());
                log.info("workingDirectory：{}",changeWorkingDirectory2);
            }
            //System.out.println(this.getUploadPath());
            if (!changeWorkingDirectory){
                log.error("更改ftp工作空间失败");
            }

            //上传文件
            boolean storeFile = ftpClient.storeFile(fileName, fileInputStream);
            if (storeFile){
                return true;
            }else {
                log.error("ftp上传文件失败");
                return false;
            }

        }catch (Exception e){
            log.error("二维码上次失败");
            return false;
        }finally {
            try {
                ftpClient.logout();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FTPUtil ftpUtil = new FTPUtil();
        String fileStrByAddress = ftpUtil.getFileStrByAddress("seats/cgs.json");

        System.out.println(fileStrByAddress);
    }
}
