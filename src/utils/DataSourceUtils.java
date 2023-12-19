package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class DataSourceUtils {
    //用于获取数据库链接的工具类
       public static ComboPooledDataSource Comb =new ComboPooledDataSource();
       public static DataSource ds=null;
     static {
         try {
             Comb.setDriverClass("com.mysql.cj.jdbc.Driver");
             Comb.setJdbcUrl("jdbc:mysql://localhost:3306/bookmarket?serverTimezone=GMT%2B8");
             Comb.setUser("admin" );
             Comb.setPassword("root");
             Comb.setMaxPoolSize(10);
             ds=Comb;

         } catch (PropertyVetoException e) {
             throw new RuntimeException(e);
         }

     }


    public static DataSource getDataSource(){
        return ds;
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void main(String[] args) throws SQLException {
        Connection con = ds.getConnection();
        DatabaseMetaData da = con.getMetaData();
        System.out.println(da.getURL());
        System.out.println(da.getUserName());
    }

}
