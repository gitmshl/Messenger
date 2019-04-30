package com.mshl.Sender;

import com.google.gson.Gson;
import com.mshl.CONSTS.Consts;
import com.mshl.PData.PQuery;

import javax.websocket.Session;
import java.io.IOException;

public class Sender
{
    public Sender()
    {
        gson = new Gson();
    }

    public void sendErr(Session session, int errCode, String msg)
    {
        send(session, new PQuery(
                errCode, Consts.SERVER_ID, -1,
                Consts.DATA_TYPE_EMPTY_TEXT,
                msg
                ));
    }

    public void sendConfirmation(Session session, int confCode)
    {
        send(session, new PQuery(
                confCode, Consts.SERVER_ID, -1,
                Consts.DATA_TYPE_EMPTY_TEXT,
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

}
