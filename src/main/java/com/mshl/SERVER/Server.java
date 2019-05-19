package com.mshl.SERVER;

import com.google.gson.Gson;
import com.mshl.DB_Handler.DB_Handler;
import com.mshl.HASH_STORE.HST;
import com.mshl.PData.FromObject;
import com.mshl.PData.PQuery;
import com.mshl.ProtocolExceptions.ProtocolException;
import com.mshl.Protocol_Handler.Protocol_Handler;
import com.mshl.SESSIONS_CONTAINER.SessionsContainer;
import com.mshl.Sender.Sender;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.sql.SQLException;

@ServerEndpoint(value = "/tmessenger")

public class Server
{
    @OnOpen
    public void onOpen(Session session)
    {
        /*
            Желательно произвести проверку того, что user_id дейтсвительно int. Строка ниже может
            сгенерировать исключение, если что.
        */

        user_id = Integer.parseInt(session.getRequestParameterMap().get("id").get(0));
        uid = session.getRequestParameterMap().get("UID").get(0);

        db_handler = new DB_Handler();
        sender = new Sender();

        try
        {
            db_handler.getUserInformationById(user_id);
            HST.add(uid, user_id);
            System.out.println("user_id = " + user_id + " uid = " + uid);

            gson = new Gson();
            protocol_handler = new Protocol_Handler(uid, user_id);
            SC = new SessionsContainer();
            if (SC.Add(session, user_id))
                System.out.println("Id: " + user_id + ". Открыто соединение");
            else
            {
                System.err.println("Id: " + user_id + ". Соединение не может быть открыто из-за ошибки с БД");
                onClose(session);
                return;
            }
        }
        catch (SQLException e)
        {
            sender.sendErr(session, 161, "DB error");
            onClose(session);
            return;
        }
        catch (ProtocolException e)
        {
            sender.sendErr(session, 150, "Protocol error");
            onClose(session);
            return;
        }

    }

    @OnMessage
    public void onMessage(Session session, String msg)
    {
        pQuery = gson.fromJson(msg, PQuery.class);
        System.out.println(pQuery);
        try
        {
            protocol_handler.handl(session, pQuery);
        } catch (ProtocolException e) {
            System.err.println("Ошибка в протоколе");
        } catch (SQLException e) {
            System.err.println("Ошибка с БД");
        }
    }

    @OnClose
    public void onClose(Session session)
    {
        SC.Remove(session, user_id);
        HST.remove(uid, user_id);
        System.out.println("Id: " + user_id + ". Соединение закрыто");
    }


    private int user_id;
    private String uid;
    private Protocol_Handler protocol_handler;
    private SessionsContainer SC;
    private Sender sender;
    private DB_Handler db_handler;
    private Gson gson;
    private PQuery pQuery;
}
