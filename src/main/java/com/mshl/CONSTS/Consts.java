package com.mshl.CONSTS;

import java.util.ArrayList;
import java.util.List;

public class Consts
{
    public static final int SERVER_ID = -1;
    public static final int DATA_TYPE_TEXT = 0; /// data = just string
    public static final int DATA_TYPE_MESSAGE = 1; /// data = {name: , avatar: , msg: }
    public static final List<Integer> Protocol_Codes = new ArrayList<>();

    static
    {
        Protocol_Codes.add(0);
        Protocol_Codes.add(1);
        Protocol_Codes.add(10);
        Protocol_Codes.add(30);
    }

    public static boolean existCode(int code)
    {
        return Protocol_Codes.contains(code);
    }

}
