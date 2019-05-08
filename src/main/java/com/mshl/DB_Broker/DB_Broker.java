package com.mshl.DB_Broker;

import com.mshl.DB_Handler.DB_Handler;
import com.mshl.PData.FromObject;
import com.mshl.PData.PQuery;
import com.mshl.ProtocolExceptions.ProtocolException;

import java.sql.SQLException;
import java.sql.Timestamp;

public class DB_Broker
{
    public DB_Broker()
    {
        db_handler = new DB_Handler();
    }

    /**
     * Сохраняет сообщение в БД (вызывается при отправке сообщения)
     * @param pQuery - запрос от клиента
     * @throws java.sql.SQLException, если возникла ошибка в БД
     * @throws ProtocolException, если ошибка в протоколе (например, неверно указан id пользователя
     * (не существует в БД такого id))
     */
    public void saveMsg(PQuery pQuery) throws SQLException, ProtocolException
    {
        FromObject fromObject = getFromObjectById(pQuery.getFrom());
        int dialog_id = pQuery.getDialog_id(),
                from_user_id = pQuery.getFrom();
        String from_user_name = fromObject.getName(),
                from_user_avatar = fromObject.getAvatar(),
                txt = pQuery.getData();
        Timestamp CurrentTime = new Timestamp(System.currentTimeMillis());

        db_handler.insertMessages_NewMessage(dialog_id, from_user_id, from_user_name, from_user_avatar,
                CurrentTime, txt, "");

        db_handler.updateDialogsTime(dialog_id, from_user_id);

        db_handler.updateReadTable_NewMessage(dialog_id, from_user_id, from_user_name, txt);
    }

    /**
     * Возвращает объект FromObject - вся необходимая информация из таблицы
     * UsersInformation, полученная по id пользователя
     * @param user_id
     * @return FromObject, если все прошло успешно
     * @throws SQLException, в случае ошибки в БД
     * @throws ProtocolException, в случае, если такая строка не найдена (ошибка в id пользователя, протокол указан неверно)
     */
    public FromObject getFromObjectById(int user_id) throws SQLException, ProtocolException
    {
        return db_handler.getUserInformationById(user_id);
    }

    private DB_Handler db_handler;
}
