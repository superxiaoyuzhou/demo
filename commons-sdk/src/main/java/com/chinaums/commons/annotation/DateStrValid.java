package com.chinaums.commons.annotation;

/**
 * 日期字符串校验
 * @author lyq
 * @time 2019/10/16
 */

import com.chinaums.commons.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Objects;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateStrValid.Validator.class)
public @interface DateStrValid {

    String message() default "日期格式错误";

    String pattern() default "yyyyMMdd";

    String[] exclude() default {};

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Slf4j
    class Validator implements ConstraintValidator<DateStrValid, Object> {

        private String pattern;

        private String[] exclude;

        @Override
        public void initialize(DateStrValid dateStrValid) {
            this.pattern = dateStrValid.pattern();
            this.exclude = dateStrValid.exclude();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            if (Objects.isNull(value) || Arrays.asList(this.exclude).contains(String.valueOf(value))) {
                return true;
            } else {
                return DateUtils.isDate(String.valueOf(value), this.pattern);
            }
        }
    }
}
