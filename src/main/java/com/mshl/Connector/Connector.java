package com.mshl.Connector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Connector
{

    Connector()
    {
        ctx = getInitialContext();
    }

    public Connection getConnection()
    {
        if (ctx == null)
        {
            ctx = getInitialContext();
            if (ctx == null) return null;
        }

        int attempts = 0;
        while (CountConnections >= MaxConnections)
        {
            if (attempts > MaxAttempts) return null;
            try
            {
                Thread.sleep((int) Math.random() * Delay);
                attempts++;
            }
            catch (InterruptedException e)
            {
                return null;
            }
        }

        attempts = 0;
        while (attempts <= MaxAttempts)
        {
            try
            {
                Connection conn = ((DataSource) ctx.lookup("java:comp/env/jdbc/root")).getConnection();
                if (conn != null)
                {
                    addCountConnections(1);
                    return conn;
                }

            }
            catch (SQLException | NamingException e)
            {}
            finally
            {
                try
                {
                    Thread.sleep((int) Math.random() * Delay);
                    attempts++;
                }
                catch (InterruptedException e1)
                {
                    return null;
                }
            }
        }

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
        if (connection == null) return true;
        try
        {
            connection.close();
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }

    private InitialContext getInitialContext()
    {
        try
        {
            return new InitialContext();
        }
        catch (NamingException e)
        {
            return null;
        }
    }

    private static void addCountConnections(int add)
    {
        if (CountConnections + add >= 0 && CountConnections + add <= MaxConnections)
            CountConnections += add;
    }

    private static int CountConnections = 0;
    private static int MaxConnections = 100;
    private static int MaxAttempts = 10;
    private static int Delay = 5000;
    private InitialContext ctx;

}
