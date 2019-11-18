package com.chinaums.commons.utils;

import java.util.UUID;

/**
 * @auther xionglei
 * @create 2018-03-21 17:11
 */

public class UUIDUtils {

    /**
     * 获取32位uuid
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
