package com.mshl.PData;

public class PQuery
{
    public PQuery(int code, int from, int dialog_id, int data_type, String data)
    {
        this.code = code;
        this.from = from;
        this.dialog_id = dialog_id;
        this.data_type = data_type;
        this.data = data;
    }

    public int getCode()
    {
        return code;
    }

    public int getFrom()
    {
        return from;
    }

    public int getDialog_id()
    {
        return dialog_id;
    }

    public int getData_type()
    {
        return data_type;
    }

    public String getData()
    {
        return data;
    }

    @Override
    public String toString()
    {
        return "Code: " + code + "\n" +
                "From: " + from + "\n" +
                "Dialog_id: " + dialog_id + "\n" +
                "DataType: " + data_type + "\n" +
                "Data: " + data;
    }

    private int code;
    private int from;
    private int dialog_id;
    private int data_type;
    private String data;
}
