package com.mshl.PData;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * data_type = 3. Класс передачи списка сообщений.
 */
public class PQueryMessagesList
{
    public void AddInMessageInfList(int from_user_id, String from_user_name, String from_user_avatar, String msg, Timestamp time)
    {
        MessageInf messageInf = new MessageInf(
                from_user_id, from_user_name, from_user_avatar, msg,
                time
        );
        messageInfList.add(messageInf);
    }

    public void setLastMsgInf(int from_user_id, Timestamp last_read_time, Timestamp last_msg_time, Timestamp my_last_reading_time)
    {
        lastMsgInf = new LastMsgInf(
                from_user_id, last_read_time, last_msg_time,
                my_last_reading_time);  
    }

    private LastMsgInf lastMsgInf;
    private List<MessageInf> messageInfList = new ArrayList<>();
}

class LastMsgInf
{
    public LastMsgInf(int from_user_id, Timestamp last_read_time, Timestamp last_msg_time, Timestamp my_last_reading_time)
    {
        this.from_user_id = from_user_id;
        this.last_read_time = last_read_time;
        this.last_msg_time = last_msg_time;
        this.my_last_reading_time = my_last_reading_time;
    }

    private int from_user_id;
    private Timestamp last_read_time;
    private Timestamp last_msg_time;
    private Timestamp my_last_reading_time;
}

class MessageInf
{
    public MessageInf(int from_user_id, String from_user_name, String from_user_avatar, String msg, Timestamp time)
    {
        this.from_user_id = from_user_id;
        this.from_user_name = from_user_name;
        this.from_user_avatar = from_user_avatar;
        this.msg = msg;
        this.time = time;
    }

    private int from_user_id;
    private String from_user_name;
    private String from_user_avatar;
    private String msg;
    private Timestamp time;
}