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
        <h1 class="user_center">新增图书</h1>
        <div class="reg_content">
            <c:if test="${!empty msg }">
                <h2>${msg}</h2>
            </c:if>
            <%
                TypeDao td =new TypeDao();
                List<Type> types = td.getTypesList();
                request.setAttribute("types",types);
            %>
            <form action="addBookServlet" method="post" enctype="multipart/form-data">
                <div class="input">
                    <span>书本名</span><br>
                    <input type="text"  name="name" required="required">
                </div>
                <div class="input">
                    <span>书籍封面</span><br>
                    <input type="file"  name="img" required="required">
                </div>
                <div class="input">
                    <span>书籍价格</span><br>
                    <input type="text"  name="price" required="required">
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
                    <input type="text"  name="total_num" required="required">
                </div>
                <br>
                <input type="submit" value="提交">
            </form>
        </div>
    </div>
    <div class="footer">
        <p>制作者: 周粤 2023.12</p>
    </div>
</div>
</body>
</html>
