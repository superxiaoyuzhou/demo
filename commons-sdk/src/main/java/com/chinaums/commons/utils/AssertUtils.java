package com.chinaums.commons.utils;

import com.chinaums.commons.BaseResult;
import com.chinaums.commons.BusinessException;
import com.chinaums.commons.ValidationException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * 断言工具类
 *
 * @author xionglei
 * @create 2018-08-02 18:59
 */

public class AssertUtils {

    public static void isTrue(boolean expression, String respMsg) {
        if (!expression) {
            throw new ValidationException(respMsg);
        }
    }

    public static void isTrue(boolean expression, String respMsg, Object[] args) {
        if (!expression) {
            throw new ValidationException(respMsg, args);
        }
    }

    public static void isTrue(boolean expression, String respCode, String respMsg) {
        if (!expression) {
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void isTrue(boolean expression, String respCode, String respMsg, Object[] args) {
        if (!expression) {
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void isTrue(boolean expression, BaseResult baseResult) {
        if (!expression) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void isTrue(boolean expression, BaseResult baseResult, Object[] args) {
        if (!expression) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void isFalse(boolean expression, String respMsg) {
        if (expression) {
            throw new ValidationException(respMsg);
        }
    }

    public static void isFalse(boolean expression, String respMsg, Object[] args) {
        if (expression) {
            throw new ValidationException(respMsg, args);
        }
    }

    public static void isFalse(boolean expression, String respCode, String respMsg) {
        if (expression) {
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void isFalse(boolean expression, String respCode, String respMsg, Object[] args) {
        if (expression) {
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void isFalse(boolean expression, BaseResult baseResult) {
        if (expression) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void isFalse(boolean expression, BaseResult baseResult, Object[] args) {
        if (expression) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void isNull(Object object, String respMsg) {
        if (object != null) {
            throw new ValidationException(respMsg);
        }
    }

    public static void isNull(Object object, String respMsg, Object[] args) {
        if (object != null) {
            throw new ValidationException(respMsg, args);
        }
    }

    public static void isNull(Object object, String respCode, String respMsg) {
        if (object != null) {
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void isNull(Object object, String respCode, String respMsg, Object[] args) {
        if (object != null) {
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void isNull(Object object, BaseResult baseResult) {
        if (object != null) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void isNull(Object object, BaseResult baseResult, Object[] args) {
        if (object != null) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void notNull(Object object, String respMsg) {
        if (object == null) {
            throw new ValidationException(respMsg);
        }
    }


    public static void notNull(Object object, String respMsg, Object[] args) {
        if (object == null) {
            throw new ValidationException(respMsg, args);
        }
    }

    public static void notNull(Object object, String respCode, String respMsg) {
        if (object == null) {
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void notNull(Object object, String respCode, String respMsg, Object[] args) {
        if (object == null) {
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void notNull(Object object, BaseResult baseResult) {
        if (object == null) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void notNull(Object object, BaseResult baseResult, Object[] args) {
        if (object == null) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void isBlank(String text, String respMsg) {
        if (StringUtils.isNotBlank(text)) {
            throw new ValidationException(respMsg);
        }
    }

    public static void isBlank(String text, String respMsg, Object[] args) {
        if (StringUtils.isNotBlank(text)) {
            throw new ValidationException(respMsg, args);
        }
    }

    public static void isBlank(String text, String respCode, String respMsg) {
        if (StringUtils.isNotBlank(text)) {
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void isBlank(String text, String respCode, String respMsg, Object[] args) {
        if (StringUtils.isNotBlank(text)) {
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void isBlank(String text, BaseResult baseResult) {
        if (StringUtils.isNotBlank(text)) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void isBlank(String text, BaseResult baseResult, Object[] args) {
        if (StringUtils.isNotBlank(text)) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void notBlank(String text, String respMsg) {
        if (StringUtils.isBlank(text)) {
            throw new ValidationException(respMsg);
        }
    }

    public static void notBlank(String text, String respMsg, Object[] args) {
        if (StringUtils.isBlank(text)) {
            throw new ValidationException(respMsg, args);
        }
    }

    public static void notBlank(String text, String respCode, String respMsg) {
        if (StringUtils.isBlank(text)) {
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void notBlank(String text, String respCode, String respMsg, Object[] args) {
        if (StringUtils.isBlank(text)) {
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void notBlank(String text, BaseResult baseResult) {
        if (StringUtils.isBlank(text)) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void notBlank(String text, BaseResult baseResult, Object[] args) {
        if (StringUtils.isBlank(text)) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void isEmpty(Collection<?> collection, String respMsg) {
        if (collection != null && !collection.isEmpty()) {
            throw new ValidationException(respMsg);
        }
    }

    public static void isEmpty(Collection<?> collection, String respMsg, Object[] args) {
        if (collection != null && !collection.isEmpty()) {
            throw new ValidationException(respMsg, args);
        }
    }

    public static void isEmpty(Collection<?> collection, String respCode, String respMsg) {
        if (collection != null && !collection.isEmpty()) {
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void isEmpty(Collection<?> collection, String respCode, String respMsg, Object[] args) {
        if (collection != null && !collection.isEmpty()) {
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void isEmpty(Collection<?> collection, BaseResult baseResult) {
        if (collection != null && !collection.isEmpty()) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void isEmpty(Collection<?> collection, BaseResult baseResult, Object[] args) {
        if (collection != null && !collection.isEmpty()) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void notEmpty(Collection<?> collection, String respMsg) {
        if (collection == null || collection.isEmpty()) {
            throw new ValidationException(respMsg);
        }
    }

    public static void notEmpty(Collection<?> collection, String respMsg, Object[] args) {
        if (collection == null || collection.isEmpty()) {
            throw new ValidationException(respMsg, args);
        }
    }

    public static void notEmpty(Collection<?> collection, String respCode, String respMsg) {
        if (collection == null || collection.isEmpty()) {
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void notEmpty(Collection<?> collection, String respCode, String respMsg, Object[] args) {
        if (collection == null || collection.isEmpty()) {
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void notEmpty(Collection<?> collection, BaseResult baseResult) {
        if (collection == null || collection.isEmpty()) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void notEmpty(Collection<?> collection, BaseResult baseResult, Object[] args) {
        if (collection == null || collection.isEmpty()) {
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void isEquals(String unexpected, String actual, String respMsg) {
        if(!StringUtils.equals(unexpected, actual)){
            throw new ValidationException(respMsg);
        }
    }

    public static void isEquals(String unexpected, String actual, String respMsg, Object[] args) {
        if(!StringUtils.equals(unexpected, actual)){
            throw new ValidationException(respMsg, args);
        }
    }

    public static void isEquals(String unexpected, String actual, String respCode, String respMsg) {
        if(!StringUtils.equals(unexpected, actual)){
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void isEquals(String unexpected, String actual, String respCode, String respMsg, Object[] args) {
        if(!StringUtils.equals(unexpected, actual)){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void isEquals(String unexpected, String actual, BaseResult baseResult) {
        if(!StringUtils.equals(unexpected, actual)){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void isEquals(String unexpected, String actual, BaseResult baseResult, Object[] args) {
        if(!StringUtils.equals(unexpected, actual)){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void notEquals(String unexpected, String actual, String respMsg) {
        if(StringUtils.equals(unexpected, actual)){
            throw new ValidationException(respMsg);
        }
    }

    public static void notEquals(String unexpected, String actual, String respMsg, Object[] args) {
        if(StringUtils.equals(unexpected, actual)){
            throw new ValidationException(respMsg, args);
        }
    }

    public static void notEquals(String unexpected, String actual, String respCode, String respMsg) {
        if(StringUtils.equals(unexpected, actual)){
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void notEquals(String unexpected, String actual, String respCode, String respMsg, Object[] args) {
        if(StringUtils.equals(unexpected, actual)){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void notEquals(String unexpected, String actual, BaseResult baseResult) {
        if(StringUtils.equals(unexpected, actual)){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void notEquals(String unexpected, String actual, BaseResult baseResult, Object[] args) {
        if(StringUtils.equals(unexpected, actual)){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void isEquals(int unexpected, int actual, String respMsg) {
        if(Integer.compare(unexpected, actual) != 0){
            throw new ValidationException(respMsg);
        }
    }

    public static void isEquals(int unexpected, int actual, String respMsg, Object[] args) {
        if(Integer.compare(unexpected, actual) != 0){
            throw new ValidationException(respMsg, args);
        }
    }

    public static void isEquals(int unexpected, int actual, String respCode, String respMsg) {
        if(Integer.compare(unexpected, actual) != 0){
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void isEquals(int unexpected, int actual, String respCode, String respMsg, Object[] args) {
        if(Integer.compare(unexpected, actual) != 0){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void isEquals(int unexpected, int actual, BaseResult baseResult) {
        if(Integer.compare(unexpected, actual) != 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void isEquals(int unexpected, int actual, BaseResult baseResult, Object[] args) {
        if(Integer.compare(unexpected, actual) != 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void notEquals(int unexpected, int actual, String respMsg) {
        if(Integer.compare(unexpected, actual) == 0){
            throw new ValidationException(respMsg);
        }
    }

    public static void notEquals(int unexpected, int actual, String respMsg, Object[] args) {
        if(Integer.compare(unexpected, actual) == 0){
            throw new ValidationException(respMsg, args);
        }
    }

    public static void notEquals(int unexpected, int actual, String respCode, String respMsg) {
        if(Integer.compare(unexpected, actual) == 0){
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void notEquals(int unexpected, int actual, String respCode, String respMsg, Object[] args) {
        if(Integer.compare(unexpected, actual) == 0){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void notEquals(int unexpected, int actual, BaseResult baseResult) {
        if(Integer.compare(unexpected, actual) == 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void notEquals(int unexpected, int actual, BaseResult baseResult, Object[] args) {
        if(Integer.compare(unexpected, actual) == 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void isEquals(float unexpected, float actual, String respMsg) {
        if(Float.compare(unexpected, actual) != 0){
            throw new ValidationException(respMsg);
        }
    }

    public static void isEquals(float unexpected, float actual, String respMsg, Object[] args) {
        if(Float.compare(unexpected, actual) != 0){
            throw new ValidationException(respMsg, args);
        }
    }

    public static void isEquals(float unexpected, float actual, String respCode, String respMsg) {
        if(Float.compare(unexpected, actual) != 0){
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void isEquals(float unexpected, float actual, String respCode, String respMsg, Object[] args) {
        if(Float.compare(unexpected, actual) != 0){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void isEquals(float unexpected, float actual, BaseResult baseResult) {
        if(Float.compare(unexpected, actual) != 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void isEquals(float unexpected, float actual, BaseResult baseResult, Object[] args) {
        if(Float.compare(unexpected, actual) != 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void notEquals(float unexpected, float actual, String respMsg) {
        if(Float.compare(unexpected, actual) == 0){
            throw new ValidationException(respMsg);
        }
    }

    public static void notEquals(float unexpected, float actual, String respMsg, Object[] args) {
        if(Float.compare(unexpected, actual) == 0){
            throw new ValidationException(respMsg, args);
        }
    }

    public static void notEquals(float unexpected, float actual, String respCode, String respMsg) {
        if(Float.compare(unexpected, actual) == 0){
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void notEquals(float unexpected, float actual, String respCode, String respMsg, Object[] args) {
        if(Float.compare(unexpected, actual) == 0){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void notEquals(float unexpected, float actual, BaseResult baseResult) {
        if(Float.compare(unexpected, actual) == 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void notEquals(float unexpected, float actual, BaseResult baseResult, Object[] args) {
        if(Float.compare(unexpected, actual) == 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void isEquals(double unexpected, double actual, String respMsg) {
        if(Double.compare(unexpected, actual) != 0){
            throw new ValidationException(respMsg);
        }
    }

    public static void isEquals(double unexpected, double actual, String respMsg, Object[] args) {
        if(Double.compare(unexpected, actual) != 0){
            throw new ValidationException(respMsg, args);
        }
    }

    public static void isEquals(double unexpected, double actual, String respCode, String respMsg) {
        if(Double.compare(unexpected, actual) != 0){
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void isEquals(double unexpected, double actual, String respCode, String respMsg, Object[] args) {
        if(Double.compare(unexpected, actual) != 0){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void isEquals(double unexpected, double actual, BaseResult baseResult) {
        if(Double.compare(unexpected, actual) != 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void isEquals(double unexpected, double actual, BaseResult baseResult, Object[] args) {
        if(Double.compare(unexpected, actual) != 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void notEquals(double unexpected, double actual, String respMsg) {
        if(Double.compare(unexpected, actual) == 0){
            throw new ValidationException(respMsg);
        }
    }

    public static void notEquals(double unexpected, double actual, String respMsg, Object[] args) {
        if(Double.compare(unexpected, actual) == 0){
            throw new ValidationException(respMsg, args);
        }
    }

    public static void notEquals(double unexpected, double actual, String respCode, String respMsg) {
        if(Double.compare(unexpected, actual) == 0){
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void notEquals(double unexpected, double actual, String respCode, String respMsg, Object[] args) {
        if(Double.compare(unexpected, actual) == 0){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void notEquals(double unexpected, double actual, BaseResult baseResult) {
        if(Double.compare(unexpected, actual) == 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void notEquals(double unexpected, double actual, BaseResult baseResult, Object[] args) {
        if(Double.compare(unexpected, actual) == 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void isEquals(BigDecimal unexpected, BigDecimal actual, String respMsg) {
        if(unexpected.compareTo(actual) != 0){
            throw new ValidationException(respMsg);
        }
    }

    public static void isEquals(BigDecimal unexpected, BigDecimal actual, String respMsg, Object[] args) {
        if(unexpected.compareTo(actual) != 0){
            throw new ValidationException(respMsg, args);
        }
    }

    public static void isEquals(BigDecimal unexpected, BigDecimal actual, String respCode, String respMsg) {
        if(unexpected.compareTo(actual) != 0){
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void isEquals(BigDecimal unexpected, BigDecimal actual, String respCode, String respMsg, Object[] args) {
        if(unexpected.compareTo(actual) != 0){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void isEquals(BigDecimal unexpected, BigDecimal actual, BaseResult baseResult) {
        if(unexpected.compareTo(actual) != 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void isEquals(BigDecimal unexpected, BigDecimal actual, BaseResult baseResult, Object[] args) {
        if(unexpected.compareTo(actual) != 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    public static void notEquals(BigDecimal unexpected, BigDecimal actual, String respMsg) {
        if(unexpected.compareTo(actual) == 0){
            throw new ValidationException(respMsg);
        }
    }

    public static void notEquals(BigDecimal unexpected, BigDecimal actual, String respMsg, Object[] args) {
        if(unexpected.compareTo(actual) == 0){
            throw new ValidationException(respMsg, args);
        }
    }

    public static void notEquals(BigDecimal unexpected, BigDecimal actual, String respCode, String respMsg) {
        if(unexpected.compareTo(actual) == 0){
            throw new ValidationException(respCode, respMsg);
        }
    }

    public static void notEquals(BigDecimal unexpected, BigDecimal actual, String respCode, String respMsg, Object[] args) {
        if(unexpected.compareTo(actual) == 0){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    public static void notEquals(BigDecimal unexpected, BigDecimal actual, BaseResult baseResult) {
        if(unexpected.compareTo(actual) == 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    public static void notEquals(BigDecimal unexpected, BigDecimal actual, BaseResult baseResult, Object[] args) {
        if(unexpected.compareTo(actual) == 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }


    /**
     * 断言实际值>期望值
     * @param unexpected
     * @param actual
     * @param respMsg
     */
    public static void isGt(BigDecimal unexpected, BigDecimal actual, String respMsg) {
        if(unexpected.compareTo(actual) >= 0){
            throw new ValidationException(respMsg);
        }
    }

    /**
     * 断言实际值>期望值
     * @param unexpected
     * @param actual
     * @param respMsg
     * @param args
     */
    public static void isGt(BigDecimal unexpected, BigDecimal actual, String respMsg, Object[] args) {
        if(unexpected.compareTo(actual) >= 0){
            throw new ValidationException(respMsg, args);
        }
    }

    /**
     * 断言实际值>期望值
     * @param unexpected
     * @param actual
     * @param respCode
     * @param respMsg
     */
    public static void isGt(BigDecimal unexpected, BigDecimal actual, String respCode, String respMsg) {
        if(unexpected.compareTo(actual) >= 0){
            throw new ValidationException(respCode, respMsg);
        }
    }

    /**
     * 断言实际值>期望值
     * @param unexpected
     * @param actual
     * @param respCode
     * @param respMsg
     * @param args
     */
    public static void isGt(BigDecimal unexpected, BigDecimal actual, String respCode, String respMsg, Object[] args) {
        if(unexpected.compareTo(actual) >= 0){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    /**
     * 断言实际值>期望值
     * @param unexpected
     * @param actual
     * @param baseResult
     */
    public static void isGt(BigDecimal unexpected, BigDecimal actual, BaseResult baseResult) {
        if(unexpected.compareTo(actual) >= 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    /**
     * 断言实际值>期望值
     * @param unexpected
     * @param actual
     * @param baseResult
     * @param args
     */
    public static void isGt(BigDecimal unexpected, BigDecimal actual, BaseResult baseResult, Object[] args) {
        if(unexpected.compareTo(actual) >= 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    /**
     * 断言实际值<=期望值
     * @param unexpected
     * @param actual
     * @param respMsg
     */
    public static void notGt(BigDecimal unexpected, BigDecimal actual, String respMsg) {
        if(unexpected.compareTo(actual) < 0){
            throw new ValidationException(respMsg);
        }
    }

    /**
     * 断言实际值<=期望值
     * @param unexpected
     * @param actual
     * @param respMsg
     * @param args
     */
    public static void notGt(BigDecimal unexpected, BigDecimal actual, String respMsg, Object[] args) {
        if(unexpected.compareTo(actual) < 0){
            throw new ValidationException(respMsg, args);
        }
    }

    /**
     * 断言实际值<=期望值
     * @param unexpected
     * @param actual
     * @param respCode
     * @param respMsg
     */
    public static void notGt(BigDecimal unexpected, BigDecimal actual, String respCode, String respMsg) {
        if(unexpected.compareTo(actual) < 0){
            throw new ValidationException(respCode, respMsg);
        }
    }

    /**
     * 断言实际值<=期望值
     * @param unexpected
     * @param actual
     * @param respCode
     * @param respMsg
     * @param args
     */
    public static void notGt(BigDecimal unexpected, BigDecimal actual, String respCode, String respMsg, Object[] args) {
        if(unexpected.compareTo(actual) < 0){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    /**
     * 断言实际值<=期望值
     * @param unexpected
     * @param actual
     * @param baseResult
     */
    public static void notGt(BigDecimal unexpected, BigDecimal actual, BaseResult baseResult) {
        if(unexpected.compareTo(actual) < 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    /**
     * 断言实际值<=期望值
     * @param unexpected
     * @param actual
     * @param baseResult
     * @param args
     */
    public static void notGt(BigDecimal unexpected, BigDecimal actual, BaseResult baseResult, Object[] args) {
        if(unexpected.compareTo(actual) < 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    /**
     * 断言实际值>期望值
     * @param unexpected
     * @param actual
     * @param respMsg
     */
    public static void isGt(int unexpected, int actual, String respMsg) {
        if(Integer.compare(unexpected, actual) >= 0){
            throw new ValidationException(respMsg);
        }
    }

    /**
     * 断言实际值>期望值
     * @param unexpected
     * @param actual
     * @param respMsg
     * @param args
     */
    public static void isGt(int unexpected, int actual, String respMsg, Object[] args) {
        if(Integer.compare(unexpected, actual) >= 0){
            throw new ValidationException(respMsg, args);
        }
    }

    /**
     * 断言实际值>期望值
     * @param unexpected
     * @param actual
     * @param respCode
     * @param respMsg
     */
    public static void isGt(int unexpected, int actual, String respCode, String respMsg) {
        if(Integer.compare(unexpected, actual) >= 0){
            throw new ValidationException(respCode, respMsg);
        }
    }

    /**
     * 断言实际值>期望值
     * @param unexpected
     * @param actual
     * @param respCode
     * @param respMsg
     * @param args
     */
    public static void isGt(int unexpected, int actual, String respCode, String respMsg, Object[] args) {
        if(Integer.compare(unexpected, actual) >= 0){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    /**
     * 断言实际值>期望值
     * @param unexpected
     * @param actual
     * @param baseResult
     */
    public static void isGt(int unexpected, int actual, BaseResult baseResult) {
        if(Integer.compare(unexpected, actual) >= 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    /**
     * 断言实际值>期望值
     * @param unexpected
     * @param actual
     * @param baseResult
     * @param args
     */
    public static void isGt(int unexpected, int actual, BaseResult baseResult, Object[] args) {
        if(Integer.compare(unexpected, actual) >= 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    /**
     * 断言实际值<=期望值
     * @param unexpected
     * @param actual
     * @param respMsg
     */
    public static void notGt(int unexpected, int actual, String respMsg) {
        if(Integer.compare(unexpected, actual) < 0){
            throw new ValidationException(respMsg);
        }
    }

    /**
     * 断言实际值<=期望值
     * @param unexpected
     * @param actual
     * @param respMsg
     * @param args
     */
    public static void notGt(int unexpected, int actual, String respMsg, Object[] args) {
        if(Integer.compare(unexpected, actual) < 0){
            throw new ValidationException(respMsg, args);
        }
    }

    /**
     * 断言实际值<=期望值
     * @param unexpected
     * @param actual
     * @param respCode
     * @param respMsg
     */
    public static void notGt(int unexpected, int actual, String respCode, String respMsg) {
        if(Integer.compare(unexpected, actual) < 0){
            throw new ValidationException(respCode, respMsg);
        }
    }

    /**
     * 断言实际值<=期望值
     * @param unexpected
     * @param actual
     * @param respCode
     * @param respMsg
     * @param args
     */
    public static void notGt(int unexpected, int actual, String respCode, String respMsg, Object[] args) {
        if(Integer.compare(unexpected, actual) < 0){
            throw new ValidationException(respCode, respMsg, args);
        }
    }

    /**
     * 断言实际值<=期望值
     * @param unexpected
     * @param actual
     * @param baseResult
     */
    public static void notGt(int unexpected, int actual, BaseResult baseResult) {
        if(Integer.compare(unexpected, actual) < 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg());
        }
    }

    /**
     * 断言实际值<=期望值
     * @param unexpected
     * @param actual
     * @param baseResult
     * @param args
     */
    public static void notGt(int unexpected, int actual, BaseResult baseResult, Object[] args) {
        if(Integer.compare(unexpected, actual) < 0){
            throw new ValidationException(baseResult.getRespCode(), baseResult.getRespMsg(), args);
        }
    }

    /**
     * 数字类型大于断言
     *
     * @param expected
     * @param actual
     * @param respCode
     */
    public static void gt(Integer expected, Integer actual, String respCode,String respMsg) {
        if (!(actual.compareTo(expected) > 0)) {
            throw new BusinessException(respCode,respMsg);
        }
    }

    /**
     * 数字类型大于断言
     *
     * @param expected
     * @param actual
     * @param respCode
     */
    public static void gt(BigDecimal expected, BigDecimal actual, String respCode,String respMsg) {
        if (!(actual.compareTo(expected) > 0)) {
            throw new BusinessException(respCode, respMsg);
        }
    }

    /**
     * 时间断言
     *
     * @param expected
     * @param actual
     * @param respCode
     */
    public static void before(Date expected, Date actual, String respCode,String respMsg) {
        if (!(actual.compareTo(expected) <= 0)) {
            throw new BusinessException(respCode, respMsg);
        }
    }
}
