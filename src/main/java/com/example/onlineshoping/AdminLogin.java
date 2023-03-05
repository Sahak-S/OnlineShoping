package com.example.onlineshoping;


import com.example.onlineshoping.db.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Getting all the parameters from the frontend (admin)
            String email = request.getParameter("email");
            String pass = request.getParameter("upass");

            //Retriving our session
            HttpSession hs = request.getSession();

            //Calling Connection method
            Connection con = DatabaseConnection.getConnection();

            //Creating Statement
            Statement st = con.createStatement();

            //Querying inside the database
            ResultSet resultset = st.executeQuery("select * from tbladmin where email='" + email + "' AND password='" + pass + "'");
            //If all the details are correct
            if (resultset.next()) {
                hs.setAttribute("uname", resultset.getString("name"));
                //Redirecting admin to dashboard page
                response.sendRedirect("dashboard.jsp");

            } else {
                //If details are wrong
                String message = "You have enter wrong credentials";
                hs.setAttribute("credential", message);
                //Redirecting admin to admin login page
                response.sendRedirect("admin-login.jsp");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
