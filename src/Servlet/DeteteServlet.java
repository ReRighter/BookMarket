package Servlet;

import Entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/bookDelete")
public class DeteteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //拿到购物车
        Order od = (Order)req.getSession().getAttribute("order");
        int bookId = Integer.parseInt(req.getParameter("book_id"));
        od.delete(bookId);
        resp.getWriter().print("ok");
    }
}
