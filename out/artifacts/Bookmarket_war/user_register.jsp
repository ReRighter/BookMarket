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

            <div class="reg_content">
                <c:if test="${!empty msg}">
                    <h2>${msg}</h2>
                </c:if>

                <form action="register_servlet" method="post" accept-charset="UTF-8">
                    <div class="input">
                        <span>用户名<label style="color: red">*</label> </span><br>
                        <input type="text" name="account" placeholder="请输入用户名" required="required">
                    </div>
                    <div class="input">
                        <span>密码<label style="color: red">*</label> </span><br>
                        <input type="password" name="password" placeholder="请输入密码" required="required">
                    </div>
                    <div class="input">
                        <span>邮箱<label style="color: red">*</label> </span><br>
                        <input type="text" name="email" placeholder="请输入邮箱" required="required">
                    </div>
                    <div class="input">
                        <span>手机号 </span><br>
                        <input type="text" name="phone" placeholder="请输入手机号">
                    </div>
                    <div class="input">
                        <span>收货人 </span><br>
                        <input type="text" name="name" placeholder="请输入收货人">
                    </div>
                    <div class="input">
                        <span>收货地址 </span><br>
                        <input type="text" name="address" placeholder="请输入收货地址">
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
