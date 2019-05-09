package com.mshl.PData;

import java.sql.Timestamp;

/**
 * Этот класс - элементарная единица хранения информации о диалоге, который
 * будет пересылаться клиенту при его запросе на получение списка диалогов.
 */
public class DialogInf
{
    private int dialog_id;
    private String dialog_name;
    private String dialog_img;
    private String last_msg;
    private String from_user_name;
    private Timestamp last_msg_time;
    private int from_user_id;
    private Timestamp last_read_time;
    private Timestamp my_last_reading_time; /// time из Dialogs

    public DialogInf(int dialog_id, String dialog_name, String dialog_img, String last_msg, String from_user_name, Timestamp last_msg_time, int from_user_id, Timestamp last_read_time, Timestamp my_last_reading_time) {
        this.dialog_id = dialog_id;
        this.dialog_name = dialog_name;
        this.dialog_img = dialog_img;
        this.last_msg = last_msg;
        this.from_user_name = from_user_name;
        this.last_msg_time = last_msg_time;
        this.from_user_id = from_user_id;
        this.last_read_time = last_read_time;
        this.my_last_reading_time = my_last_reading_time;
    }

    public Timestamp getMy_last_reading_time() {
        return my_last_reading_time;
    }

    public int getDialog_id() {
        return dialog_id;
    }

    public String getDialog_name() {
        return dialog_name;
    }

    public String getDialog_img() {
        return dialog_img;
    }

    public String getLast_msg() {
        return last_msg;
    }

    public String getFrom_user_name() {
        return from_user_name;
    }

    public Timestamp getLast_msg_time() {
        return last_msg_time;
    }

    public int getFrom_user_id() {
        return from_user_id;
    }

    public Timestamp getLast_read_time() {
        return last_read_time;
    }

    public void setDialog_id(int dialog_id) {
        this.dialog_id = dialog_id;
    }

    public void setDialog_name(String dialog_name) {
        this.dialog_name = dialog_name;
    }

    public void setDialog_img(String dialog_img) {
        this.dialog_img = dialog_img;
    }

    public void setLast_msg(String last_msg) {
        this.last_msg = last_msg;
    }

    public void setFrom_user_name(String from_user_name) {
        this.from_user_name = from_user_name;
    }

    public void setLast_msg_time(Timestamp last_msg_time) {
        this.last_msg_time = last_msg_time;
    }

    public void setFrom_user_id(int from_user_id) {
        this.from_user_id = from_user_id;
    }

    public void setLast_read_time(Timestamp last_read_time) {
        this.last_read_time = last_read_time;
    }

    public void setMy_last_reading_time(Timestamp my_last_reading_time) {
        this.my_last_reading_time = my_last_reading_time;
    }
}
