package com.chinaums.commons.extend.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 手机号码序列化
 * @author lyq
 * @time 2019/4/19 16:18
 */
public class MobilePhoneSerializer implements ObjectSerializer {


    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        if(o != null && StringUtils.isNotBlank(String.valueOf(o))){
            String mobilePhone = String.valueOf(o);
            if(mobilePhone.length() >= 7){
                StringBuffer sb = new StringBuffer();
                sb.append(mobilePhone.substring(0,3)).append("****").append(mobilePhone.substring(7));
                mobilePhone = sb.toString();
            }
            jsonSerializer.write(mobilePhone);
        }else {
            jsonSerializer.write("");
        }
    }
}
