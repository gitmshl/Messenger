package com.mshl.CHECK_IN;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CheckInServlet", urlPatterns = "/checkinservlet")
public class checkInServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String type = request.getParameter("type");
        if (type == null) return;
        response.setContentType("text/plain");
        if (type.equals("email"))
        {

        }
        else if (type.equals("login"))
        {

        }
        else if (type.equals("submit"))
        {

        }
    }

    private boolean existEmail(String email)
    {
        return true;
    }

    private boolean existLogin(String login)
    {
        return true;
    }

}
