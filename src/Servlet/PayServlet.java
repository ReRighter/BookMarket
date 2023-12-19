package Servlet;

import Entity.Order;
import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/payment")
public class PayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order =(Order)req.getSession().getAttribute("order");
        User user = (User)req.getSession().getAttribute("user");
        order.save(Integer.parseInt(req.getParameter("paytype")),user);
        resp.setHeader("refresh","3;index.jsp");
        resp.getWriter().print("success! return to index in 3 seconds!");
    }
}
