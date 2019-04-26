package com.mshl.Connector;

import java.sql.Connection;

public class Connector
{

    public Connection getConnection()
    {
        return null;
    }

    /**
     * Закрывает соединение. В действительности, он может и не закрывать
     * соединение, а, просто, освободить его для других вызовов.
     *
     * @return true, если соединение успешно закрыто.
     * @return false, если произошла ошибка на уровне SQL.
     */
    public boolean closeConnection(Connection connection)
    {
        return true;
    }
}
