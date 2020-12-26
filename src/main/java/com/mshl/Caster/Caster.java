package com.mshl.Caster;


import com.google.gson.Gson;
import com.mshl.CONSTS.Consts;
import com.mshl.PData.FromObject;
import com.mshl.PData.PQuery;
import com.mshl.PData.PQueryDataMessage;
import com.mshl.ProtocolExceptions.ProtocolException;
import com.mshl.Sender.Sender;

import javax.websocket.Session;

public class Caster
{

    public Caster()
    {
        sender = new Sender();
        gson = new Gson();
    }

    /**
     * Возвращает объект PQuery, который содержит в поле data
     * json строку, образованную из класса PQueryDataMessage.
     * Такой вид передачи данных используется при отправке сообщений.
     * @return PQuery
     */
    public PQuery getPQueryFromPQDMesage(PQuery pQuery, FromObject fromObject)
    {
        PQueryDataMessage pQueryDataMessage = new PQueryDataMessage(pQuery.getFrom(),
                fromObject.getName(), fromObject.getAvatar(),
                pQuery.getData()
        );

        String data = gson.toJson(pQueryDataMessage);

        PQuery pQuery1 = new PQuery(10, pQuery.getFrom(),
                pQuery.getDialog_id(), Consts.DATA_TYPE_MESSAGE, data);

        return pQuery1;
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
    private Gson gson;
}
