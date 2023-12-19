package Service;

import Dao.UserDao;
import Entity.User;

import java.sql.SQLException;

public class UserService {
    private UserDao ud = new UserDao();
    public boolean register(User user) throws SQLException { //注册服务
        //查询用户是否已经注册
        if(ud.isAccountExist(user.getAccount()) || ud.isEmailExist(user.getEmail())) {
            return false;
        }
        else {
            ud.addUser(user);
            return true;
        }
    }
    public User login(String accountOrEmail, String password) throws SQLException {
        User user =null;
        //先判断是不是账号登录
        user = ud.accountPassword(accountOrEmail,password);
        if(user==null){ //再判断是不是邮箱登录
            user = ud.emailPassword(accountOrEmail, password);
        }
        if(user!=null&& user.getUser_id()==1) user.setAdmin(true);
        return user;

    }
    public User changeInfo(User user) throws SQLException {
        if( !user.getAccount().isEmpty() && !user.getEmail().isEmpty() ){ //修改后的账号和邮箱不能为空
            return ud.changeInfo(user);
        }
        return null;
    }

}
