package com.chinaums.commons;

import java.text.MessageFormat;

/**
 * 验证异常
 *
 * @author xionglei
 * @create 2018-08-20 21:35
 */

public class ValidationException extends RuntimeException {

    /**
     * 响应码
     */
    protected String respCode;

    public ValidationException(){
        super();
    }

    public ValidationException(String respMsg){
        super(respMsg);
    }

    public ValidationException(Throwable cause){
        super(cause);
    }

    public ValidationException(String respCode, String respMsg){
        super(respMsg);
        this.respCode = respCode;
    }

    public ValidationException(String respMsg, Object[] args){
        super(MessageFormat.format(respMsg, args));
    }

    public ValidationException(String respCode, String respMsg, Object[] args){
        super(MessageFormat.format(respMsg, args));
        this.respCode = respCode;
    }

    public ValidationException(String respMsg, Throwable cause){
        super(respMsg, cause);
    }

    public ValidationException(String respCode, String respMsg, Throwable cause){
        super(respMsg, cause);
        this.respCode = respCode;
    }

    public ValidationException(String respMsg, Object[] args, Throwable cause){
        super(MessageFormat.format(respMsg, args), cause);
    }

    public ValidationException(String respCode, String respMsg, Object[] args, Throwable cause){
        super(MessageFormat.format(respMsg, args), cause);
        this.respCode = respCode;
    }

    public ValidationException(BaseResult baseResult){
        super(baseResult.getRespMsg());
        this.respCode = baseResult.getRespCode();
    }

    public ValidationException(BaseResult baseResult, Throwable cause){
        super(baseResult.getRespMsg(), cause);
        this.respCode = baseResult.getRespCode();
    }

    public ValidationException(BaseResult baseResult, Object[] args){
        super(MessageFormat.format(baseResult.getRespMsg(), args));
        this.respCode = baseResult.getRespCode();
    }

    public ValidationException(BaseResult baseResult, Object[] args, Throwable cause){
        super(MessageFormat.format(baseResult.getRespMsg(), args), cause);
        this.respCode = baseResult.getRespCode();
    }

    protected ValidationException(String message, Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getRespCode() {
        return this.respCode;
    }
}
