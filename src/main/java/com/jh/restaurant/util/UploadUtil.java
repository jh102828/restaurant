package com.jh.restaurant.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/5/4 9:47
 */
public class UploadUtil {

    private static final String FORMAT = "yyyy-MM-dd";

    public static String saveFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        String sufFileName = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        String fileName = getFileName() + sufFileName;
        String filePath = "E:\\IDEA\\restaurant\\src\\main\\resources\\static\\upload\\";
        File file = new File(filePath,fileName);
        multipartFile.transferTo(file);
        return  fileName;
    }
    private static String getFileName(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);
        Random random = new Random();
        return  simpleDateFormat.format(new Date())+random.nextInt(10000);
    }
}
