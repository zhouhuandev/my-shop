package com.zhouhuan.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 文件上传控制器
 *
 * @Title:UploadController
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/1/31 16:52
 */
@Controller
@RequestMapping(value = "upload")
public class UploadController {

    public static final String UPLOAD_PATH = "/static/upload/";

    @ResponseBody
    @RequestMapping(value = "uploadImg", method = RequestMethod.POST)

    public Map<String, Object> upload(MultipartFile dropzFile, MultipartFile[] editorFiles, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        //Dropzone上传
        if (dropzFile != null) {
            map.put("fileName", writeFile(dropzFile, request));
        }
        //wangEditor上传
        if (editorFiles != null && editorFiles.length > 0) {
            List<String> fileNames = new ArrayList<>();

            for (MultipartFile editorFile :
                    editorFiles) {
                fileNames.add(writeFile(editorFile, request));
            }
            // errno 即错误代码，0 表示没有错误。
            //       如果有错误，errno != 0，可通过下文中的监听函数 fail 拿到该错误码进行自定义处理
            map.put("errno", 0);
            // data 是一个数组，返回若干图片的线上地址
            map.put("data", fileNames);
        }
        return map;
    }

    /**
     * 将文件写入指定目录
     *
     * @param multipartFile
     * @param request
     * @return
     */
    private String writeFile(MultipartFile multipartFile, HttpServletRequest request) {
        //获取文件名后缀
        String fileName = multipartFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));

        //文件存放路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

        //判断路径是否存在，不存在则创建文件夹
        File file = new File(fileName);
        if (!file.exists()) {
            file.mkdir();
        }

        //将文件写入指定目录
        file = new File(filePath, UUID.randomUUID() + fileSuffix);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * Scheme: 服务器提供的协议 http / htpps
         * ServerName : 服务器名称 localhost / ip / domain
         * ServerPort : 服务器端口号
         */
        //返回文件完整路径
        //服务器地址
        String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        return serverPath + UPLOAD_PATH + file.getName();
    }
//    单个文件上传的方法
//    public Map<String, Object> upload(MultipartFile dropzFile, MultipartFile wangFile, HttpServletRequest request) {
//        Map<String, Object> map = new HashMap<>();
//
//        //判断当前是Dropzone还是wangFile
//        MultipartFile myFile = dropzFile == null ? wangFile : dropzFile;
//        //获取源文件上传的文件名
//        String fileName = myFile.getOriginalFilename();
//        //获取文件名的后缀
//        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
//        //设置文件上传路径
//        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
//
//
//        //判断并创建上传用的文件夹
//        File file = new File(filePath);
//        if (!file.exists()) {
//            file.mkdir();
//        }
//
//        //重新设置文件名为UUID，以确保唯一性
//        file = new File(filePath, UUID.randomUUID() + fileSuffix);
//        try {
//            //写入文件
//            myFile.transferTo(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (dropzFile != null) {
//            // 返回 JSON 数据，这里只带入了文件名
//            map.put("fileName", UPLOAD_PATH + file.getName());
//            return map;
//        } else {
//
//            /**
//             * Scheme: 服务器提供的协议 http / htpps
//             * ServerName : 服务器名称 localhost / ip / domain
//             * ServerPort : 服务器端口号
//             */
//            //服务器地址
//            String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//            // errno 即错误代码，0 表示没有错误。
//            //       如果有错误，errno != 0，可通过下文中的监听函数 fail 拿到该错误码进行自定义处理
//            map.put("errno", 0);
//            // data 是一个数组，返回若干图片的线上地址
//            map.put("data", new String[]{serverPath + UPLOAD_PATH + file.getName()});
//            return map;
//        }
//    }

    @ResponseBody
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public Map<String, Object> remove(String fileName, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        //服务器地址
        String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        //截掉服务器地址后的地址，以 /static/upload 开头
        String fileNameSuffix = fileName.substring(serverPath.length(), fileName.length());
        //获取当前项目路径
        String filePath = request.getSession().getServletContext().getRealPath("/");

        File file = new File(filePath + fileNameSuffix);

        //如果文件存在且是个文件的话，执行删除操作
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                map.put("msg", "文件移除成功！");
            } else {
                map.put("msg", "文件移除失败！");
            }
        } else {
            map.put("msg", "文件不存在！");
        }
        return map;
    }

}
