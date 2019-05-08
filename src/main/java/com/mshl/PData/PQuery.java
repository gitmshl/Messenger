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

    public void setCode(int code) {
        this.code = code;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setDialog_id(int dialog_id) {
        this.dialog_id = dialog_id;
    }

    public void setData_type(int data_type) {
        this.data_type = data_type;
    }

    public void setData(String data) {
        this.data = data;
    }

    private int code;
    private int from;
    private int dialog_id;
    private int data_type;
    private String data;
}
