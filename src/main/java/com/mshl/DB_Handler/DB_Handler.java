package com.mshl.DB_Handler;

import com.google.gson.Gson;
import com.mshl.Connector.Connector;
import com.mshl.PData.FromObject;
import com.mshl.PData.PQueryDialogsList;
import com.mshl.ProtocolExceptions.ProtocolException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DB_Handler
{
    public DB_Handler()
    {
        connector = new Connector();
        gson = new Gson();
    }


    /**
     * Метод, который извлекает из БД список диалогов данного пользователя user_id, создает объект
     * PQueryDialogsList и упаковывает его в JSON.
     * @param user_id
     * @return строку json из списка диалогов данного пользователя user_id
     * @throws SQLException, если возникнет ошибка в БД
     */
    public String getDialogsListByUserId(int user_id) throws SQLException
    {
        Connection connection = GetConnection();
        if (connection == null) throw new SQLException();

        try(PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "select r.dialog_id, r.dialog_name, d.dialog_img, r.last_msg, r.from_user_name, r.last_msg_time, r.from_user_id, r.last_time_read, d.time as my_last_reading_time from \"Dialogs\" as d join \"ReadTable\" as r on r.dialog_id = d.dialog_id where d.user_id = ? order by r.last_msg_time desc;"
                ))
        {
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            PQueryDialogsList pQueryDialogsList = new PQueryDialogsList();
            while (resultSet.next())
            {
                int dialog_id = resultSet.getInt("dialog_id");
                String dialog_name = resultSet.getString("dialog_name");
                String dialog_img = resultSet.getString("dialog_img");
                String last_msg = resultSet.getString("last_msg");
                String from_user_name = resultSet.getString("from_user_name");
                Timestamp last_msg_time = resultSet.getTimestamp("last_msg_time");
                int from_user_id = resultSet.getInt("from_user_id");
                Timestamp last_time_read = resultSet.getTimestamp("last_time_read");
                Timestamp my_last_reading_time = resultSet.getTimestamp("my_last_reading_time");
                pQueryDialogsList.Add(dialog_id, dialog_name, dialog_img, last_msg, from_user_name,
                        last_msg_time, from_user_id, last_time_read, my_last_reading_time);
            }
            return gson.toJson(pQueryDialogsList);
        }
        finally
        {
            connector.closeConnection(connection);
        }
    }


    /**
     * Апдейтит таблицу ReadTable, изменяя в ней время last read time на текущее.
     * Вызывается при запросе 1, т.е. запросе о прочтении сообщений в диалоге.
     * @param dialog_id
     */
    public void updateReadTable_MessageRead(int dialog_id) throws SQLException
    {
        Connection connection = GetConnection();
        if (connection == null) throw new SQLException();

        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "update \"ReadTable\" set last_time_read=now() where dialog_id=?;"
                    ))
        {
            preparedStatement.setInt(1, dialog_id);
            preparedStatement.executeUpdate();
        }
        finally
        {
            connector.closeConnection(connection);
        }
    }

    public void updateReadTable_NewMessage(int dialog_id, int from_user_id, String from_user_name,
                                           String last_msg) throws SQLException
    {
        Connection connection = GetConnection();
        if (connection == null) throw new SQLException();

        try(PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "update \"ReadTable\" set from_user_id=?, from_user_name=?, last_msg_time=now(), last_msg=? where dialog_id=?;"))
        {
            preparedStatement.setInt(1, from_user_id);
            preparedStatement.setString(2, from_user_name);
            preparedStatement.setString(3, last_msg);
            preparedStatement.setInt(4, dialog_id);
            preparedStatement.executeUpdate();
        }
        finally
        {
            connector.closeConnection(connection);
        }
    }

    public void updateDialogsTime(int dialog_id, int user_id) throws SQLException
    {
        Connection connection = GetConnection();
        if (connection == null) throw new SQLException();
        try(PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "update \"Dialogs\" set time=now() where dialog_id=? and user_id=?;"
                ))
        {
            preparedStatement.setInt(1, dialog_id);
            preparedStatement.setInt(2, user_id);
            preparedStatement.executeUpdate();
        }
        finally
        {
            connector.closeConnection(connection);
        }
    }

    public void insertMessages_NewMessage(int dialog_id, int from_user_id,
                                          String from_user_name,
                                          String from_user_avatar,
                                          Timestamp time, String txt,
                                          String img) throws SQLException
    {
        Connection connection = GetConnection();
        if (connection == null) throw new SQLException();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "insert into \"Messages\" values (?, ?, ?, ?, ?, ?, ?);"))
        {
            preparedStatement.setInt(1, dialog_id);
            preparedStatement.setInt(2, from_user_id);
            preparedStatement.setString(3, from_user_name);
            preparedStatement.setString(4, from_user_avatar);
            preparedStatement.setTimestamp(5, time);
            preparedStatement.setString(6, txt);
            preparedStatement.setString(7, img);
            preparedStatement.executeUpdate();
        }
        finally
        {
            connector.closeConnection(connection);
        }
    }

    /**
     * Возвращает строку из таблицы UsersInformation по id пользователя.
     * @param user_id
     * @return
     * @throws SQLException в случае ошибки в БД
     */
    public FromObject getUserInformationById(int user_id) throws SQLException, ProtocolException
    {
        Connection connection = GetConnection();
        if (connection == null) throw new SQLException();
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "select * from \"UsersInformation\" where user_id=?;"))
        {
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) throw new ProtocolException("wrong user_id in Protocol");
            while (resultSet.next())
            {
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");
                String email = resultSet.getString("email");
                String avatar = resultSet.getString("avatar");
                Timestamp last_visit_time = resultSet.getTimestamp("last_visit_time");
                boolean online = resultSet.getBoolean("online");
                return new FromObject(user_id, name, login, email, avatar, last_visit_time, online);
            }
            throw new ProtocolException("User with id = user_id doesn't exist");
        }
        finally
        {
            connector.closeConnection(connection);
        }
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
    private Gson gson;
}
