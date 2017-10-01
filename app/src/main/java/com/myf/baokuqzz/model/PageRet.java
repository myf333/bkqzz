package com.myf.baokuqzz.model;

/**
 * Created by myf on 2017/9/18.
 */

public class PageRet<T> {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 消息
     */
    private String message;
    /**
     * 当前页
     */
    private int currpage;
    /**
     * 每页条数
     */
    private int pagesize;
    /**
     * 总数
     */
    private int total;
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

    public int getCurrpage() {
        return currpage;
    }

    public void setCurrpage(int currpage) {
        this.currpage = currpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
