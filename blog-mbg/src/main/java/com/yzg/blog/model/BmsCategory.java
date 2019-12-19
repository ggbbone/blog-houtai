package com.yzg.blog.model;

import java.io.Serializable;
import java.util.Date;

public class BmsCategory implements Serializable {
    private Integer id;

    private String title;

    private String alias;

    private String icon;

    private String background;

    private Date createdDate;

    private Date updatedDate;

    private Integer entrycount;

    private Integer subcriberscount;

    private Boolean iscategory;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getEntrycount() {
        return entrycount;
    }

    public void setEntrycount(Integer entrycount) {
        this.entrycount = entrycount;
    }

    public Integer getSubcriberscount() {
        return subcriberscount;
    }

    public void setSubcriberscount(Integer subcriberscount) {
        this.subcriberscount = subcriberscount;
    }

    public Boolean getIscategory() {
        return iscategory;
    }

    public void setIscategory(Boolean iscategory) {
        this.iscategory = iscategory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", alias=").append(alias);
        sb.append(", icon=").append(icon);
        sb.append(", background=").append(background);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", entrycount=").append(entrycount);
        sb.append(", subcriberscount=").append(subcriberscount);
        sb.append(", iscategory=").append(iscategory);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}