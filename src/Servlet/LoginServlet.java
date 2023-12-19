package Servlet;

import Entity.User;
import Service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/login_servlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取前端传入的用户名,密码
        UserService us = new UserService();
        User user = null;
        try {
            user = us.login(req.getParameter("AccountOrEmail")
                    ,req.getParameter("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user != null){
            req.setAttribute("msg","登录成功");
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("/user_center.jsp").forward(req,resp);
        }
        else {
            req.setAttribute("msg","登录失败, 用户名或密码错误");
            req.getRequestDispatcher("/user_login.jsp").forward(req,resp);
        }


    }
}
