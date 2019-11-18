package com.chinaums.commons.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;

/**
 * 流水号生产工具
 * author lihongwei
 */
public class SerialNumGeneratorUtil {

    private static String seriaNoPrefix = "";
    private final static ThreadLocal<DateFormat> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<>();
    private final static ThreadLocal<DecimalFormat> DECIMAL_FORMAT_THREAD_LOCAL = new ThreadLocal<>();
    private final static LongAdder LONG_ADDER = new LongAdder();

    public static String generateSeqNo() {
        return generateSeqNo(null);
    }
    public static String generateSeqNo(String sysCode) {
        StringBuilder stringBuilder = new StringBuilder();
        // 添加序列号前缀 ryze
        stringBuilder.append(Optional.ofNullable(sysCode).orElse(seriaNoPrefix));
        // 添加时间戳
        DateFormat dateFormat = DATE_FORMAT_THREAD_LOCAL.get();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("yyMMddHHmmss");
            DATE_FORMAT_THREAD_LOCAL.set(dateFormat);
        }
        String millisFormat = dateFormat.format(System.currentTimeMillis());
        stringBuilder.append(millisFormat);
        // 添加自增序列
        DecimalFormat decimalFormat = DECIMAL_FORMAT_THREAD_LOCAL.get();
        if (decimalFormat == null) {
            decimalFormat = new DecimalFormat("000000");
            DECIMAL_FORMAT_THREAD_LOCAL.set(decimalFormat);
        }
        stringBuilder.append(decimalFormat.format(LONG_ADDER.longValue()));
        LONG_ADDER.increment();
        String seriaNo = stringBuilder.toString();
        return seriaNo;
    }

}
