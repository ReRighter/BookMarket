<%@ page import="Entity.Book" %>
<%@ page import="Dao.BookDao" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>
    <script type="text/javascript" src="js/layer.js"></script>
</head>
<body>
<div class="main">
    <!-- 这里引入 header-->
    <jsp:include page="heaer.jsp"></jsp:include>
    <div class="container"><!-- 主体放入左中右三个盒子 ;
             利用margin进行三栏布局, 中间的栏自适应, 所以放第三个-->
        <div class="lside">
            <h2>书籍分类</h2>
            <span class="type"><a href="book_classify.jsp?type=1">计算机类图书</a></span>
            <span class="type"><a href="book_classify.jsp?type=2">电气类图书</a></span>
            <span class="type"><a href="book_classify.jsp?type=3">文学类图书</a></span>
        </div>

        <div class="content">
            <% BookDao bd = new BookDao();
                int Type = Integer.parseInt(request.getParameter("type"));
                List<Book> books = bd.getBookByType(Type);
            %>
            <c:forEach var="bk" items="<%=books%>">
                <div class="book">
                    <a href="#">
                        <img src="img/${bk.cover_img}" class="img">
                    </a>
                    <dl>
                        <dt class="bookName">${bk.name}</dt>
                        <dt class="bookPrice">书籍价格: ${bk.price} 元</dt>
                        <dt class="bookNum">剩余数量: ${bk.total_num}</dt>
                        <dt class="shop"><br> <button onclick="buy(${bk.book_id})">加入购物车</button> </dt>
                    </dl>
                </div>
                <br>
            </c:forEach>

        </div>
        <div class="footer">
            <p>制作者: 周粤 2023.12</p>
        </div>
    </div>
</body>
</html>
