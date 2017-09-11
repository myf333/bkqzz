package com.myf.baokuqzz.model;

/**
 * Created by myf on 2017/8/21.
 */

public class ReturnRet<T> {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReturnRet{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
