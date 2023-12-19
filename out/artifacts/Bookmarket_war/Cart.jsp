<%@ page import="Entity.Order" %>
<%@ page import="Entity.OrderItem" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Collection" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>购物车</title>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>

</head>
<body>
<div class="main">
    <!-- 这里引入 header-->
    <jsp:include page="heaer.jsp"></jsp:include>
    <div class="container"><!-- 主体放入左中右三个盒子 ;
             利用margin进行三栏布局, 中间的栏自适应, 所以放第三个-->
        <% Order od=  (Order)request.getSession().getAttribute("order");
            Collection<OrderItem> c=null;
        if(od!=null){
            Map<Integer, OrderItem> m = od.getItemMap();
            c =m.values();

        }
        %>

            <div class="cart_content">
        <c:if test="<%=c!=null%>">
            <c:forEach var="item" items="<%=c%>">
                <div class="cart">
                        <img src="img/${item.img}" class="img">
                    <dl>
                        <dt class="bookName">${item.name}</dt>
                        <dt class="bookNum">购买数量: ${item.purchase_num}</dt>
                        <dt class="bookPrice">总价格: ${item.total_price} 元</dt>
                        <dt class="shop"><br>
                            <a href="javascript:buy(${item.book_id})">增加</a>
                            <a href="javascript:lessen(${item.book_id})">减少</a>
                            <a href="javascript:deletes(${item.book_id})">删除</a> </dt>
                    </dl>
                </div>
                <br>
            </c:forEach>
        </c:if>
            </div>
            <a href="orderSubmit"><button class="order_submit">提交订单</button></a>
        <div class="footer">
            <p>制作者: 周粤 2023.12</p>
        </div>
    </div>
</body>
</html>
