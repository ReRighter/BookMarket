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
    <h1 class="user_center">个人信息中心</h1>
    <div class="reg_content">
      <c:if test="${!empty msg}">
        <h2>${msg}</h2>
      </c:if>

      <form action="inf_change" method="post" accept-charset="UTF-8">
        <% User user =(User)request.getSession().getAttribute("user");
        %>
          <div class="input">
            <span>用户名</span><br>
            <input type="text" value="<%= user.getAccount()%>" name="account">
          </div>
        <div class="input">
          <span>邮箱</span><br>
          <input type="text" value="<%= user.getEmail()%>" name="email">
        </div>
        <div class="input">
          <span>收货人</span><br>
          <input type="text" value="<%= user.getName()%>" name="name">
        </div>
        <div class="input">
          <span>手机号</span><br>
          <input type="text" value="<%= user.getPhone()%>" name="phone">
        </div>
        <div class="input">
          <span>收货地址</span><br>
          <input type="text" value="<%= user.getAddress()%>" name="address">
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
