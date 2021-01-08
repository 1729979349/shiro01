package com.zking.shiro01.model;

import java.io.Serializable;

public class User implements Serializable {

    private Integer uid;
    private String  uname;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
