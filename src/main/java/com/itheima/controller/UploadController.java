package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author jsc
 * @version 1.0
 */
@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传：{},{},{}",username,age,image);
////        /Users/jsc/Pictures
//        String filename = image.getOriginalFilename();
//        int start = filename.lastIndexOf(".");
//        String extendedFileName = filename.substring(start);
//        String path = UUID.randomUUID() + extendedFileName;
//        image.transferTo(new File("/Users/jsc/Pictures/" + path));
//        log.info("File name:" + "/Users/jsc/Pictures" + path);
//        return Result.success();
//    }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException, ClientException {
        log.info("文件上传，文件名：{}",image.getOriginalFilename());

        String url = aliOSSUtils.upload(image);

        log.info("文件上传成功，文件访问url： {}",url);
        return Result.success(url);
    }
}
