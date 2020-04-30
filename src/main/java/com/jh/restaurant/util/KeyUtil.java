package com.jh.restaurant.util;

import java.util.Random;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/14 22:17
 */
public class KeyUtil {

    /**
     * 生成唯一的Key
     * @return 时间+随机数格式的Key
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        int number = random.nextInt(10000);
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
