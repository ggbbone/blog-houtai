package com.yzg.blog.portal.model;

/**
 * Created by yzg on 2020/1/13
 */
public enum CommentStatus {
    NORMAL((byte) 1, "正常"),

    LOADING((byte) 2, "审核中"),

    DELETE((byte)0, "已删除");

    private byte code;
    private String desc;


    CommentStatus(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
