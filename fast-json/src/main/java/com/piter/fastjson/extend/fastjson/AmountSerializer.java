package com.piter.fastjson.extend.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 金额序列化
 *
 * @author XL
 * @create 2019-04-15 10:56
 */

public class AmountSerializer implements ObjectSerializer {

    private static final String pattern = "###,###,###,###,##0.00";

    @Override
    public void write(JSONSerializer jsonSerializer, Object obj, Object fieldName, Type fieldType, int features) throws IOException {
        if(obj != null && StringUtils.isNotBlank(String.valueOf(obj))){
            BigDecimal amount = new BigDecimal(String.valueOf(obj));
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            jsonSerializer.write(decimalFormat.format(amount));
        }else {
            jsonSerializer.write("");
        }
    }
}
