package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {
    // return a JSON representation of the currently authenticated user for this
    // JSESSIONID.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // grab current session, if it exists, otherwise get a null value
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("id") != null) {
            try {
                int id = Integer.parseInt(session.getAttribute("id").toString());
                String name = session.getAttribute("name").toString();
                String username = session.getAttribute("username").toString();
                String email = session.getAttribute("email").toString();
                Employee emp = new Employee(id,name,username, email);
                resp.getWriter().write((new ObjectMapper()).writeValueAsString(emp));
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().write("{\"session\":null}");
            }
        } else {
            resp.getWriter().write("{\"session\":null}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}