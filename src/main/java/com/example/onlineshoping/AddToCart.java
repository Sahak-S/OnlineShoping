package com.example.onlineshoping;


import com.example.onlineshoping.db.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = 0;

        //Getting all the parameters from the user
        int productId = Integer.parseInt(request.getParameter("productId"));
        String price = request.getParameter("price");
        String mrp_price = request.getParameter("mrp_price");
        HttpSession hs = request.getSession();
        try {
            //If user session is null user have to re-login
            if ((String) hs.getAttribute("name") == null) {
                response.sendRedirect("customer-login.jsp");
                //Inserting cart details to the database
            } else {
                int customerId = (int) hs.getAttribute("id");
                //Querying to the database.
                int addToCart = DatabaseConnection.insertUpdateFromSqlQuery("insert into tblcart values('" + id + "','" + price + "',1,'" + price + "','" + customerId + "','" + productId + "','" + mrp_price + "')");
                if (addToCart > 0) {
                    response.sendRedirect("index.jsp");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
