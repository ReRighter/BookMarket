<%@ page import="Entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="/WEB-INF/c.tld"  %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="main">
    <jsp:include page="heaer.jsp"></jsp:include>
    <div class="container">
        <h1 class="user_center">支付中心</h1>
        <div class="reg_content">
            <c:if test="${!empty msg}">
                <h2>${msg}</h2>
            </c:if>

            <form action="payment" method="post">
                <% User user =(User)request.getSession().getAttribute("user");
                %>
                <div class="input">
                    <span>收货人</span><br>
                    <input type="text" value="<%= user.getName()%>" >
                </div>
                <div class="input">
                    <span>手机号</span><br>
                    <input type="text" value="<%= user.getPhone()%>">
                </div>
                <div class="input">
                    <span>收货地址</span><br>
                    <input type="text" value="<%= user.getAddress()%>">
                </div>
                <br>

                <br>
                <label for="pay">选择支付方式</label>
                <select name="paytype" id="pay">
                    <option value="1">微信支付</option>
                    <option value="2">支付宝</option>
                    <option value="3">货到付款</option>
                </select>
                <input type="submit" value="支付">
            </form>
        </div>
    </div>
    <div class="footer">
        <p>制作者: 周粤 2023.12</p>
    </div>
</div>
</body>
</html>
