package com.yzg.blog.portal.model;

/**
 * Created by yzg on 2020/1/13
 */
public enum ELikeType {
    ARTICLE((byte) 1, "文章"),

    COMMENT((byte) 2, "评论"),

    PIN((byte)3, "讨论");

    private byte code;
    private String desc;


    ELikeType(byte code, String desc) {
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
