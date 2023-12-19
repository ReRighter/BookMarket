package Servlet;

import Entity.User;
import Service.UserService;
import com.mchange.v2.beans.BeansUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet",urlPatterns = "/register_servlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user= new User();

        try {
            //调用BeanUtil工具类快速将客户端传入的参数初始化为一个User类

            BeanUtils.copyProperties(user,req.getParameterMap());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        UserService UService = new UserService();

        try {
            if(UService.register(user)){
                req.setAttribute("msg","注册成功,请登录");
                req.getRequestDispatcher("user_login.jsp").forward(req,resp);

            }
            else {
                req.setAttribute("msg","用户名或邮箱已存在, 注册失败");
                req.getRequestDispatcher("user_register.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
