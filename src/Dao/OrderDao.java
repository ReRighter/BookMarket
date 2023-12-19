package Dao;

import Entity.Order;
import Entity.Type;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    public void saveOrder(Order order){
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into `order`(total_price,purchase_num,status," +
                "paytype,user_name,phone,address,time) values(?,?,?,?,?,?,?,?)";
        try {
            queryRunner.update(sql,order.getTotal_price(),order.getPurchase_num(),
                    order.getStatus(),order.getPaytype(),order.getUser_name(),
                    order.getPhone(),order.getAddress(),order.getTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Order>getOrders(){
        QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
        String sql= "select * from `order`";
        List<Order> orders= null;
        try {
            orders = qr.query(sql,new BeanListHandler<Order>(Order.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public void deleteOrder(int id)  {
        QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
        String sql= "delete from `order` where order_id = ?";
        try {
            qr.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
