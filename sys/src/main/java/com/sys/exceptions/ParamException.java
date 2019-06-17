package com.sys.exceptions;


public class ParamException extends  RuntimeException {
   private Integer errorCode=300;
   private String errorMsg="操作失败";

    public ParamException() {
    }

    public ParamException(Integer errorCode) {
        super("操作失败!");
        this.errorCode = errorCode;
    }

    public ParamException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ParamException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
