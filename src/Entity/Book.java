package Entity;

public class Book {
    private int book_id;
    private String name;
    private String cover_img;
    private String detail_img;
    private float price;
    private int type_id;
    private int total_num;
    public Book(){

    }
    public Book(int book_id, String name, String cover_img,
                String detail_img, float price, int type_id, int total_num) {
        this.book_id = book_id;
        this.name = name;
        this.cover_img = cover_img;
        this.detail_img = detail_img;
        this.price = price;
        this.type_id = type_id;
        this.total_num = total_num;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getDetail_img() {
        return detail_img;
    }

    public void setDetail_img(String detail_img) {
        this.detail_img = detail_img;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getTotal_num() {
        return total_num;
    }

    public void setTotal_num(int total_num) {
        this.total_num = total_num;
    }
}
