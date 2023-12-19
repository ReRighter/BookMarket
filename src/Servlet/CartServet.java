package Servlet;


import Dao.BookDao;
import Entity.Book;
import Entity.Order;
import Entity.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/addToCart")
public class CartServet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order o =null;
        if(req.getSession().getAttribute("order")!=null){
            o=(Order) req.getSession().getAttribute("order");
        }else{
            o=new Order();

        }
        int bookID=Integer.parseInt(req.getParameter("book_id"));
        System.out.println("id: "+bookID);
        BookDao bkd = new BookDao();
        Book bk = bkd.getBookById(bookID);
        if(bk.getTotal_num()>0){
            o.addBook(bk);

            req.getSession().setAttribute("order",o);
            resp.getWriter().print("ok");
        }else{
            resp.getWriter().print("bad");
            System.out.println("CartServlet bad");
        }

    }
}
