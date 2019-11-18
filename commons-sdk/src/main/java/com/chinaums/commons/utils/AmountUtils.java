package com.chinaums.commons.utils;

import java.math.BigDecimal;

/**
 * 金额工具类
 *
 * @author xionglei
 * @create 2018-08-14 21:53
 */

public class AmountUtils {

    /**
     * 默认进制
     */
    private static final BigDecimal DEFAULT_RADIX = new BigDecimal("100");

    /**
     * 元转分
     * @param amount
     * @return
     */
    public static long yuanToFen(String amount){
        return new BigDecimal(amount).multiply(DEFAULT_RADIX).longValue();
    }

    /**
     * 元转分
     * @param amount
     * @return
     */
    public static long yuanToFen(BigDecimal amount){
        return amount.multiply(DEFAULT_RADIX).longValue();
    }

    /**
     * 分转元
     * @param amount
     * @return
     */
    public static BigDecimal fenToYuan(long amount){
        return new BigDecimal(String.valueOf(amount)).divide(DEFAULT_RADIX).setScale(2);
    }

    /**
     * 分转元
     * @param amount
     * @return
     */
    public static BigDecimal fenToYuan(String amount){
        return new BigDecimal(amount).divide(DEFAULT_RADIX).setScale(2);
    }
}
