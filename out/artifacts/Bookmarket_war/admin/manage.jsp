<%@ page import="Entity.Book" %>
<%@ page import="Dao.BookDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="Dao.TypeDao" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="../js/cart.js"></script>
    <script type="text/javascript" src="../js/layer.js"></script>
</head>
<body>
<div class="main">
    <!-- 这里引入 header-->
    <jsp:include page="../heaer.jsp"></jsp:include>
    <div class="container">
        <div class="out_add"><button class="add">
            <a href="addBook.jsp">添加商品</a>
        </button></div>
        <br>
        <ul class="navtab">
            <li > <a href="manage.jsp">全部商品</a></li>
            <li > <a href="Orders.jsp">所有订单</a></li>

        </ul>
        <br>
        <table class="table">
            <tr>
                <th width="5%">ID</th>
                <th width="15%">图片</th>
                <th width="15%">名称</th>
                <th width="15%">总数</th>
                <th width="15%">价格</th>
                <th width="15%">类目</th>
                <th width="20%">操作</th>
            </tr>
            <%
                BookDao bd =new BookDao();
                TypeDao td = new TypeDao();
                List<Book> books= bd.getBooks();
                Map<Integer,String> map = td.getTypes();
                request.setAttribute("map",map);
            %>
            <c:forEach items="<%=books%>" var="b">
            <tr>
                <td><p>${b.book_id }</p></td>
                <td><p><img src="../img/${b.cover_img}" width="100px" height="100px"></p></td>
                <td><p> ${b.name} </p></td>
                <td><p>${b.total_num}</p></td>
                <td><p>${b.price} 元</p></td>
                <td><p>${map.get(b.type_id)}</p></td>
                <td class ="actions">

                    <a class="action" href="BookInfChange.jsp?book_id=${b.book_id}">修改</a>
                    <a class="action" href=>删除</a>
                </td>
            </tr>
            </c:forEach>
        </table>


    </div>
        <div class="footer">
            <p>制作者: 周粤 2023.12</p>
        </div>
    </div>
</body>
</html>
