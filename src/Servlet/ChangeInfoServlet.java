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

@WebServlet("/inf_change")
public class ChangeInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            //user对象封装了修改后的信息
            BeanUtils.copyProperties(user,req.getParameterMap());

            user.setUser_id(((User)req.getSession().getAttribute("user")).getUser_id());

            UserService us=new UserService();
            User changedUser = us.changeInfo(user);
            if( changedUser !=null){
                req.setAttribute("msg","修改成功!");
                req.getSession().setAttribute("user",changedUser);
                req.getRequestDispatcher("/user_center.jsp").forward(req,resp);
            }else {
                req.setAttribute("msg","修改失败, 用户名或邮箱不能为空");
                req.getRequestDispatcher("/user_center.jsp").forward(req,resp);
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
