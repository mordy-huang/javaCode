package com.hdljava.skycat.pojo;

public class attributeValue {
    private Integer id;

    private Integer pid;

    private Integer aid;

    private String value;

    private attribute Attribute;

    public attribute getAttribute() {
        return Attribute;
    }

    public void setAttribute(attribute attribute) {
        Attribute = attribute;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}