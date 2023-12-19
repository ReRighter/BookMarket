package Entity;

import Dao.BookDao;
import Dao.OrderDao;

import java.sql.Date;
import java.util.*;

public class Order {
    private int order_id;
    private float total_price=0;//总价
    private int purchase_num=0;// 商品总数
    private int status=1;//1未付款/2已付款/3已发货/4已完成
    private int paytype; //1微信/2支付宝/3货到付款
    private String user_name;
    private String phone;
    private String address;
    private String time;
    private User user;
    private Map<Integer,OrderItem> itemMap = new HashMap<>();
    private List<OrderItem> itemList = new ArrayList<>();
    public Order(){
    }
    public void addBook(Book book){
        if(itemMap.containsKey(book.getBook_id())){
            OrderItem item = itemMap.get(book.getBook_id());
            item.setPurchase_num(item.getPurchase_num()+1);//总数量加1
            item.setTotal_price(item.getTotal_price()+book.getPrice());//总价格加上当前这本书
        }else{
            OrderItem item = new OrderItem( book.getPrice(), 1,
                    book.getBook_id(),book.getCover_img(), book.getName());//如果购物车没有这本书就新建一个
            itemMap.put(book.getBook_id(), item); //将书放入购物车
        }
        purchase_num++;
        total_price+= book.getPrice();

    }
    public void delete(int id){
        itemMap.remove(id);
    }
    public  void lessen(int id){
        OrderItem item = itemMap.get(id);
        if(item.getPurchase_num()==0) return;
        purchase_num--;
        item.setPurchase_num(item.getPurchase_num()-1);
        BookDao bd = new BookDao();
        Book bk = bd.getBookById(id);
        item.setTotal_price(item.getTotal_price() - bk.getPrice());
        total_price-= bk.getPrice();
    }
    public void save(int Paytype, User user){
        time = new Date(System.currentTimeMillis()).toString();
        paytype = Paytype;
        user_name =user.getName();
        phone = user.getPhone();
        address=user.getAddress();
        status=2; //已支付
        OrderDao od = new OrderDao();
        od.saveOrder(this);


    }


    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public int getPurchase_num() {
        return purchase_num;
    }

    public void setPurchase_num(int purchase_num) {
        this.purchase_num = purchase_num;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Integer, OrderItem> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<Integer, OrderItem> itemMap) {
        this.itemMap = itemMap;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }
}
