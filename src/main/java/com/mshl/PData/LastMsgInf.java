package com.mshl.PData;

import java.sql.Timestamp;

public class LastMsgInf
{
    public LastMsgInf(int from_user_id, Timestamp last_read_time, Timestamp last_msg_time, Timestamp my_last_reading_time)
    {
        this.from_user_id = from_user_id;
        this.last_read_time = last_read_time;
        this.last_msg_time = last_msg_time;
        this.my_last_reading_time = my_last_reading_time;
    }

    @Override
    public String toString() {
        return "LastMsgInf{" +
                "from_user_id=" + from_user_id +
                ", last_read_time=" + last_read_time +
                ", last_msg_time=" + last_msg_time +
                ", my_last_reading_time=" + my_last_reading_time +
                '}';
    }

    private int from_user_id;
    private Timestamp last_read_time;
    private Timestamp last_msg_time;
    private Timestamp my_last_reading_time;
}