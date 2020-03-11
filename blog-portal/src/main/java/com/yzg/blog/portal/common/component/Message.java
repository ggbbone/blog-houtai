package com.yzg.blog.portal.common.component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzg on 2020/3/11
 */
public class Message implements Serializable {
    private Integer userId;
    private Byte type;
    private Integer typeId;
    private Map<String, Object> data;

    private Message() {
    }

    private Message(Integer userId, Byte type, Integer typeId) {
        this.userId = userId;
        this.type = type;
        this.typeId = typeId;
    }

    public static Message createMessage(){
        return new Message();
    }
    public static Message createMessage(int userId, byte type, int typeId){
        return new Message(userId, type, typeId);
    }

    public Message add(String key, Object value) {
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        this.data.put(key, value);
        return this;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "userId=" + userId +
                ", type=" + type +
                ", typeId=" + typeId +
                ", data=" + data +
                '}';
    }
}
