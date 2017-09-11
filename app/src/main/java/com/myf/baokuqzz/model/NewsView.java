package com.myf.baokuqzz.model;

/**
 * Created by myf on 2017/8/21.
 */

public class NewsView {
    /**
     * 主键
     */
    private long id;
    /**
     * 动态名称
     */
    private String title;
    /**
     * 摘要
     */
    private String memo;
    /**
     * 正文内容
     */
    private String content;
    /**
     * 封面图
     */
    private String picurl;
    /**
     * 发布时间
     */
    private String publishdate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    @Override
    public String toString() {
        return "NewsView{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishdate='" + publishdate + '\'' +
                '}';
    }
}
