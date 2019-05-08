package com.mshl.PData;

public class PQueryDataMessage
{

    public PQueryDataMessage(String name, String avatar, String msg)
    {
        this.name = name;
        this.avatar = avatar;
        this.msg = msg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getMsg() {
        return msg;
    }

    private String name;
    private String avatar;
    private String msg;
}
