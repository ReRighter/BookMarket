package Dao;

import Entity.Book;
import Entity.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class BookDao {
    public List<Book> getBooks()  {
        QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
        String sql= "select * from books";
        List<Book> books= null;
        try {
            books = qr.query(sql,new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public Book getBookById(int id)  {
        QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from books where book_id = ?";
        try {
            return qr.query(sql,new BeanHandler<Book>(Book.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Book> getBookByType(int type){
        QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
        String sql= "select * from books where type_id = ? ";
        List<Book> books= null;
        try {
            books = qr.query(sql,new BeanListHandler<Book>(Book.class),type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
    public void changeBookInfo(Book b) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update books set name = ?, cover_img = ?, price=?,type_id=?,total_num =? where book_id=?";
        qr.update(sql,b.getName(),b.getCover_img(),b.getPrice(),b.getType_id(),b.getTotal_num(),b.getBook_id());
    }

    public int insertBook(Book newBook) {
        QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
        String sql ="insert into books(name, cover_img,price,type_id,total_num) " +
                "values(?,?,?,?,?)";
        Book book=null;
        try {
            qr.update(sql,newBook.getName(),newBook.getCover_img(),newBook.getPrice(),newBook.getType_id(),
                    newBook.getTotal_num());
            sql = "select * from books where name = ?";
             book = qr.query(sql,new BeanHandler<Book>(Book.class),newBook.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book.getBook_id();
    }
}
