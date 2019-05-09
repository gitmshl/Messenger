package com.mshl.Group_Handler;

import com.mshl.DB_Handler.DB_Handler;
import com.mshl.SESSIONS_CONTAINER.SessionsContainer;

import javax.websocket.Session;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Group_Handler
{
    public Group_Handler()
    {
        db_handler = new DB_Handler();
        SC = new SessionsContainer();
        last_change_time = new Timestamp(0);
        current_dialog_id = -1;
        cache = new LinkedList<>();
    }

    public List<Session> getSessionsByDialogId(int dialog_id)
    {
        try
        {
            List<Session> list = getFromCache(dialog_id);
            if (list != null) return list;
            list = SC.getSessionsByIds(db_handler.getIdsByDialogId(dialog_id));
            writeToCache(list, dialog_id);
            return list;
        }
        catch (SQLException e)
        {
            System.err.println("DB Err");
            return null;
        }
    }

    private List<Session> getFromCache(int dialog_id) throws SQLException
    {
        if (db_handler == null) throw new SQLException("db_handler = null. getFromCache. Group Handler");
        if (current_dialog_id != -1 && current_dialog_id == dialog_id)
        {
            Timestamp timestamp = db_handler.getLastChangeTime_DialogsLastSessionsChanges(dialog_id);
            if (last_change_time.after(timestamp)) return cache;
        }
        return null;
    }

    private void writeToCache(List<Session> newCache, int dialog_id)
    {
        if (newCache == null) return;
        cache = newCache;
        current_dialog_id = dialog_id;
        last_change_time = new Timestamp(System.currentTimeMillis());
    }

    private Timestamp last_change_time;
    private int current_dialog_id;
    private List<Session> cache;
    private DB_Handler db_handler;
    private SessionsContainer SC;

}
