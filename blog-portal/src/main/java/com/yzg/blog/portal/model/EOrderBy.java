package com.yzg.blog.portal.model;

/**
 * Created by yzg on 2020/1/13
 */
public enum EOrderBy {
    CREATED_TIME((byte) 1, "created_date"),

    UPDATED_TIME((byte) 2, "updated_date"),

    LIKES_COUNT((byte)3, "likes_count"),

    REPLY_COUNT((byte)4, "reply_count");

    private byte code;
    private String desc;


    EOrderBy(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(int code) {
        for (EOrderBy orderBy : EOrderBy.values()) {
            if (orderBy.getCode() == code) {
                return orderBy.getDesc();
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
