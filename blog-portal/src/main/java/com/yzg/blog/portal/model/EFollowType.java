package com.yzg.blog.portal.model;

/**
 * Created by yzg on 2020/1/13
 */
public enum EFollowType {
    USER((byte) 1, "用户"),

    CATEGORY((byte) 2, "标签"),
    ;
    private byte code;
    private String desc;


    EFollowType(byte code, String desc) {
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
