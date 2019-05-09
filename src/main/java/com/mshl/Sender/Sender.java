package com.mshl.Sender;

import com.google.gson.Gson;
import com.mshl.CONSTS.Consts;
import com.mshl.Group_Handler.Group_Handler;
import com.mshl.PData.PQuery;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;

public class Sender
{
    public Sender()
    {
        gson = new Gson();
        group_handler = new Group_Handler();
    }

    public void sendMessagesListToUser(Session session, PQuery pQuery, String data)
    {
        PQuery pQueryResponse = new PQuery(
                121, Consts.SERVER_ID, pQuery.getDialog_id(),
                Consts.DATA_TYPE_MESSAGES_LIST,
                data
        );
        try
        {
            session.getBasicRemote().sendText(gson.toJson(pQueryResponse));
        } catch (IOException e){}
    }

    /**
     * Отправляет PQuery запрос с data_type = 2 (DATA_TYPE_DIALOGS_LIST).
     * Вызывается DB Broker-ом при получении списка диалогов данного пользователя
     * из БД.
     * @param session - сессия пользователя, запросившего список диалогов.
     * @param pQuery - запрос, отправленный этим пользователем на сервер
     * @param data - json представление списка диалогов, полученных DB Handler-ом.
     */
    public void sendDialogsListToUser(Session session, PQuery pQuery, String data)
    {
        PQuery pQueryResponse = new PQuery(
                120, Consts.SERVER_ID, pQuery.getDialog_id(),
                Consts.DATA_TYPE_DIALOGS_LIST,
                data
        );
        try
        {
            session.getBasicRemote().sendText(gson.toJson(pQueryResponse));
        } catch (IOException e){}
    }

    public void sendErr(Session session, int errCode, String msg)
    {
        send(session, new PQuery(
                errCode, Consts.SERVER_ID, -1,
                Consts.DATA_TYPE_TEXT,
                msg
                ));
    }

    /**
     * Отправляет сообщение всем участникам диалога dialog_id
     * Нету гарантии отправки сообщений всем пользователям.
     * Если возникнет ошибка в доставке сообщения, то она просто игнорируется, и
     * никто об этом не узнает.
     * @param dialog_id - id диалога, на который нужно отправить сообщение
     * @param pQuery - объект сообщения (не имеет значение, какого типа)
     */
    public void sendToDialog(int dialog_id, PQuery pQuery)
    {
        List<Session> sessions = group_handler.getSessionsByDialogId(dialog_id);
        if (sessions == null) return; /// возникла ошибка (игнорируется)
        String message = gson.toJson(pQuery);
        for (Session session : sessions)
        {
            try
            {
                session.getBasicRemote().sendText(message);
            }
            catch (IOException e){}
        }
    }


    public void sendConfirmation(Session session, int confCode)
    {
        send(session, new PQuery(
                confCode, Consts.SERVER_ID, -1,
                Consts.DATA_TYPE_TEXT,
                ""
        ));
    }

    private void send(Session session, PQuery pQuery)
    {
        String message = gson.toJson(pQuery);
        try
        {
            session.getBasicRemote().sendText(message);
        }
        catch (IOException e){}
    }

    private Gson gson;
    private Group_Handler group_handler;
}
