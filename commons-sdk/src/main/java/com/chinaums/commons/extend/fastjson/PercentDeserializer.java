package com.chinaums.commons.extend.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * 百分比反序列化
 *
 * @author XL
 * @create 2019-04-15 10:56
 */

public class PercentDeserializer implements ObjectDeserializer {

    @Override
    public BigDecimal deserialze(DefaultJSONParser defaultJSONParser, Type type, Object fieldName) {
        Object obj = defaultJSONParser.parse();
        if(obj != null && StringUtils.isNotBlank(String.valueOf(obj))){
            return new BigDecimal(String.valueOf(obj)).divide(new BigDecimal("100"));
        }else {
            return null;
        }
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
