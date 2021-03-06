package com.mshl.Protocol_Handler;

import com.mshl.CONSTS.Consts;
import com.mshl.Caster.Caster;
import com.mshl.DB_Broker.DB_Broker;
import com.mshl.HASH_STORE.HST;
import com.mshl.PData.FromObject;
import com.mshl.PData.PQuery;
import com.mshl.ProtocolExceptions.ProtocolException;
import com.mshl.SESSIONS_CONTAINER.SessionsContainer;
import com.mshl.Sender.Sender;

import javax.websocket.Session;
import java.sql.SQLException;

public class Protocol_Handler
{

    public Protocol_Handler(String uid, int user_id)
    {
        this.user_id = user_id;
        this.uid = uid;
        sender = new Sender();
        db_broker = new DB_Broker();
        SC = new SessionsContainer();
        caster = new Caster();
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
            case 0: handl_0(pQuery); break;
            case 1: handl_1(pQuery); break;
            case 10: handl_10(session, pQuery); break;
            case 11: handl_11(session, pQuery); break;
            case 20: handl_20(session, pQuery); break;
            case 21: handl_21(session, pQuery); break;
            case 30: handl_30(session, pQuery); break;
            case 40: handl_40(session, pQuery); break;
        }
    }


    /**
     * Не производим проверку существования данной сессии в Контейнере Сессий
     * Это не настолько важная информация. Даже если возникнет какая та ошибка
     * (т.е. данную сессию получит какой то другой пользователь), это не такая
     * важная информация, даже если этот пользователь прочтет ее.
     * Более того, можно на стороне клиента производить проверку существования
     * диалога, перед добавлением в DOM дерево, на всякий случай.
     * @param pQuery
     */
    private void handl_0(PQuery pQuery)
    {
        sender.sendToDialog(pQuery.getDialog_id(), pQuery);
    }

    private void handl_1(PQuery pQuery) throws SQLException
    {
        db_broker.markReadMessage(pQuery);
        sender.sendToDialog(pQuery.getDialog_id(), pQuery);
    }

    private void handl_10(Session session, PQuery pQuery) throws SQLException, ProtocolException
    {
        FromObject fromObject = db_broker.getFromObjectById(pQuery.getFrom());
        db_broker.saveMsg(pQuery, fromObject);
        sender.sendConfirmation(session, 110);

        PQuery pQueryForDialog = caster.getPQueryFromPQDMesage(pQuery, fromObject);
        sender.sendToDialog(pQueryForDialog.getDialog_id(), pQueryForDialog);
    }

    private void handl_11(Session session, PQuery pQuery)
    {
        System.out.println("11 запрос");
        db_broker.sendToUserUserInformation(session, pQuery, uid);
    }

    private void handl_20(Session session, PQuery pQuery) throws SQLException
    {
        db_broker.sendToUserDialogsListByUserId(session, pQuery);
    }

    private void handl_21(Session session, PQuery pQuery) throws SQLException
    {
        db_broker.sendToUserMessagesList(session, pQuery);
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

    private void handl_40(Session session, PQuery pQuery)
    {
        HST.destroy(uid, pQuery.getFrom());
        sender.send_140(uid, pQuery.getFrom());
    }

    /*
        Пока что не делаем проверок, требующих идти в БД. Это слишком долго ;(
     */
    private boolean Check(Session session, PQuery pQuery) throws SQLException
    {
        if (user_id != pQuery.getFrom()) return false;
        if (!Consts.existCode(pQuery.getCode())) return false;
        if (pQuery.getDialog_id() < -1) return false;
        if (pQuery.getData() == null) return false;
        return true;
    }

    private int user_id; /// создается в момент аутентификации (создание класса) и
                            ///не меняется до разрыва соединения
    private String uid;
    private Sender sender;
    private DB_Broker db_broker;
    private SessionsContainer SC;
    private Caster caster;

}
