package com.mshl.PData;

import java.sql.Timestamp;

public class MessageInf
{
    public MessageInf(int from_user_id, String from_user_name, String from_user_avatar, String msg, Timestamp time)
    {
        this.from_user_id = from_user_id;
        this.from_user_name = from_user_name;
        this.from_user_avatar = from_user_avatar;
        this.msg = msg;
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageInf{" +
                "from_user_id=" + from_user_id +
                ", from_user_name='" + from_user_name + '\'' +
                ", from_user_avatar='" + from_user_avatar + '\'' +
                ", msg='" + msg + '\'' +
                ", time=" + time +
                '}';
    }

    private int from_user_id;
    private String from_user_name;
    private String from_user_avatar;
    private String msg;
    private Timestamp time;
}
