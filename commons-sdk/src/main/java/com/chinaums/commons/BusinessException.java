package com.chinaums.commons;


/**
 * 业务异常
 *
 * @author xionglei
 * @create 2018-02-22 15:34
 */
public class BusinessException extends ValidationException {

    public BusinessException(){
        super();
    }

    public BusinessException(String respMsg){
        super(respMsg);
    }

    public BusinessException(Throwable cause){
        super(cause);
    }

    public BusinessException(String respCode, String respMsg){
        super(respCode, respMsg);
    }

    public BusinessException(String respMsg, Object[] args){
        super(respMsg, args);
    }

    public BusinessException(String respCode, String respMsg, Object[] args){
        super(respCode, respMsg, args);
    }

    public BusinessException(String respMsg, Throwable cause){
        super(respMsg, cause);
    }

    public BusinessException(String respCode, String respMsg, Throwable cause){
        super(respCode, respMsg, cause);
    }

    public BusinessException(String respMsg, Object[] args, Throwable cause){
        super(respMsg, args, cause);
    }

    public BusinessException(String respCode, String respMsg, Object[] args, Throwable cause){
        super(respCode, respMsg, args, cause);
    }

    public BusinessException(BaseResult baseResult){
        super(baseResult.getRespCode(), baseResult.getRespMsg());
    }

    public BusinessException(BaseResult baseResult, Throwable cause){
        super(baseResult.getRespCode(), baseResult.getRespMsg(), cause);
    }

    public BusinessException(BaseResult baseResult, Object[] args){
        super(baseResult.getRespCode(), baseResult.getRespMsg(), args);
    }

    public BusinessException(BaseResult baseResult, Object[] args, Throwable cause){
        super(baseResult.getRespCode(), baseResult.getRespMsg(), args, cause);
    }

    protected BusinessException(String message, Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
