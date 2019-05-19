package com.mshl.AuthenticationServlet;

public class ResponseObject
{
    public ResponseObject(int success, String data)
    {
        this.success = success;
        this.data = data;
    }

    private int success;
    private String data;
}
