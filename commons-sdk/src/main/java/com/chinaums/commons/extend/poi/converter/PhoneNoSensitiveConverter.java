package com.chinaums.commons.extend.poi.converter;

import org.apache.commons.lang3.StringUtils;

/**
 * 手机号脱敏转换器
 *
 * @author XL
 * @create 2019-04-25 18:42
 */

public class PhoneNoSensitiveConverter implements FieldConverter {

    @Override
    public Object convert(Object o) {
        if(o != null && StringUtils.isNotBlank(String.valueOf(o))){
            String mobilePhone = String.valueOf(o);
            if(mobilePhone.length() >= 7){
                StringBuffer sb = new StringBuffer();
                sb.append(mobilePhone.substring(0,3)).append("****").append(mobilePhone.substring(7));
                mobilePhone = sb.toString();
            }
            return mobilePhone;
        }else {
            return "";
        }
    }
}
