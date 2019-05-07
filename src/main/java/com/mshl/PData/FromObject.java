package com.mshl.PData;

import java.sql.Timestamp;

public class FromObject
{
    public FromObject(int user_id, String name, String login, String email, String avatar,
                      Timestamp last_visit_time, boolean online)
    {
        this.user_id = user_id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.avatar = avatar;
        this.last_visit_time = last_visit_time;
        this.online = online;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public Timestamp getLast_visit_time() {
        return last_visit_time;
    }

    public boolean isOnline() {
        return online;
    }

    private int user_id;
    private String name;
    private String login;
    private String email;
    private String avatar;
    private Timestamp last_visit_time;
    private boolean online;
}
