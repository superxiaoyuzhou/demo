package com.chinaums.dataencrypt.interceptor;

import com.chinaums.dataencrypt.annotation.Encrypt;
import com.chinaums.dataencrypt.annotation.EncryptData;
import com.chinaums.dataencrypt.util.CommonUtil;
import com.chinaums.dataencrypt.util.DataSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author LiXun
 * @date 2019-07-26 10:42
 */
@Slf4j
@Intercepts({
    @Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class}),
    @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class}),
})
public class DataEncryptInterceptor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object target = invocation.getTarget();

    if (target instanceof ParameterHandler) {
      // 拦截的是ParameterHandler
      // mybatis-plus填充key会调用一个后缀为!selectKey的方法。跳过这个，否则会造成加密两次
      ParameterHandler parameterHandler = (ParameterHandler) target;
      Field mappedStatementField = parameterHandler.getClass().getDeclaredField("mappedStatement");
      mappedStatementField.setAccessible(true);
      MappedStatement mappedStatement = (MappedStatement) mappedStatementField.get(parameterHandler);
      if (mappedStatement.getId().endsWith("!selectKey")) {
        return invocation.proceed();
      }
      Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
      parameterField.setAccessible(true);
      Object parameterObject = parameterField.get(parameterHandler);
      // 加密后入库
      this.parameterObjectEncrypt(parameterObject);
      // 处理业务
      Object result = invocation.proceed();
      // 入库完成后要对源对象解密，否则后续业务拿到的是密文
      this.parameterObjectDecode(parameterObject);
      return result;
    } else if (target instanceof ResultSetHandler) {
      // 拦截的是ResultSetHandler
      Object result = invocation.proceed();
      if (result instanceof Collection && result != null && ((Collection) result).size() != 0) {
        ((Collection) result).forEach(item -> this.decode(item));
      } else if (result instanceof Map && result != null && ((Map) result).size() != 0) {
        ((Map) result).forEach((k, v) -> this.decode(v));
      } else {
        this.decode(result);
      }
      return result;
    } else {
      return invocation.proceed();
    }
  }

  @Override
  public Object plugin(Object o) {
    return Plugin.wrap(o, this);
  }

  @Override
  public void setProperties(Properties properties) {

  }

  private void parameterObjectEncrypt(Object parameterObject) {
    // 修改的时候是一个map，在et中
    if (parameterObject instanceof Map) {
      Map parameterMap = (Map) parameterObject;
      Object et = parameterMap.getOrDefault("et", null);
      if (et != null) {
        if (et instanceof Collection && ((Collection) et).size() != 0) {
          ((Collection) et).forEach(item -> this.encrypt(item));
        } else if (et instanceof Map && ((Map) et).size() != 0) {
          ((Map) et).forEach((k, v) -> this.encrypt(v));
        } else {
          this.encrypt(et);
        }
      }
    } else {
      this.encrypt(parameterObject);
    }
  }

  private void parameterObjectDecode(Object parameterObject) {
    // 修改的时候是一个map，在et中
    if (parameterObject instanceof Map) {
      Map parameterMap = (Map) parameterObject;
      Object et = parameterMap.getOrDefault("et", null);
      if (et != null) {
        if (et instanceof Collection && ((Collection) et).size() != 0) {
          ((Collection) et).forEach(item -> this.decode(item));
        } else if (et instanceof Map && ((Map) et).size() != 0) {
          ((Map) et).forEach((k, v) -> this.decode(v));
        } else {
          this.decode(et);
        }
      }
    } else {
      this.decode(parameterObject);
    }
  }

  private void encrypt(Object obj) {
    if(obj == null){
      return;
    }
    Class clazz = obj.getClass();
    if (clazz.getAnnotation(EncryptData.class) == null) {
      return;
    }

    // 加密
    getEncryptFields(clazz)
        .forEach(field -> {
          try {
            String value = (String) field.get(obj);
            if(value != null) {
              value = DataSecurityUtil.encrypt(value);
              field.set(obj, value);
            }
          } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
          }
        });
  }

  private void decode(Object obj) {
    if(obj == null){
      return;
    }
    Class clazz = obj.getClass();
    if (clazz.getAnnotation(EncryptData.class) == null) {
      return;
    }
    getEncryptFields(clazz).forEach(field -> {
      try {
        String value = (String) field.get(obj);
        if(value != null){
          value = DataSecurityUtil.decrypt(value);
          field.set(obj, value);
        }
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    });
  }

  private static Map<Class, List<Field>> encryptFieldsMap = new HashMap<>();

  private static List<Field> getEncryptFields(Class clazz) {
    if (encryptFieldsMap.containsKey(clazz)) {
      return encryptFieldsMap.get(clazz);
    }
    List<Field> result = Arrays.stream(CommonUtil.getAllField(clazz))
        .filter(field -> field.getAnnotation(Encrypt.class) != null)
        .collect(Collectors.toList());
    result.forEach(field -> field.setAccessible(true));
    encryptFieldsMap.put(clazz, result);
    return result;
  }


}
