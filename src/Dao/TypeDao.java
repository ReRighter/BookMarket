package Dao;

import Entity.Type;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeDao {
    public Map<Integer,String> getTypes(){
        QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
        String sql= "select * from type";
        List<Type> types= null;
        try {
            types = qr.query(sql,new BeanListHandler<Type>(Type.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Map<Integer,String> m = new HashMap<>();
        for(Type t:types){
            m.put(t.getId(),t.getName());
        }
        return m;
    }
    public List<Type> getTypesList(){
        QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
        String sql= "select * from type";
        List<Type> types= null;
        try {
            types = qr.query(sql,new BeanListHandler<Type>(Type.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return types;
    }

}
