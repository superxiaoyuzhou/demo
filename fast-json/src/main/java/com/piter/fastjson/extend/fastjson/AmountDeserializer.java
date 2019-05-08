package com.piter.fastjson.extend.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * 金额反序列化
 *
 * @author XL
 * @create 2019-04-15 10:56
 */

public class AmountDeserializer implements ObjectDeserializer {

    private static final String pattern = "###,###,###,###,##0.00";

    @Override
    public BigDecimal deserialze(DefaultJSONParser defaultJSONParser, Type type, Object fieldName) {
        Object obj = defaultJSONParser.parse();
        if(obj != null && StringUtils.isNotBlank(String.valueOf(obj))){
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            try {
                return new BigDecimal(String.valueOf(decimalFormat.parse(String.valueOf(obj))));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }else {
            return null;
        }
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
