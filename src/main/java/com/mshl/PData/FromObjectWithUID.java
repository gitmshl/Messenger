package com.mshl.PData;

import java.sql.Timestamp;

public class FromObjectWithUID
{

    public FromObjectWithUID(FromObject fromObject, String uid)
    {
        this.user_id = fromObject.getUser_id();
        this.name = fromObject.getName();
        this.login = fromObject.getLogin();
        this.email = fromObject.getEmail();
        this.avatar = fromObject.getAvatar();
        this.last_visit_time = fromObject.getLast_visit_time();
        this.online = fromObject.isOnline();
        this.uid = uid;
    }

    private int user_id;
    private String uid;
    private String name;
    private String login;
    private String email;
    private String avatar;
    private Timestamp last_visit_time;
    private boolean online;
}
