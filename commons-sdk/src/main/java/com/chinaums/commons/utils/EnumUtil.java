package com.chinaums.commons.utils;

import com.chinaums.commons.BaseEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 枚举工具类
 *
 * @author xionglei
 * @create 2018-07-03 12:22
 * @update LiuLi
 * @update 2018/11/16 13:43:14
 */

public class EnumUtil {

    /**
     * 获取枚举
     * @param cls
     * @param code
     * @return
     * @throws Exception
     */
    public static BaseEnum getWithCode(Class<?> cls, String code) throws Exception {
        // 实例化这个类
        BaseEnum[] values = (BaseEnum[]) cls.getMethod("values").invoke(new Object(), new Object[]{});
        for (BaseEnum item : values) {
            if (StringUtils.equals(code, item.getCode())) {
                return item;
            }
        }
        return null;
    }

    /**
     * 枚举中，是否包含此Code的枚举
     *
     * @param cls class extends com.chinaums.ework.approval.enums.BaseEnum
     * @param code code
     * @author LiuLi
     * @date 2018/11/16 13:31
     * @return true：包含；false：不包含
     */
    public static boolean isContains(Class<? extends BaseEnum> cls, String code){
        if(StringUtils.isBlank(code)) {
            return false;
        } else {
            BaseEnum[] baseEnums = cls.getEnumConstants();
            if(baseEnums == null || baseEnums.length == 0) {
                return false;
            }

            for (BaseEnum baseEnum : baseEnums) {
                if (StringUtils.equals(baseEnum.getCode(), code)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 枚举中，是否包含下列所有Code的枚举
     *
     * @param cls class extends com.chinaums.ework.approval.enums.BaseEnum
     * @param codes code集合
     * @author LiuLi
     * @date 2018/11/16 14:13
     * @return true：均包含；false：有不包含的项
     */
    public static boolean isContains(Class<? extends BaseEnum> cls, List<String> codes) {
        if(CollectionUtils.isEmpty(codes)) {
            return false;
        } else {
            boolean result;
            for (String code : codes) {
                result = isContains(cls, code);
                if(result) {
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

}
