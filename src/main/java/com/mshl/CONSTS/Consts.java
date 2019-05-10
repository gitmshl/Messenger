package com.mshl.CONSTS;

import java.util.ArrayList;
import java.util.List;

public class Consts
{
    public static final int SERVER_ID = -1;
    public static final int DATA_TYPE_TEXT = 0; /// data = just string
    /*
        data = "some string..."
     */
    public static final int DATA_TYPE_MESSAGE = 1; /// data = {name: , avatar: , msg: }
    /*
        data =
        {
            name: ,
            avatar: ,
            msg: ,
        }
     */
    public static final int DATA_TYPE_DIALOGS_LIST = 2;
    /*
         data =
         {
            dialogInfList: [
                {
                    dialog_id: ,
                    dialog_name: ,
                    dialog_img: ,
                    last_msg: ,
                    from_user_name: ,
                    last_msg_time: ,
                    from_user_id: ,
                    last_read_time: ,
                    my_last_reading_time:
                },
                {
                    ....
                }
            ]
         }
    */
    public static final int DATA_TYPE_MESSAGES_LIST = 3;
    /*
        data =
        {
            lastMsgInf:
            {
                from_user_id: ,
                last_read_time: ,
                last_msg_time: ,
                my_last_reading_time:
            },
            messageInfList: [
                {
                    from_user_id: ,
                    from_user_name: ,
                    from_user_avatar: ,
                    msg: ,
                    time:
                },
                {
                    ...
                }
            ]
        }
     */
    public static final List<Integer> Protocol_Codes = new ArrayList<>();

    static
    {
        Protocol_Codes.add(0);
        Protocol_Codes.add(1);
        Protocol_Codes.add(10);
        Protocol_Codes.add(20);
        Protocol_Codes.add(21);
        Protocol_Codes.add(30);
    }

    public static boolean existCode(int code)
    {
        return Protocol_Codes.contains(code);
    }

}
