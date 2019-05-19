package com.mshl.AuthenticationServlet;

import com.mshl.DB_Handler.DB_Handler;
import com.mshl.HASH_STORE.HST;
import com.mshl.PData.FromObject;
import com.mshl.ProtocolExceptions.ProtocolException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/aut")
public class AuthenticationServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/plain");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login.length() == 0 || password.length() == 0)
        {
            writer.write("0");
            return;
        }
        try
        {
            FromObject fromObject = db_handler.getUserInformationByLoginAndPassword(login, password);
            int id = fromObject.getUser_id();
            HST.add(req.getSession().getId(), id);
            resp.addCookie(new Cookie("ID", id + ""));
            writer.write("1");
            return;
        }
        catch (SQLException e)
        {
            writer.write("-1");
            System.err.println("SQLException");
            return;
        }
        catch (ProtocolException e)
        {
            writer.write("0");
            return;
        }

    }

    private static DB_Handler db_handler = new DB_Handler();

}
