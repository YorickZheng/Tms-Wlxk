package com.wlxk.controller.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 马林 on 2016/8/28.
 */
@Controller
@RequestMapping("/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final static String prefix = "E:/file/";

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String provideUploadInfo() {
        return "请使用POST方式请求!";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String testFileUpload(@RequestParam("name") String name,
                                 @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            BufferedOutputStream bos = null;
            try {
                String dir_uri = prefix + name + "-uploaded/";
                File dir = new File(dir_uri);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                byte[] bytes = file.getBytes();
                bos = new BufferedOutputStream(new FileOutputStream(new File(dir_uri + file.getName())));
                bos.write(bytes);
                return "文件上传成功 " + name + " 至 " + name + "-uploaded !";
            } catch (Exception e) {
                e.printStackTrace();
                return "文件上传失败 " + name + " => " + e.getMessage();
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return "文件上传失败 " + name + " 因为文件为空.";
        }
    }
}
