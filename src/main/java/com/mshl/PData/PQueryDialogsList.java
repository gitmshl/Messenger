package com.mshl.PData;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * data_type = 2. Класс передачи списка диалогов пользователя.
 */
public class PQueryDialogsList
{
    public void Add(int dialog_id, String dialog_name, String dialog_img, String last_msg, String from_user_name, Timestamp last_msg_time, int from_user_id, Timestamp last_read_time, Timestamp my_last_reading_time)
    {
        DialogInf dialogInf = new DialogInf(
             dialog_id, dialog_name, dialog_img, last_msg, from_user_name,
             last_msg_time, from_user_id, last_read_time, my_last_reading_time
        );
        dialogInfList.add(dialogInf);
    }

    public void Add(DialogInf dialogInf)
    {
        dialogInfList.add(dialogInf);
    }

    private List<DialogInf> dialogInfList = new ArrayList<>();
}
