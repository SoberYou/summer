package com.cq.summer.controller;

 

import java.io.FileInputStream;

import java.io.IOException;

import java.io.InputStream;

import java.net.SocketException;

import java.util.UUID;

 

import org.apache.commons.net.ftp.FTP;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;


public class FtpUtil {

 

    public static String upload(String hostname,String username,
                                String password,String targetPath,
                                String suffix,InputStream inputStream) throws SocketException, IOException {

        //实例化ftpClient
        FTPClient ftpClient = new FTPClient();
        //1.连接服务器
        ftpClient.connect(hostname);
        //2.登录（指定用户名和密码）
        ftpClient.login(username, password);
        //基本路径，一定存在
        String basePath = "/";
        String[] pathArray = targetPath.split("/");

        for (String path : pathArray) {
            basePath += path + "/";
            //3.指定目录 返回布尔类型 true表示该目录存在
            boolean dirExsists = ftpClient.changeWorkingDirectory(basePath);
            //4.如果指定的目录不存在，则创建目录
            if (!dirExsists) {
                //此方式，每次，只能创建一级目录
                ftpClient.makeDirectory(basePath);
            }
        }

        //重新指定上传文件的路径
        ftpClient.changeWorkingDirectory(targetPath);
        //5.设置上传文件的方式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //使用uuid，保存文件名唯一性
        String uuid = UUID.randomUUID().toString();

        /**
         * 6.执行上传
         * remote 上传服务后，文件的名称
         * local 文件输入流
         * 上传文件时，如果已经存在同名文件，会被覆盖
         */
        ftpClient.storeFile(uuid + suffix, inputStream);
        return uuid + suffix;
    }

 

    /**
     * 根据文件名，获取文件后缀
     * @param args
     * @throws SocketException
     * @throws IOException
     */

    public static String getSuffix(String filename){
        String suffix = "";
        int fnum = filename.lastIndexOf(".");
        if(fnum>0){
        suffix = filename.substring(fnum);
        }
        return suffix;
    }

 

 

    public static void main(String[] args) throws SocketException, IOException {


        InputStream inputStream = new FileInputStream("F:/sandro/2020-08-01～2020-08-07.txt");



        String remoteFilename = upload("192.168.0.60", "summer", "summer123", "/home/summer/cq/test", ".txt", inputStream);

        System.out.println(remoteFilename);

    }

}

 