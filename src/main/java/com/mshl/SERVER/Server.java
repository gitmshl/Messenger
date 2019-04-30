package com.mshl.SERVER;

import com.google.gson.Gson;
import com.mshl.PData.PQuery;
import com.mshl.ProtocolExceptions.ProtocolException;
import com.mshl.Protocol_Handler.Protocol_Handler;
import com.mshl.SESSIONS_CONTAINER.SessionsContainer;

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
        user_id = Integer.parseInt(session.getRequestParameterMap().get("id").get(0));
        gson = new Gson();
        protocol_handler = new Protocol_Handler(user_id);
        SC = new SessionsContainer();
        if (SC.Add(session, user_id))
            System.out.println("Id: " + user_id + ". Открыто соединение");
        else
            System.err.println("Id: " + user_id + ". Соединение не может быть открыто из-за ошибки с БД");
    }

    @OnMessage
    public void onMessage(Session session, String msg)
    {
        pQuery = gson.fromJson(msg, PQuery.class);
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
        System.out.println("Id: " + user_id + ". Соединение закрыто");
    }


    private int user_id;
    private Protocol_Handler protocol_handler;
    private SessionsContainer SC;
    private Gson gson;
    private PQuery pQuery;
}
