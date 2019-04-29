package com.mshl.DB_Handler;

import com.mshl.Connector.Connector;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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

    /*
        Если возникнет проблема в БД, то вернется null.
        В противном случае, возвращается список (быть может, пустой)
     */
    public List<Integer> getIdsByDialogId(int dialog_id)
    {
        List<Integer> Ids = new LinkedList<>();
        Connection connection = GetConnection();
        if (connection == null) return null;

        boolean result_is_null = false;

        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "select user_id from \"Dialogs\" where dialog_id = ?;");)
        {
            preparedStatement.setInt(1, dialog_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Ids.add(resultSet.getInt("user_id"));
            }
        }
        catch (SQLException e)
        {
            result_is_null = true;
        }
        finally
        {
            connector.closeConnection(connection);
            return result_is_null ? null : Ids;
        }
    }

    /*
        Если возникнет проблема с БД, то кидается SQLException.
        Если такого диалога нету, то возвращается null.
     */
    public Timestamp getLastChangeTime_DialogsLastSessionsChanges(int dialog_id) throws SQLException
    {
        Connection connection = GetConnection();
        if (connection == null) throw new SQLException("Нет соединения с БД. getLastChangeTime function. DB_Handler");

        Timestamp result = null;

        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "select last_change_time from \"DialogsLastSessionsChanges\" where dialog_id = ?;");)
        {
            preparedStatement.setInt(1, dialog_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) result = resultSet.getTimestamp("last_change_time");
        }
        catch (SQLException e)
        {
            connector.closeConnection(connection);
            throw new SQLException("Нет соединения с БД. getLastChangeTime function. DB_Handler");
        }

        connector.closeConnection(connection);
        return result;
    }

    private Connection GetConnection()
    {
        if (connector == null) return null;
        return connector.getConnection();
    }

    private Connector connector;
}
