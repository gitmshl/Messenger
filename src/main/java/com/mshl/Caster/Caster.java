package com.mshl.Caster;

import com.mshl.ProtocolExceptions.ProtocolException;
import com.mshl.Sender.Sender;

import javax.websocket.Session;

public class Caster
{

    public Caster()
    {
        sender = new Sender();
    }

    /**
     * Данный метод возвращает из data целое число (если все прошло успешно).
     * Если же возникнет ошибка в переводе в число, то метод отправляет запрос
     * 150 об ошибке данному пользователю. Сессия данного пользователя, заранее,
     * передается в метод.
     * @param data - строка с числом
     * @param session - сессия пользователя, которому нужно отправить сообщение об
     *                ошибке, в случае оного.
     * @return Возвращает целое число - число, которое содержится в строке data
     * @throws ProtocolException
     */
    public int getInt(String data, Session session) throws ProtocolException {
        try
        {
            return Integer.parseInt(data);
        }
        catch (NumberFormatException e)
        {
            sender.sendErr(session, 150, "DataType wrong");
            throw new ProtocolException();
        }
    }

    private Sender sender;
}
