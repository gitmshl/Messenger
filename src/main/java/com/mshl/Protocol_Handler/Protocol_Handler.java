package com.mshl.Protocol_Handler;

import com.mshl.DB_Broker.DB_Broker;
import com.mshl.PData.PQuery;
import com.mshl.ProtocolExceptions.ProtocolException;
import com.mshl.SESSIONS_CONTAINER.SessionsContainer;
import com.mshl.Sender.Sender;

import javax.websocket.Session;
import java.sql.SQLException;

public class Protocol_Handler
{

    public Protocol_Handler()
    {
        sender = new Sender();
        db_broker = new DB_Broker();
        SC = new SessionsContainer();
    }

    public void handl(Session session, PQuery pQuery) throws ProtocolException, SQLException {
        try
        {
            if (!Check(session, pQuery))
            {
                sender.sendErr(session, 150, "wrong form of protocol");
                throw new ProtocolException();
            }
        }
        catch (SQLException e)
        {
            sender.sendErr(session, 164, "Data Base connection error");
            throw new SQLException();
        }

        int protocol_code = pQuery.getCode();

        switch (protocol_code)
        {
            case 30: handl_30(session, pQuery); break;
        }
    }


    private void handl_30(Session session, PQuery pQuery) throws SQLException
    {
        if (SC.existInSCBySession(session))
        {
            sender.sendConfirmation(session, 130);
        }
        else
        {
            sender.sendErr(session, 161, "Data Base error");
            throw new SQLException();
        }
    }

    private boolean Check(Session session, PQuery pQuery) throws SQLException
    {
        return true;
    }

    private int user_id; /// создается в момент аутентификации (создание класса) и
                            ///не меняется до разрыва соединения
    private Sender sender;
    private DB_Broker db_broker;
    private SessionsContainer SC;
}
