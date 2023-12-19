<%@ page import="Entity.Book" %>
<%@ page import="Dao.BookDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="Dao.TypeDao" %>
<%@ page import="Dao.OrderDao" %>
<%@ page import="Entity.Order" %>
<%@ page import="java.util.HashMap" %>
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
        <c:if test="${!empty msg}">
            <h3 align="center">${msg}</h3>
        </c:if>
        <br>
        <ul class="navtab">
            <li > <a href="manage.jsp">全部商品</a></li>
            <li > <a href="Orders.jsp">所有订单</a></li>

        </ul>
        <br>
        <table class="table">
            <tr>
                <th width="5%">ID</th>
                <th width="10%">总价格</th>
                <th width="5%">购买数量</th>
                <th width="10%">支付状态</th>
                <th width="10%">支付类型</th>
                <th width="10%">收货人</th>
                <th width="10%">手机号码</th>
                <th width="10%">收货地址</th>
                <th width="20%">购买时间</th>
                <th width="10%">操作</th>
            </tr>
            <%
                OrderDao od =new OrderDao();
                List<Order> orders = od.getOrders();
                request.setAttribute("orders",orders);
                Map<Integer,String> status=new HashMap<>();//1未付款/2已付款/3已发货/4已完成
                status.put(1,"未付款");status.put(2,"已付款");status.put(3,"已发货");status.put(4,"已完成");
                request.setAttribute("Status",status);
                Map<Integer,String> payType = new HashMap<>();
                payType.put(1,"微信支付");payType.put(2,"支付宝");payType.put(3,"货到付款");
                request.setAttribute("PayType",payType);
            %>
            <c:forEach items="<%=orders%>" var="o">
                <tr>
                    <td><p>${o.order_id}</p></td>
                    <td><p>${o.total_price} </p></td>
                    <td><p> ${o.purchase_num} </p></td>
                    <td><p>${Status.get(o.status)}</p></td>
                    <td><p>${PayType.get(o.paytype)} </p></td>
                    <td><p>${o.user_name}</p></td>
                    <td><p>${o.phone}</p></td>
                    <td><p>${o.address}</p></td>
                    <td><p>${o.time}</p></td>
                    <td class ="actions">
                        <a class="act" href="deleteOrder?order_id=${o.order_id}">删除</a>
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
