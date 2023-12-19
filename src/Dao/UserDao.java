package Dao;

import Entity.User;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;

public class UserDao {
    public void addUser(User user) throws SQLException {//新增成员
        QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
        String sql ="insert into user(account,password,name,email,phone," +
                "address,isAdmin,isValidate) values(?,?,?,?,?,?,?,?)";
        //8个?代表八种值, 作为update 的参数
        qr.update(sql,user.getAccount(),user.getPassword(),user.getName(),user.getEmail()
        ,user.getPhone(),user.getAddress(),user.isAdmin(),user.isValidate());
    }
    public User changeInfo(User user) throws SQLException{
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update user set account = ?, email = ?,name=?, phone=?,address=? where user_id=?";
        qr.update(sql,user.getAccount(),user.getEmail(),user.getName(),user.getPhone(),user.getAddress(),user.getUser_id());
        //返回修改后的user
        sql= "select * from user where user_id = ?";
        return qr.query(sql, new BeanHandler<User>(User.class),user.getUser_id());

    }

    //判断用户名是否已经存在
    public boolean isAccountExist(String userName) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where account = ?";
        User u=qr.query(sql,new BeanHandler<User>(User.class), userName);//查询数据库,并将查询的结构封装到一个BeanHandle中
        return u != null;
    }

    public boolean isEmailExist(String email) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where email = ?";
        User u=qr.query(sql,new BeanHandler<User>(User.class), email);//查询数据库,并将查询的结构封装到一个BeanHandle中
        return u != null;
    }

    public User accountPassword(String account,String password) throws SQLException{
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where account = ? and password = ?";
        //直接返回查询到的对象
        return qr.query(sql, new BeanHandler<User>(User.class),account,password);

    }
    public User emailPassword(String email,String password) throws SQLException{
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where email = ? and password = ?";
        //直接返回查询到的对象
        return qr.query(sql, new BeanHandler<User>(User.class),email,password);
    }


}
