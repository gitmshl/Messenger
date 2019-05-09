package com.mshl.PData;

import java.util.ArrayList;
import java.util.List;

/**
 * data_type = 3. Класс передачи списка сообщений.
 */
public class PQueryMessagesList
{
    public void setLastMsgInf(LastMsgInf lastMsgInf)
    {
        this.lastMsgInf = lastMsgInf;
    }

    public void setMessageInfList(List<MessageInf> list)
    {
        messageInfList = list;
    }

    private LastMsgInf lastMsgInf;
    private List<MessageInf> messageInfList = new ArrayList<>();
}


