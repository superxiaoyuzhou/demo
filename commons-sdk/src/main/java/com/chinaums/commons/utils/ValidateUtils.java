package com.chinaums.commons.utils;

import com.chinaums.commons.ValidationException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 验证工具类
 *
 * @author xionglei
 * @create 2018-08-14 21:53
 */

public class ValidateUtils {

    /**
     * 使用hibernate的实现的JSR-303来进行验证
     *
     */
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 参数校验
     *
     * @param <T>
     */
    public static <T> void valid(T t) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (constraintViolations.size() > 0) {
            throw new ValidationException("参数校验失败：" + constraintViolations.iterator().next().getMessage());
        }
    }
}
