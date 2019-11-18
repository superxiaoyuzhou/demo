package com.chinaums.commons.extend.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * 百分比序列化
 *
 * @author XL
 * @create 2019-04-15 10:56
 */

public class PercentSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object obj, Object fieldName, Type fieldType, int features) throws IOException {
        if(obj != null && StringUtils.isNotBlank(String.valueOf(obj))){
            BigDecimal value = new BigDecimal(String.valueOf(obj));
            jsonSerializer.write(value.multiply(new BigDecimal("100")).doubleValue());
        }else {
            jsonSerializer.write("");
        }
    }
}
