<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<div class="header">
    <h1> 图书市场</h1>
    <p> BookMarket</p>
</div>
<div class = "nav">
    <a href="/BookMarket/index.jsp">首页</a>

    <a href="/BookMarket/user_register.jsp">注册</a>
    <c:if test="${!empty sessionScope.user}">
        <a href="/BookMarket/user_center.jsp">欢迎您,${sessionScope.user.getAccount()}</a>
        <a href="LogOutServlet">退出登录</a>
    </c:if>
    <c:if test="${empty sessionScope.user}">
        <a href="/BookMarket/user_login.jsp">登录</a>
    </c:if>
    <a href="/BookMarket/Cart.jsp">购物车</a>
    <c:if test="${ sessionScope.user.isAdmin()}">
        <a href="/BookMarket/admin/manage.jsp">后台管理</a>
    </c:if>
</div>