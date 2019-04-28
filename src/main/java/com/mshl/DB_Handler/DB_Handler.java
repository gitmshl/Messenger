package com.mshl.DB_Handler;

import com.mshl.Connector.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB_Handler
{
    public DB_Handler()
    {
        connector = new Connector();
    }


    /* Тестирование: 28.04.19 23:40 */
    public boolean Update_DialogsLastSessionsChanges(int id)
    {
        Connection connection = GetConnection();
        if (connection == null) return false;

        boolean result = true;

        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "update \"DialogsLastSessionsChanges\" SET last_change_time = now() where dialog_id" +
                                    "  in (select dialog_id from \"Dialogs\" where user_id = ?);") )
        {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            result = false;
        }
        finally
        {
            connector.closeConnection(connection);
            return result;
        }
    }

    /* Тестирование: 28.04.19 23:06 */
    public boolean Update_Online(int id, boolean online)
    {

        Connection connection = GetConnection();
        if (connection == null) return false;

        boolean result = true;

        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "update \"UsersInformation\" SET online = ?, last_visit_time = now() where user_id = ?;");)
        {
            preparedStatement.setBoolean(1, online);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            result = false;
        }
        finally
        {
            connector.closeConnection(connection);
            return result;
        }

    }


    private Connection GetConnection()
    {
        if (connector == null) return null;
        return connector.getConnection();
    }

    private Connector connector;
}
