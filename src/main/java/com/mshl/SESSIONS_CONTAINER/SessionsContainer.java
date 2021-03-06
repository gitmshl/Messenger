package com.mshl.SESSIONS_CONTAINER;

import com.mshl.DB_Handler.DB_Handler;

import javax.websocket.Session;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SessionsContainer
{
    private static Map<Session, Integer> sessionMap = new HashMap<>();
    private DB_Handler db_handler;

    public SessionsContainer()
    {
        db_handler = new DB_Handler();
    }

    /*
        Возвращается false, если возникла какая-та ошибка в БД.
     */
    public boolean Add(Session session, int id)
    {

        if (db_handler == null) return false;

        if (db_handler.Update_DialogsLastSessionsChanges(id) && db_handler.Update_Online(id, true))
        {
            System.out.println(sessionMap.put(session, id));
            return true;
        }
        return false;
    }

    /*
        Возвращается false, если возникла какая-та ошибка в БД.
     */
    public boolean Remove(Session session, int id)
    {
        if (db_handler == null) return false;

        if (db_handler.Update_DialogsLastSessionsChanges(id))
        {
            if (countUsersOnlineById(id) < 2) db_handler.Update_Online(id, false);
            if (sessionMap.remove(session) != null) return true;
        }
        return false;
    }

    /**
     * Метод ищет в контейнере данную сессию.
     * @param session
     * @return true, если сессия существует в контейнере.
     * @return  false, в противном случае
     */

    public boolean existInSCBySession(Session session)
    {
        for (Map.Entry<Session, Integer> entry : sessionMap.entrySet())
            if (entry.getKey().equals(session)) return true;

        return false;
    }


    /*
        Если Ids == null, то возвращается пустой список, но не null!!!
     */
    public List<Session> getSessionsByIds(List<Integer> Ids)
    {
        List<Session> list = new LinkedList<>();

        if (Ids == null) return list;

        for (int id : Ids)
            for (Map.Entry<Session, Integer> entry : sessionMap.entrySet())
                if (entry.getValue() == id) list.add(entry.getKey());

        return list;
    }

    private int countUsersOnlineById(int id)
    {
        int count = 0;

        for (Map.Entry<Session, Integer> entry : sessionMap.entrySet())
            if (entry.getValue() == id) count++;

        return count;
    }

}
