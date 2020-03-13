package com.yzg.blog.portal.model;

/**
 * Created by yzg on 2020/1/13
 */
public enum ECommentParentType {
    ARTICLE((byte) 1, "文章"),

    PIN((byte)2, "讨论");

    private byte code;
    private String desc;


    ECommentParentType(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static String getDesc(byte code) {
        for (ECommentParentType type : ECommentParentType.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
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
