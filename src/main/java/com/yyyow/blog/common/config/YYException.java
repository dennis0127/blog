package com.yyyow.blog.common.config;


import com.yyyow.blog.common.utils.R;

/**
 * 自定义业务逻辑的异常
 * http拦截异常后根据情况返回不同的返回码
 *
 * @Author lxd
 */
public class YYException extends RuntimeException {
    //http 返回值
    private int httpCode;

    private R r; //通用返回类型，可以从错误转过来

    public YYException(int httpCode, String additional_msg) {
        this.httpCode = httpCode;
        this.r = R.error(R.FAILURE,additional_msg);
    }

    public YYException() {
    }

    @Override
    public String getMessage() {
        return String.format("code: %s msg: %s ref_http_code: %d",this.r.get("code"),this.r.get("msg"),this.httpCode);
    }


    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public R getR(){
        return r;
    }


    public static YYException create(int code, String msg){
        YYException exception = new YYException();
        exception.httpCode = code;
        exception.r = R.error(code,msg);
        return exception;
    }


    public static YYException create(int httpCode, int errCode, String msg) {
        YYException exception = new YYException();
        exception.httpCode = httpCode;
        exception.r = R.error(errCode,msg);
        return exception;
    }

    public static YYException create(int errCode, String msg, Object data) {
        YYException exception = new YYException();
        exception.r = R.ok(errCode,msg,data);
        return exception;
    }




}
