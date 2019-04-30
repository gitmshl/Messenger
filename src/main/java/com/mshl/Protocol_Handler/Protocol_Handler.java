package com.mshl.Protocol_Handler;

import com.mshl.DB_Broker.DB_Broker;
import com.mshl.PData.PQuery;
import com.mshl.ProtocolExceptions.ProtocolException;
import com.mshl.Sender.Sender;

import javax.websocket.Session;
import java.sql.SQLException;

public class Protocol_Handler
{

    public Protocol_Handler()
    {
        sender = new Sender();
        db_broker = new DB_Broker();
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

        }
    }

    private boolean Check(Session session, PQuery pQuery) throws SQLException
    {
        return true;
    }

    private Sender sender;
    private DB_Broker db_broker;
}
