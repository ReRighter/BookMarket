package Servlet;

import Dao.BookDao;
import Entity.Book;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet("/admin/addBookServlet")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解析请求, 既有文件, 也有普通文本
        File f =new File("/admin/temp");
        DiskFileItemFactory factory=new DiskFileItemFactory();
        //设置文件缓存路径
        factory.setRepository(f);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            //将表单的所有内容封装成对象, 放在一个list
            List<FileItem> fileItems = upload.parseRequest(req);
            Book newBook= new Book();
            for (FileItem fileItem : fileItems) {
                //判断是否为普通字段
                if(!fileItem.isFormField()){
                    //不是普通字段就代表是文件, 即图片,读取并保存
                    String file = fileItem.getName();
                    String fileName = file.substring(file.lastIndexOf("\\")+1);

                    fileName =  UUID.randomUUID()+"-"+fileName;//给文件加一个前缀uid防止冲突
                    if(fileName.length()>30){
                        fileName = fileName.substring(fileName.length()-30);}

                    newBook.setCover_img(fileName);
                    //获取服务端的保存路径

                    String filePath = getServletContext().getRealPath("/img/"+fileName);
                    File saveFile = new File(filePath);
                    //创建输入输出流并写入文件
                    InputStream in =fileItem.getInputStream();
                    OutputStream out = Files.newOutputStream(saveFile.toPath());
                    byte[] buffer =new byte[1024];
                    int bytesRead;
                    while((bytesRead = in.read(buffer))!=-1){
                        out.write(buffer,0,bytesRead);
                    }

                    in.close();
                    out.close();
                }else {
                    switch (fileItem.getFieldName()){
                        case "name":
                            newBook.setName(fileItem.getString("utf-8"));
                            break;
                        case "price":
                            newBook.setPrice(Float.parseFloat(fileItem.getString()));
                            break;
                        case "type_id":
                            newBook.setType_id(Integer.parseInt(fileItem.getString()));
                            break;
                        case "total_num":
                            newBook.setTotal_num(Integer.parseInt(fileItem.getString()));
                    }
                }
            }
            BookDao bd =new BookDao();
            int id = bd.insertBook(newBook);
            req.setAttribute("msg","书籍添加成功!");
            req.getRequestDispatcher("BookInfChange.jsp?book_id="+id).forward(req,resp);
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }
    }
}
