package com.mshl.DB_Broker;

import com.mshl.DB_Handler.DB_Handler;
import com.mshl.PData.FromObject;
import com.mshl.PData.PQuery;
import com.mshl.ProtocolExceptions.ProtocolException;

import java.sql.SQLException;

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
     */
    public void saveMsg(PQuery pQuery)
    {

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
