package com.mshl.HASH_STORE;

import java.sql.Time;
import java.sql.Timestamp;

class IDINF
{

    public IDINF(int id, int count)
    {
        this.id = id;
        this.count = count;
        if (count == 0)
            this.time = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getTime()
    {
        return time;
    }

    public int getId()
    {
        return id;
    }

    public int getCount() {
        return count;
    }

    public void incCount()
    {
        count++;
    }

    public void decCount()
    {
        count--;
        if (count == 0)
            time = new Timestamp(System.currentTimeMillis());
    }

    public void setTime(Timestamp time)
    {
        this.time = time;
    }

    public void setTime()
    {
        this.time = new Timestamp(System.currentTimeMillis());
    }

    private int id;
    private int count;
    private Timestamp time;
}