<%@ page import="Dao.BookDao" %>
<%@ page import="Entity.Book" %>
<%@ page import="Entity.Type" %>
<%@ page import="Dao.TypeDao" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="/WEB-INF/c.tld"  %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="main">
    <jsp:include page="../heaer.jsp"></jsp:include>
    <div class="container">
        <h1 class="user_center">修改图书信息</h1>
        <div class="reg_content">
            <c:if test="${!empty msg}">
                <h2>${msg}</h2>
            </c:if>
            <%
                int id =Integer.parseInt( request.getParameter("book_id"));
                BookDao bd= new BookDao();
                Book book = bd.getBookById(id);
                request.setAttribute("book",book);
                TypeDao td =new TypeDao();
                List<Type> types = td.getTypesList();
                request.setAttribute("types",types);
            %>
            <form action="bookChange?book_id=${book.book_id}" method="post" enctype="multipart/form-data">
                <div class="input">
                    <span>书本名</span><br>
                    <input type="text" value="${book.name}" name="name">
                </div>
                <div class="input">
                    <span>书籍封面</span><br>
                    <img src="../img/${book.cover_img}" width="120px" height="120px">
                    <br>
                    <input type="file"  name="img">
                </div>
                <div class="input">
                    <span>书籍价格</span><br>
                    <input type="text" value="${book.price}" name="price">
                </div>
                <div class="input">
                    <label for="typeSelect">书籍类别</label><br>
                    <select name="type_id" id="typeSelect">
                        <c:forEach items="${types}" var="type">
                        <option value="${type.id}">${type.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input">
                    <span>总数量</span><br>
                    <input type="text" value="${book.total_num}" name="total_num">
                </div>
                <br>
                <input type="submit" value="修改">
            </form>
        </div>
    </div>
    <div class="footer">
        <p>制作者: 周粤 2023.12</p>
    </div>
</div>
</body>
</html>
