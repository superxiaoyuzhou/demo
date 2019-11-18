package com.chinaums.dataencrypt.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiXun
 * @date 2019-07-25 13:40
 */
public class CommonUtil {

  public static String fullStr(String data,char fullChar,int len){
    if(data.length()>=len){
      return data;
    }
    StringBuffer sb = new StringBuffer();
    for(int i = data.length();i<len;i++){
      sb.append(fullChar);
    }
    sb.append(data);
    return sb.toString();
  }



  public static void main(String[] args) {
    System.out.println(fullStr("1",'0',9));
  }

  public static Field[] getAllField(Class clazz) {
    List<Field> list = new ArrayList<>();
    list.addAll(Arrays.asList(clazz.getDeclaredFields()));

    Class<?> superclass = clazz.getSuperclass();
    if (superclass != null && superclass != Object.class) {
      list.addAll(Arrays.asList(getAllField(superclass)));
    }
    return list.toArray(new Field[list.size()]);
  }

}
